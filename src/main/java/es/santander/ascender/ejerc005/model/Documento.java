package es.santander.ascender.ejerc005.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "Documento")
public class Documento {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    //Campo tipo TEXT para almacenar descripciones largas
    @Lob
    @NotNull(message = "El campo nombre no puede ser nulo")
    @NotBlank(message = "El campo nombre no puede estar vacio")
    @Size(max = 256, message = "El nombre no puede superar los 256 caracteres")
    private  String nombre;

    @Size(max = 15, message = "La extensión no puede superar los 15 caracteres")
    private String extension;
    
    private boolean borrado;

    private LocalDate fecha_Creacion;

    @NotNull
    private Long persona_Id;

    public Documento() {}

    public Documento(Long id, @NotNull @NotBlank @Max(256) String nombre, @Max(15) String extension,
            @NotNull boolean borrado, LocalDate fecha_Creacion, @NotNull Long persona_Id) {
        this.id = id;
        this.nombre = nombre;
        this.extension = extension;
        this.borrado = borrado;
        this.fecha_Creacion = fecha_Creacion;
        this.persona_Id = persona_Id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public boolean isBorrado() {
        return borrado;
    }

    public void setBorrado(boolean borrado) {
        this.borrado = borrado;
    }

    public LocalDate getFecha_Creacion() {
        return fecha_Creacion;
    }

    public void setFecha_Creacion(LocalDate fecha_Creacion) {
        this.fecha_Creacion = fecha_Creacion;
    }

    public Long getPersona_Id() {
        return persona_Id;
    }

    public void setPersona_Id(Long persona_Id) {
        this.persona_Id = persona_Id;
    }

    

}
