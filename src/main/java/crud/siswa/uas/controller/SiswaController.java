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
import crud.siswa.uas.dto.siswa.request.MahasiswaCreateRequestDto;
import crud.siswa.uas.dto.siswa.request.MahasiswaRequestDto;
import crud.siswa.uas.dto.siswa.request.MahasiswaUpdateRequestDto;
import crud.siswa.uas.dto.siswa.request.PageRequest;
import crud.siswa.uas.dto.siswa.response.MahasiswaListResponse;
import crud.siswa.uas.dto.siswa.response.MahasiswaResponse;
import crud.siswa.uas.service.siswa.SiswaService;



@RestController
@RequestMapping("/api/mahasiswa")
@CrossOrigin(origins = "http://localhost:4200")
public class SiswaController {
    @Autowired
    private SiswaService siswaService;

    @GetMapping()
    public ResponseEntity<MahasiswaListResponse> getSiswa(
        @ModelAttribute MahasiswaRequestDto mahasiswaRequestDto,
        PageRequest pageRequest
    ){
        return siswaService.getSiswa(mahasiswaRequestDto, pageRequest.getPage());
    }

    @PostMapping("/create")
    public ResponseEntity<MahasiswaResponse> createSiswa(@RequestBody MahasiswaCreateRequestDto siswaRequestDto) {
        return siswaService.createSiswa(siswaRequestDto);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<MahasiswaResponse> updateSiswa(@PathVariable("id") Integer siswaId, @RequestBody MahasiswaUpdateRequestDto mahasiswaUpdateRequestDto) {
        return siswaService.updateSiswa(siswaId, mahasiswaUpdateRequestDto);
    }
    

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseBodyDto> deleteSiswa(@PathVariable Integer id) {
        return siswaService.deleteSiswa(id);
    }

}
