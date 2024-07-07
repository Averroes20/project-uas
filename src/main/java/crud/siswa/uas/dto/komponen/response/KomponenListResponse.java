package crud.siswa.uas.dto.komponen.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class KomponenListResponse {
    private long total;
    private List<KomponenListResponseDto> data;
    private String message;
    private int statusCode;
    private String status;
}
