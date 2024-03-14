package edu.java.domain.repository;

import edu.java.domain.model.LinkDTO;
import java.net.URI;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;

public interface LinkRepository {
    LinkDTO add(LinkDTO linkDTO);

    Integer remove(LinkDTO linkDTO);

    List<LinkDTO> findAllByChatId(Long tgChatId);

    Long findUrl(URI uri);

    @Transactional List<LinkDTO> findAll();

    void updateLink(LinkDTO elem);

    List<LinkDTO> findAllOldLinks(Integer timeInSeconds);
}