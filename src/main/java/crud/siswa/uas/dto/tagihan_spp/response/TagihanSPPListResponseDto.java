package crud.siswa.uas.dto.tagihan_spp.response;

import java.sql.Date;

import crud.siswa.uas.dto.siswa.response.MahasiswaListResponseDto;
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
public class TagihanSPPListResponseDto {
    private int sppId;
    private TransaksiListResponseDto transaksiId;
    private String bulan;
    private int jmlBayar;
    private Date tglBayar;
    private Boolean status;
}
