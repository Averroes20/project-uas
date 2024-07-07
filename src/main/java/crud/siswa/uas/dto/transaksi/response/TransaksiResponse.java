package crud.siswa.uas.dto.transaksi.response;

import crud.siswa.uas.model.Siswa;
import crud.siswa.uas.model.Transaksi;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransaksiResponse {
    private Transaksi transaksi;
    private String message;
    private int statusCode;
    private String status;
}
