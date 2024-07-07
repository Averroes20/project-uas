package crud.siswa.uas.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import crud.siswa.uas.model.Pembayaran;

@Repository
@EnableJpaRepositories
public interface PembayaranRepository extends JpaRepository<Pembayaran, Integer>, JpaSpecificationExecutor<Pembayaran> {
    
}
