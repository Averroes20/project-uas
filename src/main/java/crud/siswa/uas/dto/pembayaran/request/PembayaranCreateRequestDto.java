package crud.siswa.uas.dto.pembayaran.request;

import java.sql.Date;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import crud.siswa.uas.utils.CustomDateDeserializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PembayaranCreateRequestDto {
    
    private Integer taId;
    
    private Integer siswaId;

    @JsonDeserialize(using = CustomDateDeserializer.class)
    private Date tglPembayaran;

    private int jumlahBayar;

    private String metodeBayar;
}
