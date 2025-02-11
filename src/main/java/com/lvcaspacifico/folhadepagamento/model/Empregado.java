package com.lvcaspacifico.folhadepagamento.model;

import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Empregado {

    @Id @GeneratedValue
    private Long id;

    private String nome;
    private String cargo;

    public Empregado() {}

    public Empregado(String nome, String cargo){
        this.nome = nome;
        this.cargo = cargo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }


    @Override
    public boolean equals(Object o) {

        // se o for o objeto, é igual
        if(this == o) return true;

        // se o não for instância da classe, não é igual
        if(!(o instanceof Empregado)) return false;

        // casting de o para tipo da classe
        Empregado empregado = (Empregado) o;
        // se o id e nome do objeto é igual ao da instância, é igual  
        return Objects.equals(this.id, empregado.id) && Objects.equals(this.nome, empregado.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.nome, this.cargo);
    }

    @Override
    public String toString() {
        return "Empregado{" + "id=" + this.id + ", nome='" + this.nome + '\'' + ", cargo='" + this.cargo + '\'' + '}';
    }
    
}
