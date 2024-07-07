package crud.siswa.uas.dto.tagihan_lain.request;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TagihanLainCreateRequestDto {
    private Integer komponenId;
    private Integer transaksiId;
    private Integer taId;
    private Integer siswaId;
    private Date tglBayar;
    private Boolean status;
}
