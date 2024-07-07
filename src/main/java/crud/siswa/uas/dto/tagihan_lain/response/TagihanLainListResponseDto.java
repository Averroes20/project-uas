package crud.siswa.uas.dto.tagihan_lain.response;

import java.sql.Date;

import crud.siswa.uas.dto.komponen.response.KomponenListResponseDto;
import crud.siswa.uas.dto.tahun_ajaran.response.TahunAjaranListResponseDto;
import crud.siswa.uas.dto.transaksi.response.TransaksiListResponseDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TagihanLainListResponseDto {
    private int lainId;
    private KomponenListResponseDto komponen;
    private TransaksiListResponseDto transaksi;
    private Date tglBayar;
    private Boolean status; 
}
