package crud.siswa.uas.model;

import org.springframework.data.annotation.CreatedDate;
import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "komponen")
@Entity
public class Komponen {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "komponen_id", unique = true, nullable = false)
    private int komponenId;

    @Column(name = "kode_komponen")
    private String kodeKomponen;

    @Column(name = "nama_komponen")
    private String namaKomponen;

    @Column(name = "biaya")
    private int biaya;

    @Column(name = "kode_kelas")
    private int kodeKelas;

    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "tgl_dibuat", length = 29)
    private Timestamp tglDibuat;

    @ManyToOne
    @JoinColumn(name = "ta_id", referencedColumnName = "ta_id")
    private TahunAjaran tahunAjaran;
}
