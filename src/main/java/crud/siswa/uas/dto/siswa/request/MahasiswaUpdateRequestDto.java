package crud.siswa.uas.dto.siswa.request;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MahasiswaUpdateRequestDto {
    private Integer taId;
    private String nisn;
    private String namaLengkap;
    private Date tanggalLahir;
    private String alamat;
    private String namaOrtu;
    private String telp;
    private String foto;
    private Boolean status;
}
