/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.curso.model;

/**
 *
 * @author maico
 */
public class MovimentoEstoque {
    private int idMovimento;
    private String entradaSaida;
    private String documento;
    private String data;
    private double quantidade;
    private double valorMovimento;
    private Produto produto;
    private Funcionario funcionario;
    private TipoMovimento tipomovimento;

    public MovimentoEstoque() {
        this.idMovimento=0;
        this.entradaSaida="";
        this.documento="";
        this.data="";
        this.quantidade=0;
        this.valorMovimento=0;
        this.produto = new Produto();
        this.funcionario = new Funcionario();
        this.tipomovimento = new TipoMovimento(); 
    }

    public MovimentoEstoque(int idMovimento, String entradaSaida, String documento, String data, double quantidade, double valorMovimento, Produto produto, Funcionario funcionario, TipoMovimento tipomovimento) {
        this.idMovimento = idMovimento;
        this.entradaSaida = entradaSaida;
        this.documento = documento;
        this.data = data;
        this.quantidade = quantidade;
        this.valorMovimento = valorMovimento;
        this.produto = produto;
        this.funcionario = funcionario;
        this.tipomovimento = tipomovimento;
    }

    public int getIdMovimento() {
        return idMovimento;
    }

    public void setIdMovimento(int idMovimento) {
        this.idMovimento = idMovimento;
    }

    public String getEntradaSaida() {
        return entradaSaida;
    }

    public void setEntradaSaida(String entradaSaida) {
        this.entradaSaida = entradaSaida;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(double quantidade) {
        this.quantidade = quantidade;
    }

    public double getValorMovimento() {
        return valorMovimento;
    }

    public void setValorMovimento(double valorMovimento) {
        this.valorMovimento = valorMovimento;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public TipoMovimento getTipomovimento() {
        return tipomovimento;
    }

    public void setTipomovimento(TipoMovimento tipomovimento) {
        this.tipomovimento = tipomovimento;
    }

  
    
    
    
}
