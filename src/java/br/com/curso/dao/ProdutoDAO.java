/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.curso.dao;

import br.com.curso.model.Produto;
import br.com.curso.model.TipoProduto;
import br.com.curso.model.UnidadeMedida;
import br.com.curso.utils.SingleConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author maico
 */
public class ProdutoDAO implements GenericDAO{

     private Connection conexao;
    
    public ProdutoDAO() throws Exception{
        conexao = SingleConnection.getConnection();
    }
    
    @Override
    public boolean cadastrar(Object objeto) {
        Produto oProduto = (Produto)objeto;
        boolean retorno=false;
        if(oProduto.getIdProduto()==0){
            retorno = this.inserir(oProduto);
        }else{
            retorno = this.alterar(oProduto);
        }
        return retorno;
    }

    @Override
    public boolean inserir(Object objeto) {
        Produto oProduto = (Produto) objeto;
        PreparedStatement stmt = null;
        String sql = "insert into produto ( nomeProduto, ultimoPrecoPago, saldoAtual, unidadeMedida, tipoProduto ) values (?,?,?,?,?)";
        try{
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, oProduto.getNomeProduto());
            stmt.setDouble(2, oProduto.getUltimoPrecoPago());
            stmt.setDouble(3, oProduto.getSaldoAtual());
            stmt.setInt(4, oProduto.getUnidademedida().getIdUnidadeMedida());
            stmt.setInt(5, oProduto.getTipoproduto().getIdTipoProduto());
            stmt.execute();
            conexao.commit();
            return true;
        }catch (Exception ex){
            try{
                System.out.println("Problemas ao cadastrar a Produto ! Erro:"+ex.getMessage());
                ex.printStackTrace();
                conexao.rollback();
            }catch (SQLException e){
                System.out.println("Erro:"+e.getMessage());
                e.printStackTrace();
            }
            return false;
        }
    }

    @Override
    public boolean alterar(Object objeto) {
                Produto oProduto = (Produto) objeto;
        PreparedStatement stmt = null;
        String sql = "update produto set nomeProduto=?, ultimoPrecoPago=?, saldoAtual=?, unidadeMedida=?, tipoProduto=? where idProduto=?";
        try{
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, oProduto.getNomeProduto());
            stmt.setDouble(2, oProduto.getUltimoPrecoPago());
            stmt.setDouble(3, oProduto.getSaldoAtual());
            stmt.setInt(4, oProduto.getUnidademedida().getIdUnidadeMedida());
            stmt.setInt(5, oProduto.getTipoproduto().getIdTipoProduto());
            stmt.setInt(6, oProduto.getIdProduto());
            stmt.execute();
            conexao.commit();
            return true;
        }catch (Exception ex){
            try{
                System.out.println("Problemas ao alterar a Produto! Erro:"+ex.getMessage());
                ex.printStackTrace();
                conexao.rollback();
            }catch (SQLException e){
                System.out.println("Erro:"+e.getMessage());
                e.printStackTrace();
            }
            return false;
        }
    }

    @Override
    public boolean excluir(int numero) {
        int idProduto = numero;
        PreparedStatement stmt = null;
        
        String sql = "delete from produto where idproduto=?";
        try{
            stmt=conexao.prepareStatement(sql);
            stmt.setInt(1, idProduto);
            stmt.execute();
            conexao.commit();
            return true;
        }catch (Exception ex){
            System.out.println("Problemas ao excluir a Produto! Erro:" +ex.getMessage());
            try{
                conexao.rollback();
            }catch (Exception e){
                System.out.println("Erro rolback"+ e.getMessage());
                e.printStackTrace();
            }
            return  false;
        }
    }

    @Override
    public Object carregar(int numero) {
        int idProduto = numero;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Produto oProduto = null;
        String sql = "select * from produto where idproduto=?";
        try{
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, idProduto);
            rs=stmt.executeQuery();
            while (rs.next()){
                oProduto = new Produto();
                oProduto.setIdProduto(rs.getInt("idProduto"));
                oProduto.setUltimoPrecoPago(rs.getDouble("ultimoPrecoPago"));
                oProduto.setSaldoAtual(rs.getDouble("saldoAtual"));
                
                UnidadeMedidaDAO oUnidadeMedidaDAO = new UnidadeMedidaDAO();
                oProduto.setUnidademedida((UnidadeMedida)oUnidadeMedidaDAO.carregar(rs.getInt("unidadeMedida")));
                
                TipoProdutoDAO oTipoProdutoDAO = new TipoProdutoDAO();
                oProduto.setTipoproduto((TipoProduto)oTipoProdutoDAO.carregar(rs.getInt("tipoProduto")));
                

            }
            return oProduto;
        }catch (Exception ex) {
            System.out.println("Problemas ao carregar Produto! Erro:"+ex.getMessage());
            return false;
        }
    }

    @Override
    public List<Object> listar() {
        List<Object> resultado = new ArrayList<>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "Select * from produto order by idProduto";
        try{
            stmt = conexao.prepareStatement(sql);
            rs=stmt.executeQuery();
            while (rs.next()){
                Produto oProduto = new Produto();
                oProduto.setIdProduto(rs.getInt("idProduto"));
                oProduto.setNomeProduto(rs.getString("nomeProduto"));
                oProduto.setUltimoPrecoPago(rs.getDouble("ultimoPrecoPago"));
                oProduto.setSaldoAtual(rs.getDouble("saldoAtual"));
 
                TipoProdutoDAO oTipoProdutoDAO = null;
                UnidadeMedidaDAO oUnidadeMedidaDAO = null;
                
                try{
                    oTipoProdutoDAO = new TipoProdutoDAO();
                }catch (Exception ex){
                    System.out.println("Erro buscar tipoproduto"+ex.getMessage());
                    ex.printStackTrace();
                    
                }try {
                    oUnidadeMedidaDAO = new UnidadeMedidaDAO();
                } catch (Exception e) {
                    System.out.println("Erro buscar unidademedida"+e.getMessage());
                    e.printStackTrace();
                    
                }
                
                oProduto.setTipoproduto((TipoProduto)oTipoProdutoDAO.carregar(rs.getInt("tipoproduto"))); 
                oProduto.setUnidademedida((UnidadeMedida)oUnidadeMedidaDAO.carregar(rs.getInt("unidademedida")));
                
                resultado.add(oProduto);
            }
        }catch (SQLException ex){
            System.out.println("Problemas ao listar Produto! Erro:"+ex.getMessage());
        }
        return resultado;
    }
    
}
