package crud.siswa.uas.dto.tagihan_spp.response;

import java.util.List;

import crud.siswa.uas.dto.komponen.response.KomponenListResponseDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TagihanSPPListResponse {
    private long total;
    private List<TagihanSPPListResponseDto> data;
    private String message;
    private int statusCode;
    private String status;
}
