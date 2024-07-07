package crud.siswa.uas.service.transaksi;

import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import crud.siswa.uas.dto.ResponseBodyDto;
import crud.siswa.uas.dto.pembayaran.response.PembayaranListResponseDto;
import crud.siswa.uas.dto.siswa.response.MahasiswaListResponseDto;
import crud.siswa.uas.dto.siswa.response.MahasiswaResponse;
import crud.siswa.uas.dto.tahun_ajaran.response.TahunAjaranListResponseDto;
import crud.siswa.uas.dto.transaksi.request.TransaksiCreateRequestDto;
import crud.siswa.uas.dto.transaksi.request.TransaksiRequestDto;
import crud.siswa.uas.dto.transaksi.request.TransaksiUpdateRequestDto;
import crud.siswa.uas.dto.transaksi.response.TransaksiListResponse;
import crud.siswa.uas.dto.transaksi.response.TransaksiListResponseDto;
import crud.siswa.uas.dto.transaksi.response.TransaksiResponse;
import crud.siswa.uas.model.Pembayaran;
import crud.siswa.uas.model.Siswa;
import crud.siswa.uas.model.TahunAjaran;
import crud.siswa.uas.model.Transaksi;
import crud.siswa.uas.repositories.PembayaranRepository;
import crud.siswa.uas.repositories.SiswaRepository;
import crud.siswa.uas.repositories.TahunAjaranRepository;
import crud.siswa.uas.repositories.TransaksiRepository;
import crud.siswa.uas.service.transaksi.specification.TransaksiSpecification;
import jakarta.persistence.EntityNotFoundException;

@Service
public class TransaksiService {
    @Autowired
    private TransaksiRepository transaksiRepository;
    
    @Autowired
    private SiswaRepository siswaRepository;
    
    @Autowired
    private PembayaranRepository pembayaranRepository;

    @Autowired
    private TahunAjaranRepository tahunAjaranRepository;

    @Autowired
    private MessageSource messageSource;

    public ResponseEntity<TransaksiListResponse> getTransaksi(TransaksiRequestDto transaksiRequestDto, Pageable page) {
        try {
            Specification<Transaksi> transaksiSpec = TransaksiSpecification.transaksiFilter(transaksiRequestDto);
            
            Page<Transaksi> transaksis = transaksiRepository.findAll(transaksiSpec, page);

            List<TransaksiListResponseDto> transaksiDto = transaksis.stream().map(transaksi -> {
                Pembayaran pembayaran = transaksi.getPembayaran();
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
                PembayaranListResponseDto pembayaranDto = new PembayaranListResponseDto(
                    pembayaran.getPembayaranId(),
                    mahasiswaDto,
                    pembayaran.getTglPembayaran(),
                    pembayaran.getJumlahBayar(),
                    pembayaran.getMetodeBayar());
                return new TransaksiListResponseDto(
                    transaksi.getTransaksiId(),
                    transaksi.getKodeTransaksi(),
                    pembayaranDto,
                    transaksi.getTglPembayaran(),
                    transaksi.getStatus()
                );
            }).collect(Collectors.toList());

                long totalData = transaksis.getTotalElements();

                String message = messageSource.getMessage("get.transaksi.success", null, Locale.getDefault());

                return ResponseEntity.ok()
                    .body(new TransaksiListResponse(totalData, transaksiDto, message, HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase()));
        } catch (Exception e) {
            String message = messageSource.getMessage("internal.error", null, Locale.getDefault());
            return ResponseEntity
                .internalServerError()
                .body(new TransaksiListResponse(0, null, message, HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase()));
        }
    }

