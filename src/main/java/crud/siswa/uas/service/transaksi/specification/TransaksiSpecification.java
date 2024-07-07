package crud.siswa.uas.service.transaksi.specification;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import crud.siswa.uas.dto.siswa.request.MahasiswaRequestDto;
import crud.siswa.uas.dto.transaksi.request.TransaksiRequestDto;
import crud.siswa.uas.model.Siswa;
import crud.siswa.uas.model.Transaksi;
import jakarta.persistence.criteria.Predicate;

public class TransaksiSpecification {
    public static Specification<Transaksi> transaksiFilter(TransaksiRequestDto transaksiRequestDto){
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<Predicate>();

            if (transaksiRequestDto.getKodeTransaksi() != null) {
                String kodeTransaksiValue = "%" + transaksiRequestDto.getKodeTransaksi().toLowerCase() + "%";
                Predicate kodeTransaksiPredicate = criteriaBuilder.like(criteriaBuilder.lower(root.get("kodeTransaksi")), kodeTransaksiValue);
                predicates.add(kodeTransaksiPredicate);
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
        };
    }
}
