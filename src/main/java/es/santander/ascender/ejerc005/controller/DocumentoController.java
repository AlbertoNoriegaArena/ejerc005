package es.santander.ascender.ejerc005.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.santander.ascender.ejerc005.model.Documento;
import es.santander.ascender.ejerc005.service.DocumentoService;

@RestController
@RequestMapping("/api/documento")
public class DocumentoController {

    @Autowired
    private DocumentoService documentoService;

    @PostMapping
    public Documento create(@RequestBody Documento documento) {
        return documentoService.create(documento);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Documento> read(@PathVariable("id") Long id) {

        Documento documento = documentoService.read(id);

        if (id == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok().body(documento);
        }

    }

    @GetMapping
    public List<Documento> list() {
        return documentoService.list();
    }

    @PutMapping("/{id}")
    public Documento update(@RequestBody Documento documento) {
        return documentoService.update(documento);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        documentoService.delete(id);
    }

}
