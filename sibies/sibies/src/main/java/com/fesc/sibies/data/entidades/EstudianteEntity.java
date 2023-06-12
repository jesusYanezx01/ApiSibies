package com.fesc.sibies.data.entidades;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name="estudiante")
public class EstudianteEntity implements Serializable {

    private static final long serialVersionUID= 1L;

    @Id
    @GeneratedValue
    private long id; 

    @Column(nullable = false)
    private String idEstudiante; 

    @Column(nullable = false)
    private String nombre; 

    @Column(nullable = false)
    private String documento; 

    @Column(nullable = false)
    private String sexo;  

    @Column(nullable = false)
    private String telefono; 


    @Column(nullable = false)
    private String correo; 

    @Column(nullable = false)
    private String programa; 

    @Column(nullable = false)
    private String estado; 

    @Column(nullable = false)
    private String observacion; 


    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getIdEstudiante() {
        return this.idEstudiante;
    }

    public void setIdEstudiante(String idEstudiante) {
        this.idEstudiante = idEstudiante;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDocumento() {
        return this.documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getSexo() {
        return this.sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getTelefono() {
        return this.telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return this.correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getPrograma() {
        return this.programa;
    }

    public void setPrograma(String programa) {
        this.programa = programa;
    }

    public String getEstado() {
        return this.estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getObservacion() {
        return this.observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    
  
    
}
