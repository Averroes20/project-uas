package crud.siswa.uas.dto.tahun_ajaran.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TahunAjaranRequestDto {
    private String periode;
}
