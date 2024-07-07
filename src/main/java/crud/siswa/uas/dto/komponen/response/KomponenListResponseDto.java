package crud.siswa.uas.dto.komponen.response;

import java.sql.Timestamp;

import crud.siswa.uas.dto.tahun_ajaran.response.TahunAjaranListResponseDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class KomponenListResponseDto {
    private Integer komponenId;
    private String kodeKomponen;
    private String namaKomponen;
    private TahunAjaranListResponseDto tahunAjaranListResponseDto;
    private int biaya;
    private int kodeKelas;
}
