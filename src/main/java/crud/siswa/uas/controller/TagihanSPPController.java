package crud.siswa.uas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
import crud.siswa.uas.dto.tagihan_spp.request.TagihanSPPCreateRequestDto;
import crud.siswa.uas.dto.tagihan_spp.request.TagihanSPPRequestDto;
import crud.siswa.uas.dto.tagihan_spp.response.TagihanSPPListResponse;
import crud.siswa.uas.dto.tagihan_spp.response.TagihanSPPResponse;
import crud.siswa.uas.service.pembayaran.PembayaranService;
import crud.siswa.uas.service.tagihan_spp.TagihanSPPService;

@RestController
@RequestMapping("/api/tagihan-spp")
@CrossOrigin(origins = "http://localhost:4200")
public class TagihanSPPController {
    @Autowired
    private TagihanSPPService tagihanSPPService;

    @GetMapping()
    public ResponseEntity<TagihanSPPListResponse> getTagihanSPP(
        @ModelAttribute TagihanSPPRequestDto tagihanSPPRequestDto,
        PageRequest pageRequest
    ){
        return tagihanSPPService.getSPP(tagihanSPPRequestDto, pageRequest.getPage());
    }

    @PostMapping("/create")
    public ResponseEntity<TagihanSPPResponse> createSPP(@RequestBody TagihanSPPCreateRequestDto tagihanSPPCreateRequestDto) {
        return tagihanSPPService.createSPP(tagihanSPPCreateRequestDto);
    }
}
