/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author JotaWind
 */
public class Funcionario implements Serializable {

    private int id;
    private String nome;
    private String rg;
    private String cpf;
    private String celular;
    private String email;
    private Endereco endereco;
    private Cargo cargo;
    private Departamento departamento;
    private String perfil;
    private String senha;

    public Funcionario(int id, String nome, String rg, String cpf, String celular, String email, Endereco endereco, Cargo cargo, Departamento departamento, String perfil, String senha) {
        this.id = id;
        this.nome = nome;
        this.rg = rg;
        this.cpf = cpf;
        this.celular = celular;
        this.email = email;
        this.endereco = endereco;
        this.cargo = cargo;
        this.departamento = departamento;
        this.perfil = perfil;
        this.senha = senha;
    }

    public Funcionario(String nome, String rg, String cpf, String celular, String email, Endereco endereco, Cargo cargo, Departamento departamento, String perfil, String senha) {
        this.nome = nome;
        this.rg = rg;
        this.cpf = cpf;
        this.celular = celular;
        this.email = email;
        this.endereco = endereco;
        this.cargo = cargo;
        this.departamento = departamento;
        this.perfil = perfil;
        this.senha = senha;
    }

    public Funcionario() {
    }

    public Funcionario(int id, String nome, String cpf, String email, Cargo cargo, Departamento departamento) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.cargo = cargo;
        this.departamento = departamento;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPerfil() {
        return perfil;
    }

    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String criptografa(String senha) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        
        MessageDigest algorithm = MessageDigest.getInstance("SHA-256");
        byte messageDigest[] = algorithm.digest(senha.getBytes("UTF-8"));

        StringBuilder hexString = new StringBuilder();
        for (byte b : messageDigest) {
            hexString.append(String.format("%02X", 0xFF & b));
        }
        senha = hexString.toString();
        
        return senha;
    }
    
}