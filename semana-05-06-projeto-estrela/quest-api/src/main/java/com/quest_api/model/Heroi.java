package com.quest_api.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Heroi")
public class Heroi {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String nome;

    @Column
    private String classe;

    @Column
    private Long nivel;

    @Column
    private Long xp;

    public Heroi() {
    }

    public Heroi(Long id, String nome, String classe, Long nivel, Long xp) {
        this.id = id;
        this.nome = nome;
        this.classe = classe;
        this.nivel = nivel;
        this.xp = xp;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getclasse() {
        return this.classe;
    }

    public void setClasse(String classe) {
        this.classe = classe;
    }

    public Long getNivel() {
        return this.nivel;
    }

    public Long setNivel(Long nivel) {
        this.nivel = nivel;
    }

    public Long getXp() {
        return this.xp;
    }

    public void setXp(Long xp) {
        this.xp = xp;
    }
}
