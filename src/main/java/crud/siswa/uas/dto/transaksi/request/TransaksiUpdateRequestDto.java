package crud.siswa.uas.dto.transaksi.request;

import java.sql.Date;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import crud.siswa.uas.model.StatusEnum;
import crud.siswa.uas.utils.CustomDateDeserializer;
import crud.siswa.uas.utils.StatusEnumDeserializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransaksiUpdateRequestDto {
    private Integer transaksiId;

    private Integer taId;

    private Integer siswaId;

    private Integer pembayaranId;

    @JsonDeserialize(using = CustomDateDeserializer.class)
    private Date tglPembayaran;
    
    @JsonDeserialize(using = StatusEnumDeserializer.class)
    private StatusEnum status;
}
