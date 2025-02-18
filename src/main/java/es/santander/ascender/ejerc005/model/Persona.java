package es.santander.ascender.ejerc005.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "Persona")
public class Persona {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    private  String nombre;

    private String apellido;

    private Long provincia_Id;

    public Persona() {}

    public Persona(Long id, @NotNull String nombre, String apellido, Long provincia_Id) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.provincia_Id = provincia_Id;
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

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Long getProvincia_Id() {
        return provincia_Id;
    }

    public void setProvincia_Id(Long provincia_Id) {
        this.provincia_Id = provincia_Id;
    }
    
    

}
