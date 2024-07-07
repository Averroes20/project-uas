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
@Table(name = "pembayaran")
@Entity
public class Pembayaran {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pembayaran_id", unique = true, nullable = false)
    private int pembayaranId;

    @ManyToOne
    @JoinColumn(name = "ta_id", referencedColumnName = "ta_id")
    private TahunAjaran tahunAjaran;

    @ManyToOne
    @JoinColumn(name = "siswa_id", referencedColumnName = "siswa_id")
    private Siswa siswa;

    @Column(name = "tgl_pembayaran")
    private Date tglPembayaran;

    @Column(name = "jumlah_bayar")
    private int jumlahBayar;

    @Column(name = "metode_bayar")
    private String metodeBayar;

}
