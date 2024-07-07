package crud.siswa.uas.dto.tahun_ajaran.response;

import crud.siswa.uas.model.TahunAjaran;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TahunAjaranResponse {
    private TahunAjaran tahunAjaran;
    private String message;
    private int statusCode;
    private String status;
}
