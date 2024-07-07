package crud.siswa.uas.service.tagihan_spp.specification;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import crud.siswa.uas.dto.pembayaran.request.PembayaranRequestDto;
import crud.siswa.uas.dto.tagihan_spp.request.TagihanSPPRequestDto;
import crud.siswa.uas.model.Pembayaran;
import crud.siswa.uas.model.TagihanSPP;
import jakarta.persistence.criteria.Predicate;

public class TagihanSPPSpecification {
    public static Specification<TagihanSPP> sppFilter(TagihanSPPRequestDto tagihanSPPRequestDto){
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<Predicate>();

            if (tagihanSPPRequestDto.getBulan() != null) {
                String bulanValue = "%" + tagihanSPPRequestDto.getBulan().toLowerCase() + "%";
                Predicate bulanPredicate = criteriaBuilder.like(criteriaBuilder.lower(root.get("bulan")), bulanValue);
                predicates.add(bulanPredicate);
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
        };
    }
}
