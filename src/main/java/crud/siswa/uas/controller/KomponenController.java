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
import crud.siswa.uas.dto.komponen.request.KomponenCreateRequestDto;
import crud.siswa.uas.dto.komponen.request.KomponenRequestDto;
import crud.siswa.uas.dto.komponen.request.KomponenUpdateRequestDto;
import crud.siswa.uas.dto.komponen.response.KomponenListResponse;
import crud.siswa.uas.dto.komponen.response.KomponenResponse;
import crud.siswa.uas.dto.siswa.request.PageRequest;
import crud.siswa.uas.service.komponen.KomponenService;

@RestController
@RequestMapping("/api/komponen")
@CrossOrigin(origins = "http://localhost:4200")
public class KomponenController {
    @Autowired
    private KomponenService komponenService;

    @GetMapping()
    public ResponseEntity<KomponenListResponse> getKomponen(
        @ModelAttribute KomponenRequestDto komponenRequestDto,
        PageRequest pageRequest
    ){
        return komponenService.getKomponen(komponenRequestDto, pageRequest.getPage());
    }

    @PostMapping("/create")
    public ResponseEntity<KomponenResponse> createKomponen(@RequestBody KomponenCreateRequestDto komponenCreateRequestDto) {
        return komponenService.createKomponen(komponenCreateRequestDto);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<KomponenResponse> updateSiswa(@PathVariable("id") Integer komponenId, @RequestBody KomponenUpdateRequestDto komponenUpdateRequestDto) {
        return komponenService.updateKomponen(komponenId, komponenUpdateRequestDto);
    }
    

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseBodyDto> deleteSiswa(@PathVariable Integer id) {
        return komponenService.deleteKomponen(id);
    }
}
