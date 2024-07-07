package crud.siswa.uas.dto.tahun_ajaran.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TahunAjaranListResponse {    
    private long total;
    private List<TahunAjaranListResponseDto> data;
    private String message;
    private int statusCode;
    private String status;
}
