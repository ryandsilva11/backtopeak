package com.quest_api.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Quest")

public class Quest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String titulo;

    @Column
    private Long xp;

    @Column
    private String loot;

    @Column
    private Long lootQnt;

    @Column
    private boolean concluida;

    public Quest() {
    }

    public Quest(Long id, String titulo, Long xp, String loot, Long lootQnt, boolean concluida) {
        this.id = id;
        this.titulo = titulo;
        this.xp = xp;
        this.loot = loot;
        this.lootQnt = lootQnt;
        this.concluida = concluida;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Long getXp() {
        return xp;
    }

    public void setXp(Long xp) {
        this.xp = xp;
    }

    public String getLoot() {
        return loot;
    }

    public void setLoot(String loot) {
        this.loot = loot;
    }

    public Long getLootQnt() {
        return lootQnt;
    }

    public void setLootQnt(Long lootQnt) {
        this.lootQnt = lootQnt;
    }

    public boolean isConcluida() {
        return concluida;
    }

    public void setConcluida(boolean concluida) {
        this.concluida = concluida;
    }
}