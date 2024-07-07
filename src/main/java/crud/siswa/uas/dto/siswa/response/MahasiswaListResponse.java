package crud.siswa.uas.dto.siswa.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MahasiswaListResponse {
    private long total;
    private List<MahasiswaListResponseDto> data;
    private String message;
    private int statusCode;
    private String status;
}
