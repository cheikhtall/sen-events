package sn.dev.ct.core.domain.base;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

@NoRepositoryBean
public interface BaseRepository<T> extends JpaRepository<T, Long> {
    List<T> findAllByIdIn(List<Long> ids);
}
