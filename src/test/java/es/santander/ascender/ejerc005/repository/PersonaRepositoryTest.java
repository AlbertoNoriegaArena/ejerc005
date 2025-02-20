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

        // Listamos todas las Personas
        Iterable<Persona> valores = personaRepository.findAll();

        // Comprobar que valores no es nulo y que el Persona se guardó en la lista
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

        personaRepository.save(persona);

        Optional<Persona> resultado = personaRepository.findById(persona.getId());

        // Verificamos que el Persona existe
        assertFalse(resultado.isEmpty());
        assertEquals(persona.getId(), resultado.get().getId());
    }

    @Test
    public void testGuardarPersona() {

        Persona personaGuardada = personaRepository.save(persona);

        assertNotNull(personaGuardada.getId());
        assertEquals(persona.getNombre(), personaGuardada.getNombre());
        assertEquals(persona.getApellido(), personaGuardada.getApellido());
    }

    @Test
    public void testUpdatePersona() {

        Persona personaAModificar = personaRepository.save(persona);

        personaAModificar.setNombre("Ana Maria");
        personaAModificar.setApellido("Ortega");

        Persona personaActualizada = personaRepository.save(personaAModificar);

        assertEquals("Ana Maria", personaActualizada.getNombre());
        assertEquals("Ortega", personaActualizada.getApellido());
    }

    @Test
    public void testBorrarPersona() {

        Persona personaGuardada = personaRepository.save(persona);

        personaRepository.deleteById(personaGuardada.getId());

        Optional<Persona> personaEliminada = personaRepository.findById(personaGuardada.getId());
        assertFalse(personaEliminada.isPresent());
    }

}
