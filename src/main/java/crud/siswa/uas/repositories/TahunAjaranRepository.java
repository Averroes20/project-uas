package crud.siswa.uas.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import crud.siswa.uas.model.TahunAjaran;

@Repository
@EnableJpaRepositories
public interface TahunAjaranRepository extends JpaRepository<TahunAjaran, Integer>, JpaSpecificationExecutor<TahunAjaran> {
    
}
