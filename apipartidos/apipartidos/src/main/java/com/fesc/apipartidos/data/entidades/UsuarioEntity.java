package com.fesc.apipartidos.data.entidades;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.OneToMany;
import javax.persistence.Table;




@Entity(name = "usuario") //Especificacion para que la entidad sea una tabla
@Table(indexes = {
    @Index(columnList = "idUsuario", name = "index_idUsuario", unique = true),
    @Index(columnList = "email", name = "index_email", unique = true),
    @Index(columnList = "username", name = "index_username", unique = true),
})

public class UsuarioEntity implements Serializable{
    //Las entidades son nuestras tablas
    //Tiene que ser serializado

    private static final long serialVersionUID= 1L;

    @Id //Anotacion para especificar que es un id
    @GeneratedValue //Anotacion para especificar que es Autoincrementable
    private long id; 

    @Column(nullable = false) //Anotacion para especificar una columna en la tabla y que no sea null
    private String idUsuario;

    @Column(nullable = false, length = 50) //length para especificar la cantidad de caracteres
    private String nombre;

    @Column(nullable = false, length = 100)
    private String email;

    @Column(nullable = false, length = 100)
    private String username;

    @Column(nullable = false)
    private String passwordEncripatada;
 
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuarioEntity")  //Relacion de uno a muchos (estando en casacada cuando quiera eliminar algo se elimina todo y sera mapeado se llama igual que la entidad)
    private List<PartidoEntity> partidosEntityList = new ArrayList<>();


    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getIdUsuario() {
        return this.idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswordEncripatada() {
        return this.passwordEncripatada;
    }

    public void setPasswordEncripatada(String passwordEncripatada) {
        this.passwordEncripatada = passwordEncripatada;
    }


    public List<PartidoEntity> getPartidosEntityList() {
        return this.partidosEntityList;
    }

    public void setPartidosEntityList(List<PartidoEntity> partidosEntityList) {
        this.partidosEntityList = partidosEntityList;
    }



   




}
