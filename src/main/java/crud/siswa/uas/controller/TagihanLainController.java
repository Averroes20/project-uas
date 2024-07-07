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
import crud.siswa.uas.dto.tagihan_lain.request.TagihanLainCreateRequestDto;
import crud.siswa.uas.dto.tagihan_lain.request.TagihanLainRequestDto;
import crud.siswa.uas.dto.tagihan_lain.response.TagihanLainListResponse;
import crud.siswa.uas.dto.tagihan_lain.response.TagihanLainResponse;
import crud.siswa.uas.dto.tagihan_spp.request.TagihanSPPCreateRequestDto;
import crud.siswa.uas.dto.tagihan_spp.request.TagihanSPPRequestDto;
import crud.siswa.uas.dto.tagihan_spp.response.TagihanSPPListResponse;
import crud.siswa.uas.dto.tagihan_spp.response.TagihanSPPResponse;
import crud.siswa.uas.service.tagihan_lain.TagihanLainService;
import crud.siswa.uas.service.tagihan_spp.TagihanSPPService;

@RestController
@RequestMapping("/api/tagihan-lain")
@CrossOrigin(origins = "http://localhost:4200")
public class TagihanLainController {
    @Autowired
    private TagihanLainService tagihanLainService;

    @GetMapping()
    public ResponseEntity<TagihanLainListResponse> getTL(
        @ModelAttribute TagihanLainRequestDto tagihanLainRequestDto,
        PageRequest pageRequest
    ){
        return tagihanLainService.getTL(tagihanLainRequestDto, pageRequest.getPage());
    }

    @PostMapping("/create")
    public ResponseEntity<TagihanLainResponse> createSPP(@RequestBody TagihanLainCreateRequestDto tagihanLainCreateRequestDto) {
        return tagihanLainService.createTL(tagihanLainCreateRequestDto);
    }
}
