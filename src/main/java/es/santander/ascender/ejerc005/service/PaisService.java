package es.santander.ascender.ejerc005.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.santander.ascender.ejerc005.model.Pais;
import es.santander.ascender.ejerc005.repository.PaisRepository;
import jakarta.annotation.PostConstruct;

@Transactional
@Service
public class PaisService {

    @Autowired
    private PaisRepository repository;

    // Crear registros
    @PostConstruct
    public void init() {

        if (repository.count() == 0) {
            Pais pais1 = new Pais(null, "España", "El mejor", "Europa");
            Pais pais2 = new Pais(null, "Otro", "El otro", "Asia");
            repository.save(pais1);
            repository.save(pais2);
        }
    }

    // CREATE
    public Pais create(Pais pais) {

        if (pais.getId() != null) {
            throw new CRUDSecurityException("Han tratado de modificar un registro pais",
                    CRUDOperation.CREATE,
                    pais.getId());
        } else {
            return repository.save(pais);
        }

    }

    // Leer uno
    @Transactional(readOnly = true)
    public Pais read(Long id) {
        return repository.findById(id).orElse(null);
    }

    // Listar
    @Transactional(readOnly = true)
    public List<Pais> list() {
        return repository.findAll();
    }

    // UPDATE
    public Pais update(Pais persona) {
        if (persona.getId() == null) {
            throw new CRUDSecurityException("Han tratado de crear un registro país utilizando modificar",
                    CRUDOperation.UPDATE,
                    null);
        }
        return repository.save(persona);
    }

    // DELETE
    public void delete(Long id) {
        repository.deleteById(id);
    }

}
