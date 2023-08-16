/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.curso.dao;

import br.com.curso.model.Funcionario;
import br.com.curso.model.MovimentoEstoque;
import br.com.curso.model.Produto;
import br.com.curso.model.TipoMovimento;
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
public class MovimentoEstoqueDAO implements GenericDAO{
    
    private Connection conexao;
    
    public MovimentoEstoqueDAO() throws Exception{
        conexao = SingleConnection.getConnection();
    }
    

    @Override
    public boolean cadastrar(Object objeto) {
        MovimentoEstoque oMovimentoEstoque = (MovimentoEstoque)objeto;
        boolean retorno=false;
        if(oMovimentoEstoque.getIdMovimento()==0){
            retorno = this.inserir(oMovimentoEstoque);
        }else{
            retorno = this.alterar(oMovimentoEstoque);
        }
        return retorno;
    }

    @Override
    public boolean inserir(Object objeto) {
        MovimentoEstoque oMovimentoEstoque = (MovimentoEstoque) objeto;
        PreparedStatement stmt = null;
        String sql = "insert into movimentoestoque ( entradaSaida, documento, data, quantidade, valorMovimento, produto, funcionario, tipomovimento ) values (?,?,?,?,?,?,?,?)";
        try{
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, oMovimentoEstoque.getEntradaSaida());
            stmt.setString(2, oMovimentoEstoque.getDocumento());
            stmt.setString(3, oMovimentoEstoque.getData());
            stmt.setDouble(4, oMovimentoEstoque.getQuantidade());
            stmt.setDouble(5, oMovimentoEstoque.getValorMovimento());
            stmt.setInt(6, oMovimentoEstoque.getProduto().getIdProduto());
            stmt.setInt(7, oMovimentoEstoque.getFuncionario().getIdFuncionario());
            stmt.setInt(8, oMovimentoEstoque.getTipomovimento().getIdTipoMovimento());
            stmt.execute();
            conexao.commit();
            return true;
        }catch (Exception ex){
            try{
                System.out.println("Problemas ao cadastrar a Movimento Estoque ! Erro:"+ex.getMessage());
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
        MovimentoEstoque oMovimentoEstoque = (MovimentoEstoque) objeto;
        PreparedStatement stmt = null;
        String sql = "update movimentoestoque set entradaSaida=?, documento=?, data=?, quantidade=?, valorMovimento=?, produto=?, funcionario=?, tipomovimento=? where idMovimento=?";
        try{
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, oMovimentoEstoque.getEntradaSaida());
            stmt.setString(2, oMovimentoEstoque.getDocumento());
            stmt.setString(3, oMovimentoEstoque.getData());
            stmt.setDouble(4, oMovimentoEstoque.getQuantidade());
            stmt.setDouble(5, oMovimentoEstoque.getValorMovimento());
            stmt.setDouble(6, oMovimentoEstoque.getProduto().getIdProduto());
            stmt.setInt(7, oMovimentoEstoque.getFuncionario().getIdFuncionario());
            stmt.setInt(8, oMovimentoEstoque.getTipomovimento().getIdTipoMovimento());
            stmt.setInt(9, oMovimentoEstoque.getIdMovimento());
            stmt.execute();
            conexao.commit();
            return true;
        }catch (Exception ex){
            try{
                System.out.println("Problemas ao alterar o MovimentoEstoque! Erro:"+ex.getMessage());
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
                int IdMovimento = numero;
        PreparedStatement stmt = null;
        
        String sql = "delete from movimentoestoque where idmovimento=?";
        try{
            stmt=conexao.prepareStatement(sql);
            stmt.setInt(1, IdMovimento);
            stmt.execute();
            conexao.commit();
            return true;
        }catch (Exception ex){
            System.out.println("Problemas ao excluir o MovimentoEstoque! Erro:" +ex.getMessage());
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
        int IdMovimento = numero;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        MovimentoEstoque oMovimentoEstoque = null;
        String sql = "select * from movimentoestoque where idmovimento=?";
        try{
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, IdMovimento);
            rs=stmt.executeQuery();
            while (rs.next()){
                oMovimentoEstoque = new MovimentoEstoque();
                oMovimentoEstoque.setIdMovimento(rs.getInt("idMovimento"));
                oMovimentoEstoque.setEntradaSaida(rs.getString("entradaSaida"));
                oMovimentoEstoque.setDocumento(rs.getString("documento"));
                oMovimentoEstoque.setData(rs.getString("data"));
                oMovimentoEstoque.setQuantidade(rs.getDouble("quantidade"));
                oMovimentoEstoque.setValorMovimento(rs.getDouble("valorMovimento"));
                
                
                ProdutoDAO oProdutoDAO = new ProdutoDAO();
                oMovimentoEstoque.setProduto((Produto)oProdutoDAO.carregar(rs.getInt("produto")));
                
                FuncionarioDAO oFuncionarioDAO = new FuncionarioDAO();
                oMovimentoEstoque.setFuncionario((Funcionario)oFuncionarioDAO.carregar(rs.getInt("funcionario")));
                
                TipoMovimentoDAO oTipoMovimentoDAO = new TipoMovimentoDAO();
                oMovimentoEstoque.setTipomovimento((TipoMovimento)oTipoMovimentoDAO.carregar(rs.getInt("tipomovimento")));

                

            }
            return oMovimentoEstoque;
        }catch (Exception ex) {
            System.out.println("Problemas ao carregar MovimentoEstoque! Erro:"+ex.getMessage());
            return false;
        }
    }

    @Override
    public List<Object> listar() {
        List<Object> resultado = new ArrayList<>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "Select * from movimentoestoque order by idMovimento";
        try{
            stmt = conexao.prepareStatement(sql);
            rs=stmt.executeQuery();
            while (rs.next()){
                MovimentoEstoque oMovimentoEstoque = new MovimentoEstoque();
                oMovimentoEstoque.setIdMovimento(rs.getInt("idMovimento"));
                oMovimentoEstoque.setEntradaSaida(rs.getString("entradaSaida"));
                oMovimentoEstoque.setDocumento(rs.getString("documento"));
                oMovimentoEstoque.setData(rs.getString("data"));
                oMovimentoEstoque.setQuantidade(rs.getDouble("quantidade"));
                oMovimentoEstoque.setValorMovimento(rs.getDouble("valorMovimento"));
              
                
                ProdutoDAO oProdutoDAO = null;
                try{
                    oProdutoDAO = new ProdutoDAO();
                }catch (Exception ex){
                    System.out.println("Erro buscar produto"+ex.getMessage());
                    ex.printStackTrace();   
                }
                oMovimentoEstoque.setProduto((Produto)oProdutoDAO.carregar(rs.getInt("produto")));
                
                
                
                FuncionarioDAO oFuncionarioDAO = null;
                try {
                    oFuncionarioDAO = new FuncionarioDAO();
                } catch (Exception e) {
                    System.out.println("Erro buscar funcionario"+e.getMessage());
                    e.printStackTrace(); 
                }
                oMovimentoEstoque.setFuncionario((Funcionario)oFuncionarioDAO.carregar(rs.getInt("funcionario")));
                

                
                 TipoMovimentoDAO oTipoMovimentoDAO = null;
                try{
                    oTipoMovimentoDAO = new TipoMovimentoDAO();
                }catch (Exception x) {
                    System.out.println("Erro buscar tipomovimento" +x.getMessage());
                    x.printStackTrace();
                }
                oMovimentoEstoque.setTipomovimento((TipoMovimento)oTipoMovimentoDAO.carregar(rs.getInt("tipomovimento")));
                

                resultado.add(oMovimentoEstoque);
            }
        }catch (SQLException ex){
            System.out.println("Problemas ao listar Produto! Erro:"+ex.getMessage());
        }
        return resultado;
    }
    
}
