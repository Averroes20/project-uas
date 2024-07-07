package crud.siswa.uas.model;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tahun_ajaran")
@Entity
@Builder
public class TahunAjaran {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ta_id")
    private int taId;

    @Column(name = "periode")
    private int periode;

    @Column(name = "tgl_mulai")
    private Date tglMulai;

    @Column(name = "tgl_akhir")
    private Date tglAkhir;

    @Column(name = "kurikulum")
    private String kurikulum;
}
