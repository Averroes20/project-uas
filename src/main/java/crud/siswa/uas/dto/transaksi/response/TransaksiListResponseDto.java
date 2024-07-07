package crud.siswa.uas.dto.transaksi.response;

import java.sql.Date;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import crud.siswa.uas.dto.pembayaran.response.PembayaranListResponseDto;
import crud.siswa.uas.dto.siswa.response.MahasiswaListResponseDto;
import crud.siswa.uas.dto.tahun_ajaran.response.TahunAjaranListResponseDto;
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
public class TransaksiListResponseDto {
    private int transaksiId;
    private String kodeTransaksi;
    private PembayaranListResponseDto pembayaran;
    
    @JsonDeserialize(using = CustomDateDeserializer.class)
    private Date tglPembayaran;

    @JsonDeserialize(using = StatusEnumDeserializer.class)
    private StatusEnum status;
}
