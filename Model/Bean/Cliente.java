/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Bean;

/**
 *
 * @author Leandro Melo
 */
public class Cliente {
    
    private int     id;             //Identificador do Cliente
    private String  nome;           //Nome do Cliente
    private String  endereco;       //Enderaço do Cliente
    private String  telefone;       //Telefone do Cliente
    private String  celular;        //Celular do Cliente
    private String  dtNasc;         //Data de Nascimento do Cliente
    private String  rg;             //Identidade do Cliente
    private String  cpf;            //CPF do Cliente
    private String  email;          //e-mail do Cliente
    private String  DELET;          //Marcação se o registor está deletado no sistema
    
    public int getId() {
        return id;
    }
    public void setID(int id) {
        this.id = id;
    }
    
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }
    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTelefone() {
        return telefone;
    }
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCelular() {
        return celular;
    }
    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getDtNasc() {
        return dtNasc;
    }
    public void setDtNasc(String dtNasc) {
        this.dtNasc = dtNasc;
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

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getDELET() {
        return DELET;
    }
    public void setDELET(String DELET) {
        this.DELET = DELET;
    }
}   
