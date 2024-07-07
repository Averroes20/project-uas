package crud.siswa.uas.service.siswa;

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
import crud.siswa.uas.dto.siswa.request.MahasiswaCreateRequestDto;
import crud.siswa.uas.dto.siswa.request.MahasiswaRequestDto;
import crud.siswa.uas.dto.siswa.request.MahasiswaUpdateRequestDto;
import crud.siswa.uas.dto.siswa.response.MahasiswaListResponse;
import crud.siswa.uas.dto.siswa.response.MahasiswaListResponseDto;
import crud.siswa.uas.dto.siswa.response.MahasiswaResponse;
import crud.siswa.uas.dto.tahun_ajaran.response.TahunAjaranListResponseDto;
import crud.siswa.uas.model.Siswa;
import crud.siswa.uas.model.TahunAjaran;
import crud.siswa.uas.repositories.SiswaRepository;
import crud.siswa.uas.repositories.TahunAjaranRepository;
import crud.siswa.uas.service.siswa.specification.SiswaSpecification;
import jakarta.persistence.EntityNotFoundException;

@Service
public class SiswaService {
    
    @Autowired
    private SiswaRepository siswaRepository;

    @Autowired
    private TahunAjaranRepository tahunAjaranRepository;

    @Autowired
    private MessageSource messageSource;

    public ResponseEntity<MahasiswaListResponse> getSiswa(MahasiswaRequestDto mahasiswaRequestDto, Pageable page) {
        try {
            Specification<Siswa> siswaSpec = SiswaSpecification.siswaFilter(mahasiswaRequestDto);
            
            Page<Siswa> siswas = siswaRepository.findAll(siswaSpec, page);

            List<MahasiswaListResponseDto> siswasDto = siswas.stream().map(siswa ->
                new MahasiswaListResponseDto(siswa.getSiswaId(),
                    new TahunAjaranListResponseDto(
                        siswa.getTahunAjaran().getTaId(), 
                        siswa.getTahunAjaran().getPeriode(),
                        siswa.getTahunAjaran().getTglMulai(),
                        siswa.getTahunAjaran().getTglAkhir(),
                        siswa.getTahunAjaran().getKurikulum()),
                    siswa.getNisn(),
                    siswa.getNamaLengkap(),
                    siswa.getTanggalLahir(),
                    siswa.getAlamat(),
                    siswa.getNamaOrtu(),
                    siswa.getTelp(),
                    siswa.getFoto(),
                    siswa.getStatus())
                ).collect(Collectors.toList());

                long totalData = siswas.getTotalElements();

                String message = messageSource.getMessage("get.siswa.success", null, Locale.getDefault());

                return ResponseEntity
                .ok()
                .body(new MahasiswaListResponse(totalData, siswasDto, message, HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase()));
        } catch (Exception e) {
            String message = messageSource.getMessage("internal.error", null, Locale.getDefault());
            return ResponseEntity
                .internalServerError()
                .body(new MahasiswaListResponse(0, null, message, HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase()));
        }
    }

    public ResponseEntity<MahasiswaResponse> createSiswa(MahasiswaCreateRequestDto mahasiswaCreateRequestDto) {
        try {
            TahunAjaran tahunAjaran = tahunAjaranRepository.findById(mahasiswaCreateRequestDto.getTaId())
                .orElseThrow();

            Siswa siswa = new Siswa();
            siswa.setTahunAjaran(tahunAjaran);
            siswa.setNisn(mahasiswaCreateRequestDto.getNisn());
            siswa.setNamaLengkap(mahasiswaCreateRequestDto.getNamaLengkap());
            siswa.setTanggalLahir(mahasiswaCreateRequestDto.getTanggalLahir());
            siswa.setAlamat(mahasiswaCreateRequestDto.getAlamat());
            siswa.setNamaOrtu(mahasiswaCreateRequestDto.getNamaOrtu());
            siswa.setTelp(mahasiswaCreateRequestDto.getTelp());
            siswa.setFoto(mahasiswaCreateRequestDto.getFoto());
            siswa.setStatus(mahasiswaCreateRequestDto.getStatus());

            Siswa savedSiswa = siswaRepository.save(siswa);

            String message = messageSource.getMessage("create.siswa.success", null, Locale.getDefault());
            return ResponseEntity.status(HttpStatus.CREATED).body(new MahasiswaResponse(savedSiswa, message, HttpStatus.CREATED.value(), HttpStatus.CREATED.getReasonPhrase()));
        } catch (Exception e) {
            String message = messageSource.getMessage("internal.error", null, Locale.getDefault());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new MahasiswaResponse(null, message, HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase()));
        }
    }

// Method to update an existing Siswa
    @Transactional
    public ResponseEntity<MahasiswaResponse> updateSiswa(MahasiswaUpdateRequestDto mahasiswaUpdateRequestDto) {
        
        Siswa existingSiswa = siswaRepository.findById(mahasiswaUpdateRequestDto.getSiswaId()).orElseThrow(() -> new EntityNotFoundException(messageSource.getMessage("siswa.not.found", null, Locale.getDefault())));
        
        TahunAjaran tahunAjaran = tahunAjaranRepository.findById(mahasiswaUpdateRequestDto.getTaId())
                .orElseThrow(() -> new RuntimeException("Tahun Ajaran not found")); // Customize exception as needed

        existingSiswa.setTahunAjaran(tahunAjaran);
        existingSiswa.setNisn(mahasiswaUpdateRequestDto.getNisn());
        existingSiswa.setNamaLengkap(mahasiswaUpdateRequestDto.getNamaLengkap());
        existingSiswa.setTanggalLahir(mahasiswaUpdateRequestDto.getTanggalLahir());
        existingSiswa.setAlamat(mahasiswaUpdateRequestDto.getAlamat());
        existingSiswa.setNamaOrtu(mahasiswaUpdateRequestDto.getNamaOrtu());
        existingSiswa.setTelp(mahasiswaUpdateRequestDto.getTelp());
        existingSiswa.setFoto(mahasiswaUpdateRequestDto.getFoto());
        existingSiswa.setStatus(mahasiswaUpdateRequestDto.getStatus());

        Siswa updatedSiswa = siswaRepository.save(existingSiswa);

        String message = messageSource.getMessage("update.siswa.success", null, Locale.getDefault());
            return ResponseEntity
                    .ok()
                    .body(new MahasiswaResponse(updatedSiswa, message, HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase()));
    }

    public ResponseEntity<ResponseBodyDto> deleteSiswa(Integer id) {
        try {
            Optional<Siswa> siswaOptional = siswaRepository.findById(id);
            if (!siswaOptional.isPresent()) {
                String message = messageSource.getMessage("siswa.not.found", null, Locale.getDefault());
                return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new ResponseBodyDto(message, HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.getReasonPhrase()));
            }
            siswaRepository.deleteById(id);

            String message = messageSource.getMessage("delete.siswa.success", null, Locale.getDefault());
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
