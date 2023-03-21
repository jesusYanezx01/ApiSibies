package com.fesc.apipartidos.data.entidades;

import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;


@Entity(name = "equipo")
public class EquipoEntity implements Serializable{

    private static final long serialVersionUID= 1L;
    
    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false, length = 20)
    private String nombre; 

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "equipoEntityLocal")  
    private List<PartidoEntity> partidosEntityLocalList = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "equipoEntityVisitante")  
    private List<PartidoEntity> partidosEntityVisitanteList = new ArrayList<>();


    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<PartidoEntity> getPartidosEntityLocalList() {
        return this.partidosEntityLocalList;
    }

    public void setPartidosEntityLocalList(List<PartidoEntity> partidosEntityLocalList) {
        this.partidosEntityLocalList = partidosEntityLocalList;
    }

    public List<PartidoEntity> getPartidosEntityVisitanteList() {
        return this.partidosEntityVisitanteList;
    }

    public void setPartidosEntityVisitanteList(List<PartidoEntity> partidosEntityVisitanteList) {
        this.partidosEntityVisitanteList = partidosEntityVisitanteList;
    }




}
