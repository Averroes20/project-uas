package crud.siswa.uas.dto.pembayaran.response;

import java.sql.Date;

import crud.siswa.uas.dto.siswa.response.MahasiswaListResponseDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PembayaranListResponseDto {
    private int pembayaranId;
    private MahasiswaListResponseDto mahasiswaListResponseDto;
    private Date tglPembayaran;
    private int jumlahBayaran;
    private String metodeBayar;
}
