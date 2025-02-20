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

        paisRepository.save(pais);

        Optional<Pais> resultado = paisRepository.findById(pais.getId());

        assertFalse(resultado.isEmpty());
        assertEquals(pais.getId(), resultado.get().getId());
    }

    @Test
    public void testGuardarPais() {

        Pais paisGuardado = paisRepository.save(pais);

        assertNotNull(paisGuardado.getId());
        assertEquals(pais.getNombre(), paisGuardado.getNombre());
        assertEquals(pais.getDescripcion(), paisGuardado.getDescripcion());
        assertEquals(pais.getContinente(), paisGuardado.getContinente());
    }

    @Test
    public void testUpdatePais() {

        Pais paisAModificar = paisRepository.save(pais);

        paisAModificar.setNombre("Belgica");
        paisAModificar.setDescripcion("País belga");

        Pais paisActualizado = paisRepository.save(paisAModificar);

        assertEquals("Belgica", paisActualizado.getNombre());
        assertEquals("País belga", paisActualizado.getDescripcion());
    }

    @Test
    public void testBorrarPais() {

        Pais paisGuardado = paisRepository.save(pais);

        paisRepository.deleteById(paisGuardado.getId());

        Optional<Pais> paisEliminado = paisRepository.findById(paisGuardado.getId());
        assertFalse(paisEliminado.isPresent());
    }

}
