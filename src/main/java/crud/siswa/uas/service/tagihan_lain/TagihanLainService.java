package crud.siswa.uas.service.tagihan_lain;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import crud.siswa.uas.dto.komponen.response.KomponenListResponseDto;
import crud.siswa.uas.dto.pembayaran.response.PembayaranListResponseDto;
import crud.siswa.uas.dto.siswa.response.MahasiswaListResponseDto;
import crud.siswa.uas.dto.tagihan_lain.request.TagihanLainCreateRequestDto;
import crud.siswa.uas.dto.tagihan_lain.request.TagihanLainRequestDto;
import crud.siswa.uas.dto.tagihan_lain.response.TagihanLainListResponse;
import crud.siswa.uas.dto.tagihan_lain.response.TagihanLainListResponseDto;
import crud.siswa.uas.dto.tagihan_lain.response.TagihanLainResponse;
import crud.siswa.uas.dto.tahun_ajaran.response.TahunAjaranListResponseDto;
import crud.siswa.uas.dto.transaksi.response.TransaksiListResponseDto;
import crud.siswa.uas.model.Komponen;
import crud.siswa.uas.model.Pembayaran;
import crud.siswa.uas.model.Siswa;
import crud.siswa.uas.model.TagihanLain;
import crud.siswa.uas.model.TahunAjaran;
import crud.siswa.uas.model.Transaksi;
import crud.siswa.uas.repositories.KomponenRepository;
import crud.siswa.uas.repositories.PembayaranRepository;
import crud.siswa.uas.repositories.SiswaRepository;
import crud.siswa.uas.repositories.TagihanLainRepository;
import crud.siswa.uas.repositories.TahunAjaranRepository;
import crud.siswa.uas.repositories.TransaksiRepository;
import crud.siswa.uas.service.tagihan_lain.specification.TagihanLainSpecification;

@Service
public class TagihanLainService {
    
    @Autowired
    private PembayaranRepository pembayaranRepository;

    @Autowired
    private KomponenRepository komponenRepository;

    @Autowired
    private TransaksiRepository transaksiRepository;

    @Autowired
    private TagihanLainRepository tagihanLainRepository;

    @Autowired
    private SiswaRepository siswaRepository;

    @Autowired
    private TahunAjaranRepository tahunAjaranRepository;

    @Autowired
    private MessageSource messageSource;

    public ResponseEntity<TagihanLainListResponse> getTL(TagihanLainRequestDto tagihanLainRequestDto, Pageable page) {
        try {
            Specification<TagihanLain> tlSpec = TagihanLainSpecification.tagihanLainFilter(tagihanLainRequestDto);

            Page<TagihanLain> tagihanLains = tagihanLainRepository.findAll(tlSpec, page);

            List<TagihanLainListResponseDto> tagihanLainListResponseDto = tagihanLains.stream().map(tagihanLain -> {
                Transaksi transaksi = tagihanLain.getTransaksiId();
                Pembayaran pembayaran = transaksi.getPembayaran();
                Siswa siswa = pembayaran.getSiswa();
                TahunAjaran tahunAjaran = siswa.getTahunAjaran();
                Komponen komponen = tagihanLain.getKomponenId();

                TahunAjaranListResponseDto tahunAjaranDto = new TahunAjaranListResponseDto(
                    tahunAjaran.getTaId(),
                    tahunAjaran.getPeriode(),
                    tahunAjaran.getTglMulai(),
                    tahunAjaran.getTglAkhir(),
                    tahunAjaran.getKurikulum()
                );

                MahasiswaListResponseDto mahasiswaDto = new MahasiswaListResponseDto(
                    siswa.getSiswaId(),
                    tahunAjaranDto,
                    siswa.getNisn(),
                    siswa.getNamaLengkap(),
                    siswa.getTanggalLahir(),
                    siswa.getAlamat(),
                    siswa.getNamaOrtu(),
                    siswa.getTelp(),
                    siswa.getFoto(),
                    siswa.getStatus()
                );

                PembayaranListResponseDto pembayaranDto = new PembayaranListResponseDto(
                    pembayaran.getPembayaranId(),
                    mahasiswaDto,
                    pembayaran.getTglPembayaran(),
                    pembayaran.getJumlahBayar(),
                    pembayaran.getMetodeBayar()
                );

                TransaksiListResponseDto transaksiDto = new TransaksiListResponseDto(
                    transaksi.getTransaksiId(),
                    transaksi.getKodeTransaksi(),
                    pembayaranDto,
                    transaksi.getTglPembayaran(),
                    transaksi.getStatus()
                );

                KomponenListResponseDto komponenDto = new KomponenListResponseDto(
                    komponen.getKomponenId(),
                    komponen.getKodeKomponen(),
                    komponen.getNamaKomponen(),
                    tahunAjaranDto,
                    komponen.getBiaya(),
                    komponen.getKodeKelas()
                );

                return new TagihanLainListResponseDto(
                    tagihanLain.getLainId(),
                    komponenDto,
                    transaksiDto,
                    tagihanLain.getTglBayar(),
                    tagihanLain.getStatus()
                );
            }).collect(Collectors.toList());

            long totalData = tagihanLains.getTotalElements();
            String message = messageSource.getMessage("get.tagihan.lain.success", null, Locale.getDefault());

            return ResponseEntity.ok()
                .body(new TagihanLainListResponse(totalData, tagihanLainListResponseDto, message, HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase()));
        } catch (Exception e) {
            String message = messageSource.getMessage("internal.error", null, Locale.getDefault());
            return ResponseEntity.internalServerError()
                .body(new TagihanLainListResponse(0, null, message, HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase()));
        }
    }

    public ResponseEntity<TagihanLainResponse> createTL(TagihanLainCreateRequestDto tagihanLainCreateRequestDto) {
        try {
            Komponen komponen = komponenRepository.findById(tagihanLainCreateRequestDto.getKomponenId()).orElseThrow();
            Transaksi transaksi = transaksiRepository.findById(tagihanLainCreateRequestDto.getTransaksiId()).orElseThrow();
            Siswa siswa = siswaRepository.findById(tagihanLainCreateRequestDto.getSiswaId()).orElseThrow();
            TahunAjaran tahunAjaran = tahunAjaranRepository.findById(tagihanLainCreateRequestDto.getTaId()).orElseThrow();

            TagihanLain tagihanLain = new TagihanLain();
            tagihanLain.setKomponenId(komponen);
            tagihanLain.setTransaksiId(transaksi);
            tagihanLain.setSiswaId(siswa);
            tagihanLain.setTahunAjaran(tahunAjaran);
            tagihanLain.setTglBayar(tagihanLainCreateRequestDto.getTglBayar());
            tagihanLain.setStatus(tagihanLainCreateRequestDto.getStatus());

            TagihanLain savedTagihanLain = tagihanLainRepository.save(tagihanLain);

            String message = messageSource.getMessage("create.tagihan.lain.success", null, Locale.getDefault());
            return ResponseEntity.status(HttpStatus.CREATED).body(new TagihanLainResponse(savedTagihanLain, message, HttpStatus.CREATED.value(), HttpStatus.CREATED.getReasonPhrase()));
        } catch (Exception e) {
            String message = messageSource.getMessage("internal.error", null, Locale.getDefault());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new TagihanLainResponse(null, message, HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase()));
        }
    }
}
