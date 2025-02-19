package es.santander.ascender.ejerc005.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.santander.ascender.ejerc005.model.Persona;
import es.santander.ascender.ejerc005.repository.PersonaRepository;
import jakarta.annotation.PostConstruct;

@Transactional
@Service
public class PersonaService {

    @Autowired
    private PersonaRepository repository;

    // Crear registros
    @PostConstruct
    public void init() {

        if (repository.count() == 0) {
            Persona persona1 = new Persona(null, "Juan", "Gomez", 30l);
            Persona persona2 = new Persona(null, "Maria", "Perez", 21l);
            repository.save(persona1);
            repository.save(persona2);
        }
    }

    // CREATE
    public Persona create(Persona persona) {

        if (persona.getId() != null) {
            throw new CRUDSecurityException("Han tratado de modificar un registro persona",
                    CRUDOperation.CREATE,
                    persona.getId());
        } else {
            return repository.save(persona);
        }

    }

    // Leer uno
    @Transactional(readOnly = true)
    public Persona read(Long id) {
        return repository.findById(id).orElse(null);
    }

    // Listar
    @Transactional(readOnly = true)
    public List<Persona> list() {
        return repository.findAll();
    }

    // UPDATE
    public Persona update(Persona persona) {
        if (persona.getId() == null) {
            throw new CRUDSecurityException("Han tratado de crear un registro persona utilizando modificar",
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
