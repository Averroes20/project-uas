package crud.siswa.uas.dto.tagihan_lain.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TagihanLainRequestDto {
    private String status;
    private Integer taId;
}
