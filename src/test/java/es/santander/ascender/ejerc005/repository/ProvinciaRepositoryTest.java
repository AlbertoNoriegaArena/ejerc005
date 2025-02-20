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

import es.santander.ascender.ejerc005.model.Provincia;

@SpringBootTest
public class ProvinciaRepositoryTest {

    @Autowired
    private ProvinciaRepository provinciaRepository;

    private Provincia provincia;

    @BeforeEach
    public void setUp() {
        provincia = new Provincia(null, "Madrid");
    }

    @Test
    public void testList() {
        provinciaRepository.save(provincia);

        Iterable<Provincia> valores = provinciaRepository.findAll();

        assertNotNull(valores);
    }

    @Test
    public void testNoExiste() {

        Optional<Provincia> resultado = provinciaRepository.findById(45L);

        assertTrue(resultado.isEmpty());
    }

    @Test
    public void testLeerUno() {

        provinciaRepository.save(provincia);

        Optional<Provincia> resultado = provinciaRepository.findById(provincia.getId());

        // Verificamos que la Provincia existe
        assertFalse(resultado.isEmpty());
        assertEquals(provincia.getId(), resultado.get().getId());
    }

    @Test
    public void testGuardarProvincia() {

        Provincia provinciaGuardada = provinciaRepository.save(provincia);

        assertNotNull(provinciaGuardada.getId());
        assertEquals(provincia.getNombre(), provinciaGuardada.getNombre());
    }

    @Test
    public void testUpdateProvincia() {

        Provincia provinciaAModificar = provinciaRepository.save(provincia);

        provinciaAModificar.setNombre("Cádiz");

        Provincia provinciaActualizada = provinciaRepository.save(provinciaAModificar);

        assertEquals("Cádiz", provinciaActualizada.getNombre());
    }

    @Test
    public void testBorrarProvincia() {

        Provincia provinciaGuardada = provinciaRepository.save(provincia);

        provinciaRepository.deleteById(provinciaGuardada.getId());

        Optional<Provincia> provinciaEliminada = provinciaRepository.findById(provinciaGuardada.getId());
        assertFalse(provinciaEliminada.isPresent());
    }
}
