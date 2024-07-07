package crud.siswa.uas.dto.tagihan_lain.response;

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
public class TagihanLainListResponse {
    private long total;
    private List<TagihanLainListResponseDto> data;
    private String message;
    private int statusCode;
    private String status;
}
