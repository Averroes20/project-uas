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
@Table(name = "tagihan_spp")
@Entity
public class TagihanSPP {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "spp_id", unique = true, nullable = false)
    private int sppId;

    @Column(name = "bulan")
    private String bulan;

    @Column(name = "jml_bayar")
    private int jmlBayar;

    @Column(name = "tgl_bayar")
    private Date tglbayar;

    @Column(name = "status")
    private Boolean status;

    @ManyToOne
    @JoinColumn(name = "transaksi_id", referencedColumnName = "transaksi_id")
    private Transaksi transaksi;

    @ManyToOne
    @JoinColumn(name = "siswa_id", referencedColumnName = "siswa_id")
    private Siswa siswa;

    @ManyToOne
    @JoinColumn(name = "ta_id", referencedColumnName = "ta_id")
    private TahunAjaran tahunAjaran;
}
