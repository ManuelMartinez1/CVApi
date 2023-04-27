package com.example.demo;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.io.Serializable;
@JsonSerialize
public class Alumno implements Serializable{

        @JsonProperty("id")
        private String id;
        @JsonProperty("nombre")
        private String nombre;
        @JsonProperty("direccion")
        private String direccion;
        @JsonProperty("email")
        private String email;
        @JsonProperty("profesion")
        private String profesion;
        @JsonProperty("telefono")
        private String telefono;
        @JsonProperty("sobreMi")
        private String sobreMi;
        @JsonProperty("habilidades")
        private String habilidades;
        @JsonProperty("educacion")
        private String educacion;
        @JsonProperty("fechagrad")
        private String fechagrad;


        public Alumno(){

        }
        public Alumno(String id, String nombre, String direccion, String email, String profesion, String telefono,
                      String sobreMi, String habilidades, String educacion, String fechagrad) {
            this.id = id;
            this.nombre = nombre;
            this.direccion = direccion;
            this.email = email;
            this.profesion = profesion;
            this.telefono = telefono;
            this.sobreMi = sobreMi;
            this.habilidades = habilidades;
            this.educacion = educacion;
            this.fechagrad = fechagrad;
        }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProfesion() {
        return profesion;
    }

    public void setProfesion(String profesion) {
        this.profesion = profesion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getSobreMi() {
        return sobreMi;
    }

    public void setSobreMi(String sobreMi) {
        this.sobreMi = sobreMi;
    }

    public String getHabilidades() {
        return habilidades;
    }

    public void setHabilidades(String habilidades) {
        this.habilidades = habilidades;
    }

    public String getEducacion() {
        return educacion;
    }

    public void setEducacion(String educacion) {
        this.educacion = educacion;
    }

    public String getFechagrad() {
        return fechagrad;
    }

    public void setFechagrad(String fechagrad) {
        this.fechagrad = fechagrad;
    }

    public String getId() {
            return this.id;
        }

        public String getDireccion(){return this.direccion;}

        public String getNombre(){return this.nombre;}
        public String getCorreo(){return this.email;}

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        public void setDireccion(String direccion){
            this.direccion = direccion;
        }
        public void setCorreo(String correo){
            this.email = correo;
        }


    }
