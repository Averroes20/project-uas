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
public class TagihanSPPCreateRequestDto {
    
    private Integer transaksiId;
    private Integer siswaId;
    private Integer taId;
    private String bulan;
    private int jmlBayar;
    private Date tglBayar;
    private Boolean status;
}
