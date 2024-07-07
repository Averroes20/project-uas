package crud.siswa.uas.model;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tagihan_lain")
@Entity
public class TagihanLain {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lain_id", unique = true, nullable = false)
    private int lainId;

    @Column(name = "tgl_bayar")
    private Date tglBayar;

    @Column(name = "status")
    private Boolean status;

    @ManyToOne
    @JoinColumn(name = "komponen_id", referencedColumnName = "komponen_id")
    private Komponen komponenId;

    @ManyToOne
    @JoinColumn(name = "transaksi_id", referencedColumnName = "transaksi_id")
    private Transaksi transaksiId;

    @ManyToOne
    @JoinColumn(name = "siswa_id", referencedColumnName = "siswa_id")
    private Siswa siswaId;

    @ManyToOne
    @JoinColumn(name = "ta_id", referencedColumnName = "ta_id")
    private TahunAjaran tahunAjaran;

}
