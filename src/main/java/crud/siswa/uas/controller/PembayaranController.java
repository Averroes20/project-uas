package crud.siswa.uas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import crud.siswa.uas.dto.pembayaran.request.PageRequest;
import crud.siswa.uas.dto.pembayaran.request.PembayaranCreateRequestDto;
import crud.siswa.uas.dto.pembayaran.request.PembayaranRequestDto;
import crud.siswa.uas.dto.pembayaran.response.PembayaranListResponse;
import crud.siswa.uas.dto.pembayaran.response.PembayaranResponse;
import crud.siswa.uas.service.pembayaran.PembayaranService;

@RestController
@RequestMapping("/api/pembayaran")
public class PembayaranController {
    @Autowired
    private PembayaranService pembayaranService;

    @GetMapping()
    public ResponseEntity<PembayaranListResponse> getPembayaran(
        @ModelAttribute PembayaranRequestDto pembayaranRequestDto,
        PageRequest pageRequest
    ){
        return pembayaranService.getPembayaran(pembayaranRequestDto, pageRequest.getPage());
    }

    @PostMapping("/create")
    public ResponseEntity<PembayaranResponse> createPembayaran(@RequestBody PembayaranCreateRequestDto pembayaranCreateRequestDto) {
        return pembayaranService.createPembayaran(pembayaranCreateRequestDto);
    }

}
