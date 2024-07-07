package crud.siswa.uas.dto.tahun_ajaran.response;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TahunAjaranListResponseDto {
    private int taId;
    private int periode;
    private Date tglMulai;
    private Date tglAkhir;
    private String kurikulum;
}
