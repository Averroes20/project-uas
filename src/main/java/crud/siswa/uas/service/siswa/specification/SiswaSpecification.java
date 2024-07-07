package crud.siswa.uas.service.siswa.specification;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import crud.siswa.uas.dto.siswa.request.MahasiswaRequestDto;
import crud.siswa.uas.model.Siswa;
import jakarta.persistence.criteria.Predicate;

public class SiswaSpecification {
    public static Specification<Siswa> siswaFilter(MahasiswaRequestDto mahasiswaRequestDto){
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<Predicate>();

            if (mahasiswaRequestDto.getNamaLengkap() != null) {
                String namaLengkapValue = "%" + mahasiswaRequestDto.getNamaLengkap().toLowerCase() + "%";
                Predicate namaLengkapPredicate = criteriaBuilder.like(criteriaBuilder.lower(root.get("namaLengkap")), namaLengkapValue);
                predicates.add(namaLengkapPredicate);
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
        };
    }
}
