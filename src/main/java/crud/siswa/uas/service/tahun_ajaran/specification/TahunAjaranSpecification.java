package crud.siswa.uas.service.tahun_ajaran.specification;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import crud.siswa.uas.dto.tahun_ajaran.request.TahunAjaranRequestDto;
import crud.siswa.uas.model.TahunAjaran;
import jakarta.persistence.criteria.Predicate;

public class TahunAjaranSpecification {
    public static Specification<TahunAjaran> taFilter(TahunAjaranRequestDto tahunAjaranRequestDto){
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<Predicate>();

            if (tahunAjaranRequestDto.getPeriode() != null) {
                String periodeValue = "%" + tahunAjaranRequestDto.getPeriode().toLowerCase() + "%";
                Predicate periodePredicate = criteriaBuilder.like(criteriaBuilder.lower(root.get("periode")), periodeValue);
                predicates.add(periodePredicate);
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
        };
    }
}
