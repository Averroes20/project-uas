package crud.siswa.uas.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import crud.siswa.uas.model.TagihanLain;

@Repository
@EnableJpaRepositories
public interface TagihanLainRepository extends JpaRepository<TagihanLain, Integer>, JpaSpecificationExecutor<TagihanLain> {
    
}
