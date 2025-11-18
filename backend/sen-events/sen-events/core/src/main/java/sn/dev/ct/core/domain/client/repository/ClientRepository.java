package sn.dev.ct.core.domain.client.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import sn.dev.ct.core.domain.base.BaseRepository;
import sn.dev.ct.core.domain.client.entity.Client;

import java.util.Optional;

@Repository
public interface ClientRepository extends BaseRepository<Client> {
    Optional<Client> findByAccountEmail(String email);
    Optional<Client> findByAccountUsername(String username);
    Page<Client> findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(String firstName, String lastName, Pageable pageable);

}
