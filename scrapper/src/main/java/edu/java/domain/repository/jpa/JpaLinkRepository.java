package edu.java.domain.repository.jpa;

import edu.java.domain.model.LinkDTO;
import edu.java.domain.repository.LinkRepository;
import edu.java.domain.repository.jpa.entity.LinkEntity;
import edu.java.domain.repository.jpa.mapper.MapperLinkDTOLinkEntity;
import edu.java.exception.exception.RepeatTrackException;
import jakarta.persistence.EntityManager;
import java.net.URI;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
public class JpaLinkRepository implements LinkRepository {
    private final JpaLinkRepositoryInterface jpaLinkRepository;
    private final EntityManager entityManager;
    private final JpaLinkChatRepository jpaLinkChatRepository;

    @Override
    @Transactional
    public LinkDTO add(LinkDTO linkDTO) {

        linkDTO.setCreatedAt(LocalDateTime.now().atOffset(ZoneOffset.UTC));
        var inserted = MapperLinkDTOLinkEntity.dtoToEntity(linkDTO);
        try {
            jpaLinkRepository.save(inserted);
        } catch (DataIntegrityViolationException e) {
            throw new RepeatTrackException(e);
        }

        linkDTO.setLinkId(inserted.getLinkId());
        return linkDTO;
    }

    @Override
    @Transactional
    public Integer remove(LinkDTO linkDTO) {
        Long linkId = findLinkIdByUrl(linkDTO.getUri());
        linkDTO.setLinkId(linkId);
        var response = jpaLinkChatRepository.findAllByLinkId(linkId);

        if (response.isEmpty()) {
            return entityManager.createQuery("delete from LinkEntity le where le.linkId = :linkId")
                .setParameter("linkId", linkId)
                .executeUpdate();
        }
        return 0;

    }

    @Override
    public List<LinkDTO> findAllByChatId(Long tgChatId) {
        return jpaLinkChatRepository.findAllByChatId(tgChatId);
    }

    @Override
    public List<LinkDTO> findAllByLinkId(Long linkId) {
        return jpaLinkChatRepository.findAllByLinkId(linkId);
    }

    @Override
    public Long findLinkIdByUrl(URI uri) {
        var entity = jpaLinkRepository.findLinkEntityByUri(uri.toString());
        return entity.map(LinkEntity::getLinkId).orElse(null);
    }

    @Override
    public List<LinkDTO> findAll() {
        var entityList = jpaLinkRepository.findAll();
        return entityList.stream().map(MapperLinkDTOLinkEntity::entityToDto).toList();
    }

    @Override
    @Transactional
    public void updateLink(LinkDTO elem) {
        elem.setLastUpdate(OffsetDateTime.now());
        var entity = MapperLinkDTOLinkEntity.dtoToEntity(elem);
        jpaLinkRepository.save(entity);
    }

    @Override
    public List<LinkDTO> findAllOldLinks(Integer timeInSeconds) {
        LocalDateTime differenceTime = LocalDateTime.now().minusSeconds(timeInSeconds);
        var entityList = entityManager.createQuery("""
                select le
                from LinkEntity le
                where le.lastUpdate is null  or le.lastUpdate < :time
                """, LinkEntity.class)
            .setParameter("time", differenceTime)
            .getResultList();

        return entityList.stream().map(MapperLinkDTOLinkEntity::entityToDto).toList();
    }
}