    public ResponseEntity<TransaksiResponse> createTransaksi(TransaksiCreateRequestDto transaksiCreateRequestDto) {
        try {
            TahunAjaran tahunAjaran = tahunAjaranRepository.findById(transaksiCreateRequestDto.getTaId()).orElseThrow();
            Siswa siswa = siswaRepository.findById(transaksiCreateRequestDto.getSiswaId()).orElseThrow();
            Pembayaran pembayaran = pembayaranRepository.findById(transaksiCreateRequestDto.getPembayaranId()).orElseThrow();

            Transaksi transaksi = new Transaksi();
            transaksi.setKodeTransaksi(transaksiCreateRequestDto.getKodeTransaksi());
            transaksi.setTahunAjaran(tahunAjaran);
            transaksi.setSiswa(siswa);
            transaksi.setPembayaran(pembayaran);
            transaksi.setTglPembayaran(transaksiCreateRequestDto.getTglPembayaran());
            transaksi.setStatus(transaksiCreateRequestDto.getStatus());

            Transaksi savedTransaksi = transaksiRepository.save(transaksi);

            String message = messageSource.getMessage("create.transaksi.success", null, Locale.getDefault());
            return ResponseEntity.status(HttpStatus.CREATED).body(new TransaksiResponse(savedTransaksi, message, HttpStatus.CREATED.value(), HttpStatus.CREATED.getReasonPhrase()));
        } catch (Exception e) {
            String message = messageSource.getMessage("internal.error", null, Locale.getDefault());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new TransaksiResponse(null, message, HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase()));
        }
    }

// Method to update an existing Siswa
    @Transactional
    public ResponseEntity<TransaksiResponse> updateTransaksi(Integer transaksiId, TransaksiUpdateRequestDto transaksiUpdateRequestDto) {
        
        Transaksi existingTransaksi = transaksiRepository.findById(transaksiId)
            .orElseThrow(() -> new EntityNotFoundException(messageSource.getMessage("transaksi.not.found", null, Locale.getDefault())));
        
        TahunAjaran tahunAjaran = tahunAjaranRepository.findById(transaksiUpdateRequestDto.getTaId())
                .orElseThrow(() -> new RuntimeException("Transaksi not found")); // Customize exception as needed

        Pembayaran pembayaran = pembayaranRepository.findById(transaksiUpdateRequestDto.getPembayaranId())
                .orElseThrow(() -> new RuntimeException("Transaksi not found")); // Customize exception as needed
        
        Siswa siswa = siswaRepository.findById(transaksiUpdateRequestDto.getSiswaId())
                .orElseThrow(() -> new RuntimeException("Transaksi not found")); // Customize exception as needed

        existingTransaksi.setTahunAjaran(tahunAjaran);
        existingTransaksi.setSiswa(siswa);
        existingTransaksi.setPembayaran(pembayaran);
        existingTransaksi.setTglPembayaran(transaksiUpdateRequestDto.getTglPembayaran());
        existingTransaksi.setStatus(transaksiUpdateRequestDto.getStatus());

        Transaksi updatedTransaksi = transaksiRepository.save(existingTransaksi);

        String message = messageSource.getMessage("update.transaksi.success", null, Locale.getDefault());
            return ResponseEntity
                    .ok().body(new TransaksiResponse(updatedTransaksi, message, HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase()));
    }

    public ResponseEntity<ResponseBodyDto> deleteTransaksi(Integer id) {
        try {
            Optional<Transaksi> transaksiOptional = transaksiRepository.findById(id);
            if (!transaksiOptional.isPresent()) {
                String message = messageSource.getMessage("transaksi.not.found", null, Locale.getDefault());
                return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new ResponseBodyDto(message, HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.getReasonPhrase()));
            }
            transaksiRepository.deleteById(id);

            String message = messageSource.getMessage("delete.transaksi.success", null, Locale.getDefault());
            return ResponseEntity
                .ok()
                .body(new ResponseBodyDto(message, HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase()));
        } catch (Exception e) {
            String message = messageSource.getMessage("internal.error", null, Locale.getDefault());
            return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ResponseBodyDto(message, HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase()));
        }
    }
}
