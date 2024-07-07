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
import crud.siswa.uas.dto.tahun_ajaran.request.TahunAjaranCreateRequestDto;
import crud.siswa.uas.dto.tahun_ajaran.request.TahunAjaranRequestDto;
import crud.siswa.uas.dto.tahun_ajaran.request.TahunAjaranUpdateRequestDto;
import crud.siswa.uas.dto.tahun_ajaran.response.TahunAjaranListResponse;
import crud.siswa.uas.dto.tahun_ajaran.response.TahunAjaranResponse;
import crud.siswa.uas.service.tahun_ajaran.TahunAjaranService;

@RestController
@RequestMapping("/api/tahun-ajaran")
@CrossOrigin(origins = "http://localhost:4200")
public class TahunAjaranController {

    @Autowired
    private TahunAjaranService tahunAjaranService;

    @GetMapping()
    public ResponseEntity<TahunAjaranListResponse> getTahunAjaran(
        @ModelAttribute TahunAjaranRequestDto tahunAjaranRequestDto,
        PageRequest pageRequest
    ){
        return tahunAjaranService.getTahunAjaran(tahunAjaranRequestDto, pageRequest.getPage());
    }

    @PostMapping("/create")
    public ResponseEntity<TahunAjaranResponse> createTahunAjaran(@RequestBody TahunAjaranCreateRequestDto tahunAjaranCreateRequestDto) {
        return tahunAjaranService.createTahunAjaran(tahunAjaranCreateRequestDto);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<TahunAjaranResponse> updateTahunAjaran(@PathVariable("id") Integer taId, @RequestBody TahunAjaranUpdateRequestDto tahunAjaranUpdateRequestDto) {
        return tahunAjaranService.updateTahunAjaran(taId, tahunAjaranUpdateRequestDto);
    }
    

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseBodyDto> deleteTahunAjaran(@PathVariable Integer id) {
        return tahunAjaranService.deleteTahunAjaran(id);
    }


}
