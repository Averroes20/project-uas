package crud.siswa.uas.service.tagihan_lain.specification;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import crud.siswa.uas.dto.tagihan_lain.request.TagihanLainRequestDto;
import crud.siswa.uas.model.TagihanLain;
import jakarta.persistence.criteria.Predicate;

public class TagihanLainSpecification {
    public static Specification<TagihanLain> tagihanLainFilter(TagihanLainRequestDto tagihanLainRequestDto){
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<Predicate>();

            if (tagihanLainRequestDto.getStatus() != null) {
                String metodeBayarValue = "%" + tagihanLainRequestDto.getStatus().toLowerCase() + "%";
                Predicate metodeBayarPredicate = criteriaBuilder.like(criteriaBuilder.lower(root.get("status")), metodeBayarValue);
                predicates.add(metodeBayarPredicate);
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
        };
    }
}
