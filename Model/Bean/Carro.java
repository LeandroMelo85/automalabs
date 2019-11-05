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
public class Carro {
    
    private int id;                 //identificação do carro
    private int idCliente;
    private String fabricante;      //fabricante do carro
    private String modelo;          //modelo do carro
    private String cor;             //cor do carro
    private String motor;           //Potência do motor
    private String tpCombustivel;   //Tipo de combustível do carro
    private String dtCadastro;      //Tata de cadastro do carro no sistema
    private String kmAtual;         //Kilometragem atual do carro ao alugar
    private String alugado;         //Informar se o carro encontra-se alugado
    private String dtAluguel;
    private String dtDevolucao;
    

    public int getId() {            //Inicio do bloco dos Gets e Setters
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public int getIdCliente() {
        return idCliente;
    }
    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getFabricante() {
        return fabricante;
    }
    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }

    public String getModelo() {
        return modelo;
    }
    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getCor() {
        return cor;
    }
    public void setCor(String cor) {
        this.cor = cor;
    }

    public String getMotor() {
        return motor;
    }
    public void setMotor(String motor) {
        this.motor = motor;
    }

    public String getTpCombustivel() {
        return tpCombustivel;
    }
    public void setTpCombustivel(String tpCombustivel) {
        this.tpCombustivel = tpCombustivel;
    }

    public String getDtCadastro() {
        return dtCadastro;
    }
    public void setDtCadastro(String dtCadastro) {
        this.dtCadastro = dtCadastro;
    }
    
    public String getKmAtual() {
        return kmAtual;
    }
    public void setKmAtual(String kmAtual) {
        this.kmAtual = kmAtual;
    }
    public String getAlugado() {
        return alugado;
    }
    public void setAlugado(String alugado) {
        this.alugado = alugado;
    }

    public String getDtAluguel() {
        return dtAluguel;
    }
    public void setDtAluguel(String dtAluguel) {
        this.dtAluguel = dtAluguel;
    }

    public String getDtDevolucao() {
        return dtDevolucao;
    }
    public void setDtDevolucao(String dtDevolucao) {
        this.dtDevolucao = dtDevolucao;
    }
                                    //Fim do bloco dos Gets e Setters 
    
}
