package crud.siswa.uas.dto.tagihan_spp.request;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TagihanSPPUpdateRequestDto {
    private Integer sppId;
    private Integer transaksiId;
    private Integer siswaId;
    private Integer taId;
    private String bula;
    private int jmlBayar;
    private Date tglBayar;
    private Boolean status;
}
