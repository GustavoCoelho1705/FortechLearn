package com.fortech.model;

import javax.persistence.*;

@Entity
@Table(name = "Conteudos")
public class Conteudos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;
    
    @Column(name = "Título", length = 200)
    private String titulo;
    
    @Column(name = "Conteúdo", length = 4000)
    private String conteudo;
    
    @Column(name = "Módulo", length = 150)
    private String modulo;
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getTitulo() {
        return titulo;
    }
    
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    
    public String getConteudo() {
        return conteudo;
    }
    
    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }
    
    public String getModulo() {
        return modulo;
    }
    
    public void setModulo(String modulo) {
        this.modulo = modulo;
    }
}