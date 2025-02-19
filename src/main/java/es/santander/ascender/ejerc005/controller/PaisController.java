package es.santander.ascender.ejerc005.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.santander.ascender.ejerc005.model.Pais;
import es.santander.ascender.ejerc005.service.PaisService;

@RestController
@RequestMapping("/api/pais")
public class PaisController {

    @Autowired
    private PaisService paisService;

    @PostMapping
    public Pais create(@RequestBody Pais pais) {
        return paisService.create(pais);
    }

    @GetMapping("/{id}")
    public Pais read(@PathVariable("id") Long id) {
        return paisService.read(id);
    }

    @GetMapping
    public List<Pais> list() {
        return paisService.list();
    }

    @PutMapping("/{id}")
    public Pais update(@RequestBody Pais pais) {
        return paisService.update(pais);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        paisService.delete(id);
    }


}
