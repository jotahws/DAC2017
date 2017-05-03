/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.util.Date;
import java.util.List;

/**
 *
 * @author JotaWind
 */
public class Cargo {
    
    private String nome;
    private double salario;
    private String requisitos;
    private Date horaMinima;
    private List<Funcionario> funcionarios;

    public Cargo() {
    }

    public List<Funcionario> getFuncionarios() {
        return funcionarios;
    }

    public void setFuncionarios(List<Funcionario> funcionarios) {
        this.funcionarios = funcionarios;
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

    public String getRequisitos() {
        return requisitos;
    }

    public void setRequisitos(String requisitos) {
        this.requisitos = requisitos;
    }

    public Date getHoraMinima() {
        return horaMinima;
    }

    public void setHoraMinima(Date horaMinima) {
        this.horaMinima = horaMinima;
    }
    
    
}