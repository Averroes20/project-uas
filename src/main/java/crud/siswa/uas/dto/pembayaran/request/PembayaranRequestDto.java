package crud.siswa.uas.dto.pembayaran.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PembayaranRequestDto {
    private String metodeBayar;
    private Integer taId;
}
