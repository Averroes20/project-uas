package crud.siswa.uas.dto.komponen.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class KomponenRequestDto {
    private String namaKomponen;
    private Integer taId;
}
