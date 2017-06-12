/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 *
 * @author JotaWind
 */
public class Cargo implements Serializable {

    private int id;
    private String nome;
    private double salario;
    private double liquido;
    private String requisitos;
    private int cargaMinima;
    private double descImposto;
    private List<Funcionario> funcionarios;

    public Cargo() {
    }

    public Cargo(String nome, double salario, String requisitos, int cargaMinima, double descImposto) {
        this.nome = nome;
        this.salario = salario;
        this.requisitos = requisitos;
        this.cargaMinima = cargaMinima;
        this.descImposto = descImposto;
        this.funcionarios = funcionarios;
    }

    public Cargo(int id, String nome, double salario, String requisitos, int cargaMinima, double descImposto) {
        this.id = id;
        this.nome = nome;
        this.salario = salario;
        this.requisitos = requisitos;
        this.cargaMinima = cargaMinima;
        this.descImposto = descImposto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Funcionario> getFuncionarios() {
        return funcionarios;
    }

    public void setFuncionarios(List<Funcionario> funcionarios) {
        this.funcionarios = funcionarios;
    }

    public double getDescImposto() {
        return descImposto;
    }

    public void setDescImposto(double descImposto) {
        this.descImposto = descImposto;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }
    public double getLiquido() {
        return liquido;
    }

    public void setLiquido(double liquido) {
        this.liquido = liquido;
    }

    public String getRequisitos() {
        return requisitos;
    }

    public void setRequisitos(String requisitos) {
        this.requisitos = requisitos;
    }

    public int getCargaMinima() {
        return cargaMinima;
    }

    public void setCargaMinima(int cargaMinima) {
        this.cargaMinima = cargaMinima;
    }

}
