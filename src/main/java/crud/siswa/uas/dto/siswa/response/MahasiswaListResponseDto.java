package crud.siswa.uas.dto.siswa.response;

import java.sql.Date;

import crud.siswa.uas.dto.tahun_ajaran.response.TahunAjaranListResponseDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MahasiswaListResponseDto {
    private int siswaId;
    private TahunAjaranListResponseDto tahunAjaran;
    private String nisn;
    private String nama_lengkap;
    private Date tanggal_lahir;
    private String alamat;
    private String nama_ortu;
    private String telp;
    private String foto;
    private Boolean status;
}
