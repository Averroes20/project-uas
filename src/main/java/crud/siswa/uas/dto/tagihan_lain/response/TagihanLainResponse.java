package crud.siswa.uas.dto.tagihan_lain.response;

import crud.siswa.uas.model.Siswa;
import crud.siswa.uas.model.TagihanLain;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TagihanLainResponse {
    private TagihanLain tagihanLain;
    private String message;
    private int statusCode;
    private String status;
}
