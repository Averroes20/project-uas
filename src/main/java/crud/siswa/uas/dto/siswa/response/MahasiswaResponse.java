package crud.siswa.uas.dto.siswa.response;

import crud.siswa.uas.model.Siswa;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MahasiswaResponse {
    private Siswa siswa;
    private String message;
    private int statusCode;
    private String status;
}
