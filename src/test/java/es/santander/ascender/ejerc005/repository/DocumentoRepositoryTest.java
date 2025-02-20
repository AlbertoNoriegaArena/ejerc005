package es.santander.ascender.ejerc005.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import es.santander.ascender.ejerc005.model.Documento;

@SpringBootTest
public class DocumentoRepositoryTest {

    @Autowired
    private DocumentoRepository documentoRepository;

    private Documento documento;

    @BeforeEach
    public void setUp() {
        // Inicializamos un Documento con datos de prueba
        documento = new Documento(
                null,
                "Nombre de Documento",
                "txt",
                false,
                LocalDate.of(2020, 5, 3),
                20L
        );
    }

    @Test
    public void testList() {
        documentoRepository.save(documento);

        Iterable<Documento> documentos = documentoRepository.findAll();

        assertNotNull(documentos);
    }

    @Test
    public void testNoExiste() {
        
        Optional<Documento> resultado = documentoRepository.findById(999L);

        assertTrue(resultado.isEmpty());
    }

    @Test
    public void testLeerUno() {

        documentoRepository.save(documento);
        Optional<Documento> resultado = documentoRepository.findById(documento.getId());

        assertFalse(resultado.isEmpty());
        assertEquals(documento.getId(), resultado.get().getId());
    }

    @Test
    public void testGuardarDocumento() {

        Documento documentoGuardado = documentoRepository.save(documento);

        assertNotNull(documentoGuardado.getId());
        assertEquals(documento.getNombre(), documentoGuardado.getNombre());
        assertEquals(documento.getExtension(), documentoGuardado.getExtension());
        assertEquals(documento.isBorrado(), documentoGuardado.isBorrado());
        assertEquals(documento.getFecha_Creacion(), documentoGuardado.getFecha_Creacion());
        assertEquals(documento.getPersona_Id(), documentoGuardado.getPersona_Id());
    }

    @Test
    public void testUpdateDocumento() {

        Documento documentoAModificar = documentoRepository.save(documento);

        documentoAModificar.setNombre("Documento Modificado");
        documentoAModificar.setExtension("pdf");

        Documento documentoActualizado = documentoRepository.save(documentoAModificar);

        assertEquals("Documento Modificado", documentoActualizado.getNombre());
        assertEquals("pdf", documentoActualizado.getExtension());
    }

    @Test
    public void testBorrarDocumento() {

        Documento documentoGuardado = documentoRepository.save(documento);
        documentoRepository.deleteById(documentoGuardado.getId());

        Optional<Documento> documentoEliminado = documentoRepository.findById(documentoGuardado.getId());
        assertFalse(documentoEliminado.isPresent());
    }
}
     
