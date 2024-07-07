package crud.siswa.uas.service.komponen;

import java.sql.Timestamp;
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
import crud.siswa.uas.dto.komponen.request.KomponenCreateRequestDto;
import crud.siswa.uas.dto.komponen.request.KomponenRequestDto;
import crud.siswa.uas.dto.komponen.request.KomponenUpdateRequestDto;
import crud.siswa.uas.dto.komponen.response.KomponenListResponse;
import crud.siswa.uas.dto.komponen.response.KomponenListResponseDto;
import crud.siswa.uas.dto.komponen.response.KomponenResponse;
import crud.siswa.uas.dto.tahun_ajaran.response.TahunAjaranListResponseDto;
import crud.siswa.uas.model.Komponen;
import crud.siswa.uas.model.TahunAjaran;
import crud.siswa.uas.repositories.KomponenRepository;
import crud.siswa.uas.repositories.TahunAjaranRepository;
import crud.siswa.uas.service.komponen.specification.KomponenSpecification;
import jakarta.persistence.EntityNotFoundException;

@Service
public class KomponenService {
    
    @Autowired
    private KomponenRepository komponenRepository;

    @Autowired
    private TahunAjaranRepository tahunAjaranRepository;

    @Autowired
    private MessageSource messageSource;

    public ResponseEntity<KomponenListResponse> getKomponen(KomponenRequestDto komponenRequestDto, Pageable page) {
        try {
            Specification<Komponen> komponenSpec = KomponenSpecification.komponenFilter(komponenRequestDto);
            
            Page<Komponen> komponens = komponenRepository.findAll(komponenSpec, page);

            List<KomponenListResponseDto> komponenDto = komponens.stream().map(komponen -> {
                TahunAjaran tahunAjaran = komponen.getTahunAjaran();
                TahunAjaranListResponseDto tahunAjaranDto = new TahunAjaranListResponseDto(
                    tahunAjaran.getTaId(),
                    tahunAjaran.getPeriode(),
                    tahunAjaran.getTglMulai(),
                    tahunAjaran.getTglAkhir(),
                    tahunAjaran.getKurikulum()
                );
                return new KomponenListResponseDto(
                    komponen.getKomponenId(),
                    komponen.getKodeKomponen(),
                    komponen.getNamaKomponen(),
                    tahunAjaranDto, // Gunakan konstruktor yang benar
                    komponen.getBiaya(),
                    komponen.getKodeKelas());
            }).collect(Collectors.toList());

                long totalData = komponens.getTotalElements();

                String message = messageSource.getMessage("get.komponen.success", null, Locale.getDefault());

                return ResponseEntity
                .ok()
                .body(new KomponenListResponse(totalData, komponenDto, message, HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase()));
        } catch (Exception e) {
            String message = messageSource.getMessage("internal.error", null, Locale.getDefault());
            return ResponseEntity
                .internalServerError()
                .body(new KomponenListResponse(0, null, message, HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase()));
        }
    }

    public ResponseEntity<KomponenResponse> createKomponen(KomponenCreateRequestDto komponenCreateRequestDto) {
        try {
            TahunAjaran tahunAjaran = tahunAjaranRepository.findById(komponenCreateRequestDto.getTaId())
                .orElseThrow();

            Komponen komponen = new Komponen();
            komponen.setKodeKomponen(komponenCreateRequestDto.getKodeKomponen());
            komponen.setNamaKomponen(komponenCreateRequestDto.getNamaKomponen());
            komponen.setTahunAjaran(tahunAjaran);
            komponen.setBiaya(komponenCreateRequestDto.getBiaya());
            komponen.setKodeKelas(komponenCreateRequestDto.getKodeKelas());
            komponen.setTglDibuat(komponenCreateRequestDto.getTglDibuat() != null ? komponenCreateRequestDto.getTglDibuat() : new Timestamp(System.currentTimeMillis()));

            Komponen savedKomponen = komponenRepository.save(komponen);

            String message = messageSource.getMessage("create.komponen.success", null, Locale.getDefault());
            return ResponseEntity.status(HttpStatus.CREATED).body(new KomponenResponse(savedKomponen, message, HttpStatus.CREATED.value(), HttpStatus.CREATED.getReasonPhrase()));
        } catch (Exception e) {
            String message = messageSource.getMessage("internal.error", null, Locale.getDefault());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new KomponenResponse(null, message, HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase()));
        }
    }

// Method to update an existing Siswa
    @Transactional
    public ResponseEntity<KomponenResponse> updateKomponen(KomponenUpdateRequestDto komponenUpdateRequestDto) {
        
        Komponen existingKomponen = komponenRepository.findById(komponenUpdateRequestDto.getKomponenId()).orElseThrow(() -> new EntityNotFoundException(messageSource.getMessage("komponen.not.found", null, Locale.getDefault())));
        
        TahunAjaran tahunAjaran = tahunAjaranRepository.findById(komponenUpdateRequestDto.getTaId())
                .orElseThrow(() -> new RuntimeException("Tahun Ajaran not found")); // Customize exception as needed

        existingKomponen.setKodeKomponen(komponenUpdateRequestDto.getKodeKomponen());
        existingKomponen.setNamaKomponen(komponenUpdateRequestDto.getNamaKomponen());
        existingKomponen.setTahunAjaran(tahunAjaran);
        existingKomponen.setBiaya(komponenUpdateRequestDto.getBiaya());
        existingKomponen.setKodeKelas(komponenUpdateRequestDto.getKodeKelas());
        existingKomponen.setTglDibuat(komponenUpdateRequestDto.getTglDibuat() != null ? komponenUpdateRequestDto.getTglDibuat() : new Timestamp(System.currentTimeMillis()));

        Komponen updatedKomponen = komponenRepository.save(existingKomponen);

        String message = messageSource.getMessage("update.komponen.success", null, Locale.getDefault());
            return ResponseEntity
                    .ok()
                    .body(new KomponenResponse(updatedKomponen, message, HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase()));
    }

    public ResponseEntity<ResponseBodyDto> deleteKomponen(Integer id) {
        try {
            Optional<Komponen> komponenOptional = komponenRepository.findById(id);
            if (!komponenOptional.isPresent()) {
                String message = messageSource.getMessage("komponen.not.found", null, Locale.getDefault());
                return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new ResponseBodyDto(message, HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.getReasonPhrase()));
            }
            komponenRepository.deleteById(id);

            String message = messageSource.getMessage("delete.komponen.success", null, Locale.getDefault());
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
