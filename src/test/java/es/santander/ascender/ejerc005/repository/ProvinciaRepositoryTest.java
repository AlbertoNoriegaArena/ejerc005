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
        Provincia provinciaAGuardar = new Provincia(null, "Barcelona");
        provinciaRepository.save(provinciaAGuardar);

        Optional<Provincia> resultado = provinciaRepository.findById(provinciaAGuardar.getId());

        // Verificamos que la Provincia existe
        assertFalse(resultado.isEmpty());
        assertEquals(provinciaAGuardar.getId(), resultado.get().getId());
    }

    @Test
    public void testGuardarProvincia() {
        Provincia provinciaAGuardar = new Provincia(null, "Valencia");

        Provincia provinciaGuardada = provinciaRepository.save(provinciaAGuardar);

        assertNotNull(provinciaGuardada.getId());
        assertEquals(provinciaAGuardar.getNombre(), provinciaGuardada.getNombre());
    }

    @Test
    public void testUpdateProvincia() {
        Provincia provinciaAGuardar = new Provincia(null, "Sevilla");
        Provincia provinciaAModificar = provinciaRepository.save(provinciaAGuardar);

        provinciaAModificar.setNombre("Cádiz");

        Provincia provinciaActualizada = provinciaRepository.save(provinciaAModificar);

        assertEquals("Cádiz", provinciaActualizada.getNombre());
    }

    @Test
    public void testBorrarProvincia() {
        Provincia provinciaAGuardar = new Provincia(null, "Murcia");
        Provincia provinciaGuardada = provinciaRepository.save(provinciaAGuardar);

        provinciaRepository.deleteById(provinciaGuardada.getId());

        Optional<Provincia> provinciaEliminada = provinciaRepository.findById(provinciaGuardada.getId());
        assertFalse(provinciaEliminada.isPresent());
    }
}
