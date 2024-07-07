package crud.siswa.uas.dto.transaksi.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransaksiRequestDto {
    private String kodeTransaksi;
    private Integer taId;
    private Integer siswaId;
    private Integer pembayaranId;
}
