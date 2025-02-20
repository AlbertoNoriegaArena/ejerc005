package es.santander.ascender.ejerc005.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.santander.ascender.ejerc005.model.Documento;
import es.santander.ascender.ejerc005.repository.DocumentoRepository;


@Transactional
@Service
public class DocumentoService {

    @Autowired
    private DocumentoRepository repository;

    // CREATE
    public Documento create(Documento documento) {

        if (documento.getId() != null) {
            throw new CRUDSecurityException("Han tratado de modificar un registro documento",
                    CRUDOperation.CREATE,
                    documento.getId());
        } else {
            return repository.save(documento);
        }

    }

    // Leer uno
    @Transactional(readOnly = true)
    public Documento read(Long id) {
        return repository.findById(id).orElse(null);
    }

    // Listar
    @Transactional(readOnly = true)
    public List<Documento> list() {
        return repository.findAll();
    }

    // UPDATE
    public Documento update(Documento documento) {
        if (documento.getId() == null) {
            throw new CRUDSecurityException("Han tratado de crear un registro documento utilizando modificar",
                    CRUDOperation.UPDATE,
                    null);
        }
        return repository.save(documento);
    }

    // DELETE
    public void delete(Long id) {
        repository.deleteById(id);
    }

}
