package crud.siswa.uas.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import crud.siswa.uas.model.Siswa;

@Repository
@EnableJpaRepositories
public interface SiswaRepository extends JpaRepository<Siswa, Integer>, JpaSpecificationExecutor<Siswa> {
}
