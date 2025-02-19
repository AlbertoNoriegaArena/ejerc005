package es.santander.ascender.ejerc005.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.santander.ascender.ejerc005.model.Provincia;
import es.santander.ascender.ejerc005.repository.ProvinciaRepository;
import jakarta.annotation.PostConstruct;

@Transactional
@Service
public class ProvinciaService {

    @Autowired
    private ProvinciaRepository repository;

    // Crear registros
    @PostConstruct
    public void init() {
        // Solo crear si no existen registros para evitar duplicados
        if (repository.count() == 0) {
            Provincia provincia1 = new Provincia(null, "Cantabria");
            Provincia provincia2 = new Provincia(null, "Asturias");
            repository.save(provincia1);
            repository.save(provincia2);
        }
    }

    // CREATE
    public Provincia create(Provincia provincia) {

        if (provincia.getId() != null) {
            throw new CRUDSecurityException("Han tratado de modificar un registro provincia",
                    CRUDOperation.CREATE,
                    provincia.getId());
        } else {
            return repository.save(provincia);
        }

    }

    // Leer uno
    @Transactional(readOnly = true)
    public Provincia read(Long id) {
        return repository.findById(id).orElse(null);
    }

    // Listar
    @Transactional(readOnly = true)
    public List<Provincia> list() {
        return repository.findAll();
    }

    // UPDATE
    public Provincia update(Provincia provincia) {
        if (provincia.getId() == null) {
            throw new CRUDSecurityException("Han tratado de crear un registro provincia utilizando modificar",
                    CRUDOperation.UPDATE,
                    null);
        }
        return repository.save(provincia);
    }

    // DELETE
    public void delete(Long id) {
        repository.deleteById(id);
    }

}
