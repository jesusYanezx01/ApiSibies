package com.fesc.sibies.data.entidades;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;




@Entity(name = "usuario") 
@Table(indexes = {
    @Index(columnList = "idUsuario", name = "index_idUsuario", unique = true),
    @Index(columnList = "email", name = "index_email", unique = true),
    @Index(columnList = "username", name = "index_username", unique = true),
})

public class UsuarioEntity implements Serializable{
    

    private static final long serialVersionUID= 1L;

    @Id 
    @GeneratedValue 
    private long id; 

    @Column(nullable = false) 
    private String idUsuario;

    @Column(nullable = false, length = 50)
    private String nombre;

    @Column(nullable = false, length = 100)
    private String email;

    @Column(nullable = false, length = 100)
    private String username;

    @Column(nullable = false)
    private String passwordEncripatada;
 
    


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


  
    }



   





