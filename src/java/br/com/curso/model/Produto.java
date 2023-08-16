/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.curso.model;

/**
 *
 * @author maico
 */
public class Produto {
    
    private int idProduto;
    private String nomeProduto;
    private double ultimoPrecoPago;
    private double saldoAtual;
    private TipoProduto tipoproduto;
    private UnidadeMedida unidademedida;

    public Produto() {
        this.idProduto=0;
        this.nomeProduto="";
        this.ultimoPrecoPago=0;
        this.saldoAtual=0;
        this.tipoproduto = new TipoProduto();
        this.unidademedida = new UnidadeMedida();
    }

    public Produto(int idProduto, String nomeProduto, double ultimoPrecoPago, double saldoAtual, TipoProduto tipoproduto, UnidadeMedida unidademedida) {
        this.idProduto = idProduto;
        this.nomeProduto = nomeProduto;
        this.ultimoPrecoPago = ultimoPrecoPago;
        this.saldoAtual = saldoAtual;
        this.tipoproduto = tipoproduto;
        this.unidademedida = unidademedida;
    }

    public Produto(int idProduto, String nomeProduto) {
        this.idProduto = idProduto;
        this.nomeProduto = nomeProduto;
    }
    
    public int getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(int idProduto) {
        this.idProduto = idProduto;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public double getUltimoPrecoPago() {
        return ultimoPrecoPago;
    }

    public void setUltimoPrecoPago(double ultimoPrecoPago) {
        this.ultimoPrecoPago = ultimoPrecoPago;
    }

    public double getSaldoAtual() {
        return saldoAtual;
    }

    public void setSaldoAtual(double saldoAtual) {
        this.saldoAtual = saldoAtual;
    }

    public TipoProduto getTipoproduto() {
        return tipoproduto;
    }

    public void setTipoproduto(TipoProduto tipoproduto) {
        this.tipoproduto = tipoproduto;
    }

    public UnidadeMedida getUnidademedida() {
        return unidademedida;
    }

    public void setUnidademedida(UnidadeMedida unidademedida) {
        this.unidademedida = unidademedida;
    }


    

   
    
    
    
}
