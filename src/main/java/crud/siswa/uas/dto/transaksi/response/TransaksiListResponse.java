package crud.siswa.uas.dto.transaksi.response;

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
public class TransaksiListResponse {
    private long total;
    private List<TransaksiListResponseDto> data;
    private String message;
    private int statusCode;
    private String status;
}
