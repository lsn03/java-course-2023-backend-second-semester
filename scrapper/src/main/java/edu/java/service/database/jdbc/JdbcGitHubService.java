package edu.java.service.database.jdbc;

import edu.java.domain.model.GitHubCommitDTO;
import edu.java.domain.repository.jdbc.JdbcGitHubRepository;
import edu.java.exception.exception.RecordAlreadyExistException;
import edu.java.service.database.GitHubService;
import java.net.URI;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class JdbcGitHubService implements GitHubService {
    private final JdbcGitHubRepository jdbcGitHubRepository;

    @Override
    @Transactional
    public Integer addCommits(List<GitHubCommitDTO> gitHubCommitList) {
        try {
            return jdbcGitHubRepository.addCommits(gitHubCommitList);
        } catch (DuplicateKeyException e) {
            throw new RecordAlreadyExistException(e);
        }
    }

    @Override
    @Transactional
    public Integer deleteCommits(List<GitHubCommitDTO> gitHubCommitList) {
        return jdbcGitHubRepository.deleteCommits(gitHubCommitList);
    }

    @Override
    @Transactional
    public List<GitHubCommitDTO> getCommits(Long linkId) {
        return jdbcGitHubRepository.getCommits(linkId);
    }

    @Override
    @Transactional
    public List<GitHubCommitDTO> getCommits(URI uri) {
        return jdbcGitHubRepository.getCommits(uri);
    }
}
