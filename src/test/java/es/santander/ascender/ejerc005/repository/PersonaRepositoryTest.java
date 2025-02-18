package es.santander.ascender.ejerc005.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import es.santander.ascender.ejerc005.model.Persona;

@SpringBootTest
public class PersonaRepositoryTest {

    @Autowired
    private PersonaRepository personaRepository;

    private Persona persona;

    @BeforeEach
    public void setUp() {

        persona = new Persona(null, "Juan", "Perez", 20l);
    }

    @Test
    public void testList() {

        personaRepository.save(persona);

        // Listamos todos los Personaes
        Iterable<Persona> valores = personaRepository.findAll();

        // Comprobar que valore no es nulo y que el Persona se guardó en la lista
        assertNotNull(valores);
    }

    @Test
    public void testNoExiste() {
        // ID que no exista
        Optional<Persona> resultado = personaRepository.findById(45L);

        assertTrue(resultado.isEmpty());
    }

    @Test
    public void testLeerUno() {

        Persona personaAGuardar = new Persona(null, "Juan", "Perez", 20l);
        personaRepository.save(personaAGuardar);

        Optional<Persona> resultado = personaRepository.findById(personaAGuardar.getId());

        // Verificamos que el Persona existe
        assertFalse(resultado.isEmpty());
        assertEquals(personaAGuardar.getId(), resultado.get().getId());
    }

}
