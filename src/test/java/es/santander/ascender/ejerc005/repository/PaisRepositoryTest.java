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

import es.santander.ascender.ejerc005.model.Pais;

@SpringBootTest
public class PaisRepositoryTest {

    @Autowired
    private PaisRepository paisRepository;

    private Pais pais;

    @BeforeEach
    public void setUp() {
        pais = new Pais(null, "España", "País de Europa", "Europa");
    }

    @Test
    public void testList() {
        paisRepository.save(pais);

        Iterable<Pais> valores = paisRepository.findAll();

        assertNotNull(valores);
    }

    @Test
    public void testNoExiste() {
        
        Optional<Pais> resultado = paisRepository.findById(45L);

        assertTrue(resultado.isEmpty());
    }

    @Test
    public void testLeerUno() {
        Pais paisAGuardar = new Pais(null, "Francia", "País de Europa", "Europa");
        paisRepository.save(paisAGuardar);

        Optional<Pais> resultado = paisRepository.findById(paisAGuardar.getId());

        assertFalse(resultado.isEmpty());
        assertEquals(paisAGuardar.getId(), resultado.get().getId());
    }

    @Test
    public void testGuardarPais() {
        Pais paisAGuardar = new Pais(null, "Italia", "País mediterráneo", "Europa");

        Pais paisGuardado = paisRepository.save(paisAGuardar);

        assertNotNull(paisGuardado.getId());
        assertEquals(paisAGuardar.getNombre(), paisGuardado.getNombre());
        assertEquals(paisAGuardar.getDescripcion(), paisGuardado.getDescripcion());
        assertEquals(paisAGuardar.getContinente(), paisGuardado.getContinente());
    }

    @Test
    public void testUpdatePais() {
        Pais paisAGuardar = new Pais(null, "Alemania", "País de Europa", "Europa");
        Pais paisAModificar = paisRepository.save(paisAGuardar);

        paisAModificar.setNombre("Belgica");
        paisAModificar.setDescripcion("País belga");

        Pais paisActualizado = paisRepository.save(paisAModificar);

        assertEquals("Belgica", paisActualizado.getNombre());
        assertEquals("País belga", paisActualizado.getDescripcion());
    }

    @Test
    public void testBorrarPais() {
        Pais paisAGuardar = new Pais(null, "Portugal", "País de Europa", "Europa");
        Pais paisGuardado = paisRepository.save(paisAGuardar);

        paisRepository.deleteById(paisGuardado.getId());

        Optional<Pais> paisEliminado = paisRepository.findById(paisGuardado.getId());
        assertFalse(paisEliminado.isPresent());
    }

}
