package crud.siswa.uas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import crud.siswa.uas.dto.ResponseBodyDto;
import crud.siswa.uas.dto.siswa.request.PageRequest;
import crud.siswa.uas.dto.transaksi.request.TransaksiCreateRequestDto;
import crud.siswa.uas.dto.transaksi.request.TransaksiRequestDto;
import crud.siswa.uas.dto.transaksi.request.TransaksiUpdateRequestDto;
import crud.siswa.uas.dto.transaksi.response.TransaksiListResponse;
import crud.siswa.uas.dto.transaksi.response.TransaksiResponse;
import crud.siswa.uas.service.transaksi.TransaksiService;

@RestController
@RequestMapping("/api/transaksi")
@CrossOrigin(origins = "http://localhost:4200")
public class TransaksiController {
    @Autowired
    private TransaksiService transaksiService;

    @GetMapping()
    public ResponseEntity<TransaksiListResponse> getTransaksi(
        @ModelAttribute TransaksiRequestDto transaksiRequestDto,
        PageRequest pageRequest
    ){
        return transaksiService.getTransaksi(transaksiRequestDto, pageRequest.getPage());
    }

    @PostMapping("/create")
    public ResponseEntity<TransaksiResponse> createTransaksi(@RequestBody TransaksiCreateRequestDto transaksiCreateRequestDto) {
        return transaksiService.createTransaksi(transaksiCreateRequestDto);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<TransaksiResponse> updateTransaksi(@PathVariable("id") Integer transaksiId, @RequestBody TransaksiUpdateRequestDto transaksiUpdateRequestDto) {
        return transaksiService.updateTransaksi(transaksiId, transaksiUpdateRequestDto);
    }
    

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseBodyDto> deleteTransaksi(@PathVariable Integer id) {
        return transaksiService.deleteTransaksi(id);
    }
}
