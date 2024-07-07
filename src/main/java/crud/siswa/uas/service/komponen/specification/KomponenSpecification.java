package crud.siswa.uas.service.komponen.specification;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import crud.siswa.uas.dto.komponen.request.KomponenRequestDto;
import crud.siswa.uas.dto.siswa.request.MahasiswaRequestDto;
import crud.siswa.uas.model.Komponen;
import crud.siswa.uas.model.Siswa;
import jakarta.persistence.criteria.Predicate;

public class KomponenSpecification {
    public static Specification<Komponen> komponenFilter(KomponenRequestDto komponenRequestDto){
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<Predicate>();

            if (komponenRequestDto.getNamaKomponen() != null) {
                String namaKomponenValue = "%" + komponenRequestDto.getNamaKomponen().toLowerCase() + "%";
                Predicate namaKomponenPredicate = criteriaBuilder.like(criteriaBuilder.lower(root.get("namaKomponen")), namaKomponenValue);
                predicates.add(namaKomponenPredicate);
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
        };
    }
}
