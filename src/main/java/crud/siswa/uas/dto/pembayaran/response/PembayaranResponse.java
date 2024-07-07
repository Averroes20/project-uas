package crud.siswa.uas.dto.pembayaran.response;

import crud.siswa.uas.model.Pembayaran;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PembayaranResponse {
    private Pembayaran pembayaran;
    private String message;
    private int statusCode;
    private String status;
}
