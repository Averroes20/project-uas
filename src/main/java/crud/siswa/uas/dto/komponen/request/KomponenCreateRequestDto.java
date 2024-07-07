package crud.siswa.uas.dto.komponen.request;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class KomponenCreateRequestDto {
    private String kodeKomponen;
    private String namaKomponen;
    private Integer taId;
    private int biaya;
    private int kodeKelas;
    private Timestamp tglDibuat;
}
