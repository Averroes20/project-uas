package crud.siswa.uas.dto.tagihan_spp.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TagihanSPPRequestDto {
    private String bulan;
    private Integer transaksiId;
    private Integer siswaId;
    private Integer taId;
}
