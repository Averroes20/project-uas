package crud.siswa.uas.service.pembayaran;

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

import crud.siswa.uas.dto.pembayaran.request.PembayaranCreateRequestDto;
import crud.siswa.uas.dto.pembayaran.request.PembayaranRequestDto;
import crud.siswa.uas.dto.pembayaran.response.PembayaranListResponse;
import crud.siswa.uas.dto.pembayaran.response.PembayaranListResponseDto;
import crud.siswa.uas.dto.pembayaran.response.PembayaranResponse;
import crud.siswa.uas.dto.siswa.response.MahasiswaListResponseDto;
import crud.siswa.uas.dto.tahun_ajaran.response.TahunAjaranListResponseDto;
import crud.siswa.uas.model.Pembayaran;
import crud.siswa.uas.model.Siswa;
import crud.siswa.uas.model.TahunAjaran;
import crud.siswa.uas.repositories.PembayaranRepository;
import crud.siswa.uas.repositories.SiswaRepository;
import crud.siswa.uas.repositories.TahunAjaranRepository;
import crud.siswa.uas.service.pembayaran.specification.PembayaranSpecification;

@Service
public class PembayaranService {
    
    @Autowired
    private PembayaranRepository pembayaranRepository;

    @Autowired
    private SiswaRepository siswaRepository;

    @Autowired
    private TahunAjaranRepository tahunAjaranRepository;

    @Autowired
    private MessageSource messageSource;

    public ResponseEntity<PembayaranListResponse> getPembayaran(PembayaranRequestDto pembayaranRequestDto, Pageable page) {
        try {
            Specification<Pembayaran> pembayaranSpec = PembayaranSpecification.pembayaranFilter(pembayaranRequestDto);

            Page<Pembayaran> pembayarans = pembayaranRepository.findAll(pembayaranSpec, page);

            List<PembayaranListResponseDto> pembayaranDto = pembayarans.stream().map(pembayaran -> {
                Siswa siswa = pembayaran.getSiswa();
                TahunAjaran tahunAjaran = siswa.getTahunAjaran();
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
                return new PembayaranListResponseDto(
                    pembayaran.getPembayaranId(),
                    mahasiswaDto,
                    pembayaran.getTglPembayaran(),
                    pembayaran.getJumlahBayar(),
                    pembayaran.getMetodeBayar()
                );
            }).collect(Collectors.toList());

            long totalData = pembayarans.getTotalElements();

            String message = messageSource.getMessage("get.pembayaran.success", null, Locale.getDefault());

            return ResponseEntity.ok()
                .body(new PembayaranListResponse(totalData, pembayaranDto, message, HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase()));
        } catch (Exception e) {
            String message = messageSource.getMessage("internal.error", null, Locale.getDefault());
            return ResponseEntity.internalServerError()
                .body(new PembayaranListResponse(0, null, message, HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase()));
        }
    }

    public ResponseEntity<PembayaranResponse> createPembayaran(PembayaranCreateRequestDto pembayaranCreateRequestDto) {
        try {
            TahunAjaran tahunAjaran = tahunAjaranRepository.findById(pembayaranCreateRequestDto.getTaId())
                .orElseThrow();

            Siswa siswa = siswaRepository.findById(pembayaranCreateRequestDto.getSiswaId()).orElseThrow();

            Pembayaran pembayaran = new Pembayaran();
            pembayaran.setTahunAjaran(tahunAjaran);
            pembayaran.setSiswa(siswa);
            pembayaran.setTglPembayaran(pembayaranCreateRequestDto.getTglPembayaran());
            pembayaran.setJumlahBayar(pembayaranCreateRequestDto.getJumlahBayar());
            pembayaran.setMetodeBayar(pembayaranCreateRequestDto.getMetodeBayar());

            Pembayaran savedPembayaran = pembayaranRepository.save(pembayaran);

            String message = messageSource.getMessage("create.pembayaran.success", null, Locale.getDefault());
            return ResponseEntity.status(HttpStatus.CREATED).body(new PembayaranResponse(savedPembayaran, message, HttpStatus.CREATED.value(), HttpStatus.CREATED.getReasonPhrase()));
        } catch (Exception e) {
            String message = messageSource.getMessage("internal.error", null, Locale.getDefault());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new PembayaranResponse(null, message, HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase()));
        }
    }
}
