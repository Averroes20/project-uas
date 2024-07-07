package crud.siswa.uas.model;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
@Table(name = "transaksi")
@Entity
public class Transaksi {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transaksi_id", unique = true, nullable = false)
    private int transaksiId;

    @Column(name = "kode_transaksi")
    private String kodeTransaksi;
    
    @Column(name = "tgl_pembayaran")
    private Date tglPembayaran;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "status", columnDefinition = "status_enum")
    private StatusEnum status;

    @ManyToOne
    @JoinColumn(name = "ta_id", referencedColumnName = "ta_id")
    private TahunAjaran tahunAjaran;

    @ManyToOne
    @JoinColumn(name = "siswa_id", referencedColumnName = "siswa_id")
    private Siswa siswa;

    @ManyToOne
    @JoinColumn(name = "pembayaran_id", referencedColumnName = "pembayaran_id")
    private Pembayaran pembayaran;
}
