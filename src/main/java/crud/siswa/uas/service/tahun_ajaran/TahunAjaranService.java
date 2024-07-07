package crud.siswa.uas.service.tahun_ajaran;

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
import crud.siswa.uas.dto.tahun_ajaran.request.TahunAjaranCreateRequestDto;
import crud.siswa.uas.dto.tahun_ajaran.request.TahunAjaranRequestDto;
import crud.siswa.uas.dto.tahun_ajaran.request.TahunAjaranUpdateRequestDto;
import crud.siswa.uas.dto.tahun_ajaran.response.TahunAjaranListResponse;
import crud.siswa.uas.dto.tahun_ajaran.response.TahunAjaranListResponseDto;
import crud.siswa.uas.dto.tahun_ajaran.response.TahunAjaranResponse;
import crud.siswa.uas.model.TahunAjaran;
import crud.siswa.uas.repositories.TahunAjaranRepository;
import crud.siswa.uas.service.tahun_ajaran.specification.TahunAjaranSpecification;
import jakarta.persistence.EntityNotFoundException;

@Service
public class TahunAjaranService {

    @Autowired
    private TahunAjaranRepository tahunAjaranRepository;

    @Autowired
    private MessageSource messageSource;

    public ResponseEntity<TahunAjaranListResponse> getTahunAjaran(TahunAjaranRequestDto tahunAjaranRequestDto, Pageable page) {
        try {
            Specification<TahunAjaran> tahunAjaranSpec = TahunAjaranSpecification.taFilter(tahunAjaranRequestDto);
            
            Page<TahunAjaran> ta = tahunAjaranRepository.findAll(tahunAjaranSpec, page);

            List<TahunAjaranListResponseDto> taDto = ta.stream().map(tahunAjaran ->
                new TahunAjaranListResponseDto(
                    tahunAjaran.getTaId(),
                    tahunAjaran.getPeriode(),
                    tahunAjaran.getTglMulai(),
                    tahunAjaran.getTglAkhir(),
                    tahunAjaran.getKurikulum())
                ).collect(Collectors.toList());

            long totalData = ta.getTotalElements();

            String message = messageSource.getMessage("get.tahun.ajaran.success", null, Locale.getDefault());

            return ResponseEntity
            .ok()
            .body(new TahunAjaranListResponse(totalData, taDto, message, HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase()));
        } catch (Exception e) {
            String message = messageSource.getMessage("internal.error", null, Locale.getDefault());
            return ResponseEntity
                .internalServerError()
                .body(new TahunAjaranListResponse(0, null, message, HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase()));
        }
    }

    public ResponseEntity<TahunAjaranResponse> createTahunAjaran(TahunAjaranCreateRequestDto tahunAjaranCreateRequestDto) {
        try {
            TahunAjaran ta = new TahunAjaran();
            ta.setPeriode(tahunAjaranCreateRequestDto.getPeriode());
            ta.setTglMulai(tahunAjaranCreateRequestDto.getTglMulai());
            ta.setTglAkhir(tahunAjaranCreateRequestDto.getTglAkhir());
            ta.setKurikulum(tahunAjaranCreateRequestDto.getKurikulum());

            TahunAjaran savedTA = tahunAjaranRepository.save(ta);

            String message = messageSource.getMessage("create.tahun.ajaran.success", null, Locale.getDefault());
            return ResponseEntity.status(HttpStatus.CREATED).body(new TahunAjaranResponse(savedTA, message, HttpStatus.CREATED.value(), HttpStatus.CREATED.getReasonPhrase()));
        } catch (Exception e) {
            String message = messageSource.getMessage("internal.error", null, Locale.getDefault());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new TahunAjaranResponse(null, message, HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase()));
        }
    }

    @Transactional
    public ResponseEntity<TahunAjaranResponse> updateTahunAjaran(Integer taId, TahunAjaranUpdateRequestDto tahunAjaranUpdateRequestDto) {
        TahunAjaran existingTA = tahunAjaranRepository.findById(taId)
            .orElseThrow(() -> new EntityNotFoundException(messageSource.getMessage("tahun.ajaran.not.found", null, Locale.getDefault())));
        
        existingTA.setPeriode(tahunAjaranUpdateRequestDto.getPeriode());
        existingTA.setTglMulai(tahunAjaranUpdateRequestDto.getTglMulai());
        existingTA.setTglAkhir(tahunAjaranUpdateRequestDto.getTglAkhir());
        existingTA.setKurikulum(tahunAjaranUpdateRequestDto.getKurikulum());

        TahunAjaran updatedTA = tahunAjaranRepository.save(existingTA);

        String message = messageSource.getMessage("update.tahun.ajaran.success", null, Locale.getDefault());
        return ResponseEntity
                .ok()
                .body(new TahunAjaranResponse(updatedTA, message, HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase()));
    }

    public ResponseEntity<ResponseBodyDto> deleteTahunAjaran(Integer id) {
        try {
            Optional<TahunAjaran> taOptional = tahunAjaranRepository.findById(id);
            if (!taOptional.isPresent()) {
                String message = messageSource.getMessage("tahun.ajaran.not.found", null, Locale.getDefault());
                return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new ResponseBodyDto(message, HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.getReasonPhrase()));
            }
            tahunAjaranRepository.deleteById(id);

            String message = messageSource.getMessage("delete.tahun.ajaran.success", null, Locale.getDefault());
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