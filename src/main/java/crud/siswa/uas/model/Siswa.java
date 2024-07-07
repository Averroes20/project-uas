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
@Table(name = "siswa")
@Entity
public class Siswa {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "siswa_id", unique = true, nullable = false)
    private int siswaId;
    
    @Column(name = "nisn")
    private String nisn;
    
    @Column(name = "nama_lengkap")
    private String namaLengkap;
    
    @Column(name = "tanggal_lahir")
    private Date tanggalLahir;
    
    @Column(name = "alamat")
    private String alamat;
    
    @Column(name = "nama_ortu")
    private String namaOrtu;
    
    @Column(name = "telp")
    private String telp;
    
    @Column(name = "foto")
    private String foto;
    
    @Column(name = "status")
    private Boolean status;

    @ManyToOne
    @JoinColumn(name = "ta_id", referencedColumnName = "ta_id")
    private TahunAjaran tahunAjaran;
}
