package com.lvcaspacifico.folhadepagamento.model;

import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Empregado {

    @Id @GeneratedValue
    private Long id;

    private String primeiroNome;
    private String sobrenome;

    private String nome;
    private String cargo;

    public Empregado() {}

    public Empregado(String primeiroNome, String sobrenome, String cargo){
        this.primeiroNome = primeiroNome;
        this.sobrenome = sobrenome;
        this.cargo = cargo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return this.primeiroNome != null ? this.primeiroNome + " " + this.sobrenome : this.nome;
    }

    public void setNome(String nome) {
        String[] parts = nome.split(" ");
        this.primeiroNome = parts[0];
        this.sobrenome = parts[1];
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    
    public String getPrimeiroNome() {
        return this.primeiroNome != null ? this.primeiroNome : this.nome.split(" ")[0];
    }

    public void setPrimeiroNome(String primeiroNome) {
        this.primeiroNome = primeiroNome;
    }

    public String getSobrenome() {
        return this.sobrenome != null ? this.sobrenome : this.nome.split(" ")[0];
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
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
        return Objects.equals(this.id, empregado.id)
             && Objects.equals(this.primeiroNome, empregado.primeiroNome)
             && Objects.equals(this.sobrenome, empregado.sobrenome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.primeiroNome, this.sobrenome, this.cargo);
    }

    @Override
    public String toString() {
        return "Empregado{" + "id=" + this.id + ", nome='" + this.primeiroNome + '\'' + ", sobrenome='" + this.sobrenome + '\'' + ", cargo='" + this.cargo + '\'' + '}';
    }

    
}
