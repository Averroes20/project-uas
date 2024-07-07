package crud.siswa.uas.dto.tahun_ajaran.request;

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
public class TahunAjaranCreateRequestDto {

    private int periode;

    @JsonDeserialize(using = CustomDateDeserializer.class)
    private Date tglMulai;

    @JsonDeserialize(using = CustomDateDeserializer.class)
    private Date tglAkhir;
    
    private String kurikulum;
    
}
