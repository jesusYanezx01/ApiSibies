package com.fesc.apipartidos.shared;

import java.io.Serializable;
import java.util.Date;



public class PartidoDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private long id;
    private String idPartido;
    private Date creado; //Almacenar fecha de auditoria
    private Date fecha;
    private String username;
    private long equipoLocal;
    private long equipoVisitante;
    private UsuarioDto usuarioEntity; 
    private String golesLoscal;
    private String golesVisitante;
    private EquipoDto equipoEntityLocal;
    private EquipoDto equipoEntityVisitante;


    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getIdPartido() {
        return this.idPartido;
    }

    public void setIdPartido(String idPartido) {
        this.idPartido = idPartido;
    }

    public Date getCreado() {
        return this.creado;
    }

    public void setCreado(Date creado) {
        this.creado = creado;
    }

    public Date getFecha() {
        return this.fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public long getEquipoLocal() {
        return this.equipoLocal;
    }

    public void setEquipoLocal(long equipoLocal) {
        this.equipoLocal = equipoLocal;
    }

    public long getEquipoVisitante() {
        return this.equipoVisitante;
    }

    public void setEquipoVisitante(long equipoVisitante) {
        this.equipoVisitante = equipoVisitante;
    }

    public UsuarioDto getUsuarioEntity() {
        return this.usuarioEntity;
    }

    public void setUsuarioEntity(UsuarioDto usuarioEntity) {
        this.usuarioEntity = usuarioEntity;
    }

    public String getGolesLoscal() {
        return this.golesLoscal;
    }

    public void setGolesLoscal(String golesLoscal) {
        this.golesLoscal = golesLoscal;
    }

    public String getGolesVisitante() {
        return this.golesVisitante;
    }

    public void setGolesVisitante(String golesVisitante) {
        this.golesVisitante = golesVisitante;
    }

    public EquipoDto getEquipoEntityLocal() {
        return this.equipoEntityLocal;
    }

    public void setEquipoEntityLocal(EquipoDto equipoEntityLocal) {
        this.equipoEntityLocal = equipoEntityLocal;
    }

    public EquipoDto getEquipoEntityVisitante() {
        return this.equipoEntityVisitante;
    }

    public void setEquipoEntityVisitante(EquipoDto equipoEntityVisitante) {
        this.equipoEntityVisitante = equipoEntityVisitante;
    }

    
}
