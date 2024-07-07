package crud.siswa.uas.dto.siswa.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MahasiswaRequestDto {
    private String namaLengkap;
    private Integer taId;
}
