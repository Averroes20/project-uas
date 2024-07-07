package crud.siswa.uas.service.pembayaran.specification;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import crud.siswa.uas.dto.pembayaran.request.PembayaranRequestDto;
import crud.siswa.uas.model.Pembayaran;
import jakarta.persistence.criteria.Predicate;

public class PembayaranSpecification {
    public static Specification<Pembayaran> pembayaranFilter(PembayaranRequestDto pembayaranRequestDto){
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<Predicate>();

            if (pembayaranRequestDto.getMetodeBayar() != null) {
                String metodeBayarValue = "%" + pembayaranRequestDto.getMetodeBayar().toLowerCase() + "%";
                Predicate metodeBayarPredicate = criteriaBuilder.like(criteriaBuilder.lower(root.get("metodeBayar")), metodeBayarValue);
                predicates.add(metodeBayarPredicate);
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
        };
    }
}
