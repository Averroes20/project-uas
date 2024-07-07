package crud.siswa.uas.dto.komponen.response;

import crud.siswa.uas.model.Komponen;
import crud.siswa.uas.model.Siswa;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class KomponenResponse {
    private Komponen komponen;
    private String message;
    private int statusCode;
    private String status;
}
