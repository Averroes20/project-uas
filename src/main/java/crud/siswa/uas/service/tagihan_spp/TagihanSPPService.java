package crud.siswa.uas.service.tagihan_spp;

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

import crud.siswa.uas.dto.pembayaran.response.PembayaranListResponseDto;
import crud.siswa.uas.dto.siswa.response.MahasiswaListResponseDto;
import crud.siswa.uas.dto.tagihan_spp.request.TagihanSPPCreateRequestDto;
import crud.siswa.uas.dto.tagihan_spp.request.TagihanSPPRequestDto;
import crud.siswa.uas.dto.tagihan_spp.response.TagihanSPPListResponse;
import crud.siswa.uas.dto.tagihan_spp.response.TagihanSPPListResponseDto;
import crud.siswa.uas.dto.tagihan_spp.response.TagihanSPPResponse;
import crud.siswa.uas.dto.tahun_ajaran.response.TahunAjaranListResponseDto;
import crud.siswa.uas.dto.transaksi.response.TransaksiListResponseDto;
import crud.siswa.uas.model.Pembayaran;
import crud.siswa.uas.model.Siswa;
import crud.siswa.uas.model.TagihanSPP;
import crud.siswa.uas.model.TahunAjaran;
import crud.siswa.uas.model.Transaksi;
import crud.siswa.uas.repositories.PembayaranRepository;
import crud.siswa.uas.repositories.SiswaRepository;
import crud.siswa.uas.repositories.TagihanSPPRepository;
import crud.siswa.uas.repositories.TahunAjaranRepository;
import crud.siswa.uas.repositories.TransaksiRepository;
import crud.siswa.uas.service.tagihan_spp.specification.TagihanSPPSpecification;

@Service
public class TagihanSPPService {

    @Autowired
    private TahunAjaranRepository tahunAjaranRepository;

    @Autowired
    private TagihanSPPRepository tagihanSPPRepository;

    @Autowired
    private SiswaRepository siswaRepository;

    @Autowired
    private TransaksiRepository transaksiRepository;

    @Autowired
    private PembayaranRepository pembayaranRepository;

    @Autowired
    private MessageSource messageSource;

    public ResponseEntity<TagihanSPPListResponse> getSPP(TagihanSPPRequestDto tagihanSPPRequestDto, Pageable page) {
        try {
            Specification<TagihanSPP> sppSpec = TagihanSPPSpecification.sppFilter(tagihanSPPRequestDto);

            Page<TagihanSPP> spp = tagihanSPPRepository.findAll(sppSpec, page);

            List<TagihanSPPListResponseDto> tagihanSPPDto = spp.stream().map(tagihanSpp -> {
                Transaksi transaksi = tagihanSpp.getTransaksi();
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
                TransaksiListResponseDto transaksiDto = new TransaksiListResponseDto(
                    transaksi.getTransaksiId(),
                    transaksi.getKodeTransaksi(),
                    pembayaranDto,
                    transaksi.getTglPembayaran(),
                    transaksi.getStatus()
                );
                return new TagihanSPPListResponseDto(
                    tagihanSpp.getSppId(),
                    transaksiDto,
                    tagihanSpp.getBulan(),
                    tagihanSpp.getJmlBayar(),
                    tagihanSpp.getTglbayar(),
                    tagihanSpp.getStatus()
                );
            }).collect(Collectors.toList());

            long totalData = spp.getTotalElements();

            String message = messageSource.getMessage("get.spp.success", null, Locale.getDefault());

            return ResponseEntity.ok()
                .body(new TagihanSPPListResponse(totalData, tagihanSPPDto, message, HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase()));
        } catch (Exception e) {
            String message = messageSource.getMessage("internal.error", null, Locale.getDefault());
            return ResponseEntity.internalServerError()
                .body(new TagihanSPPListResponse(0, null, message, HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase()));
        }
    }

    public ResponseEntity<TagihanSPPResponse> createSPP(TagihanSPPCreateRequestDto tagihanSPPCreateRequestDto) {
        try {
            TahunAjaran tahunAjaran = tahunAjaranRepository.findById(tagihanSPPCreateRequestDto.getTaId()).orElseThrow();
            Transaksi transaksi = transaksiRepository.findById(tagihanSPPCreateRequestDto.getTransaksiId()).orElseThrow();
            Siswa siswa = siswaRepository.findById(tagihanSPPCreateRequestDto.getSiswaId()).orElseThrow();

            TagihanSPP tagihanSPP = new TagihanSPP();
            tagihanSPP.setTransaksi(transaksi);
            tagihanSPP.setSiswa(siswa);
            tagihanSPP.setTahunAjaran(tahunAjaran);
            tagihanSPP.setBulan(tagihanSPPCreateRequestDto.getBulan());
            tagihanSPP.setJmlBayar(tagihanSPPCreateRequestDto.getJmlBayar());
            tagihanSPP.setTglbayar(tagihanSPPCreateRequestDto.getTglBayar());
            tagihanSPP.setStatus(tagihanSPPCreateRequestDto.getStatus());

            TagihanSPP savedSPP = tagihanSPPRepository.save(tagihanSPP);

            String message = messageSource.getMessage("create.tagihan.spp.success", null, Locale.getDefault());
            return ResponseEntity.status(HttpStatus.CREATED).body(new TagihanSPPResponse(savedSPP, message, HttpStatus.CREATED.value(), HttpStatus.CREATED.getReasonPhrase()));
        } catch (Exception e) {
            String message = messageSource.getMessage("internal.error", null, Locale.getDefault());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new TagihanSPPResponse(null, message, HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase()));
        }
    }
}
