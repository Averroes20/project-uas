package crud.siswa.uas.dto.tagihan_spp.response;

import crud.siswa.uas.model.Siswa;
import crud.siswa.uas.model.TagihanSPP;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TagihanSPPResponse {
    private TagihanSPP tagihanSPP;
    private String message;
    private int statusCode;
    private String status;
}
