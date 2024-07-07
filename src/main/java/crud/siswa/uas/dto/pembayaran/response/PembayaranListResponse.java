package crud.siswa.uas.dto.pembayaran.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PembayaranListResponse {
    private long total;
    private List<PembayaranListResponseDto> data;
    private String message;
    private int statusCode;
    private String status;
}
