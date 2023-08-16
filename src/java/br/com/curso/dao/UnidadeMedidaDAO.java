/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.curso.dao;

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
public class UnidadeMedidaDAO implements GenericDAO{
    
    
    private Connection conexao;
    
    public UnidadeMedidaDAO() throws Exception{
        conexao = SingleConnection.getConnection();
    }

    @Override
    public boolean cadastrar(Object objeto) {
        UnidadeMedida oUnidadeMedida = (UnidadeMedida)objeto;
        boolean retorno=false;
        if(oUnidadeMedida.getIdUnidadeMedida()==0){
            retorno = this.inserir(oUnidadeMedida);
        }else{
            retorno = this.alterar(oUnidadeMedida);
        }
        return retorno;
    }

    @Override
    public boolean inserir(Object objeto) {
        UnidadeMedida oUnidadeMedida = (UnidadeMedida)objeto;
        PreparedStatement stmt = null;
        String sql = "Insert into unidademedida (descricao, sigla) values (?,?)";
        try{
          stmt = conexao.prepareStatement(sql);
          stmt.setString(1, oUnidadeMedida.getDescricao());
          stmt.setString(2, oUnidadeMedida.getSigla());
          stmt.execute();
          conexao.commit();
          return true;
        }catch (Exception ex){
            try{
            System.out.println("Problemas ao cadastrar a UnidadeMedida! Erro:" + ex.getMessage());
            ex.printStackTrace();
              conexao.rollback();
          }catch(SQLException e){
                System.out.println("Erro"+ e.getMessage());
                e.printStackTrace();
          }
            return false;
       }
    }

    @Override
    public boolean alterar(Object objeto) {
        UnidadeMedida oUnidadeMedida = (UnidadeMedida)objeto;    
        PreparedStatement stmt = null;
        String sql = "update unidademedida set descricao=?, sigla=? where idUnidadeMedida=?";
        try{
          stmt = conexao.prepareStatement(sql);
          stmt.setString(1, oUnidadeMedida.getDescricao());
          stmt.setString(2, oUnidadeMedida.getSigla());
          stmt.setInt(3, oUnidadeMedida.getIdUnidadeMedida());
          stmt.execute();
          conexao.commit();
          return  true;
        } catch (Exception ex){
            try{
                System.out.println("Problemas ao alterar a UnidadeMedida! Erro:" +ex.getMessage());
                ex.printStackTrace();
                conexao.rollback();
            }catch (SQLException e){
                System.out.println("Erro:" +e.getMessage());
                e.printStackTrace();
            }
            return false;
        }
    }

    @Override
    public boolean excluir(int numero) {
        int idUnidadeMedida = numero;
        PreparedStatement stmt = null;
        
        String sql = "delete from unidademedida where idunidademedida=?";
        try{
            stmt=conexao.prepareStatement(sql);
            stmt.setInt(1, idUnidadeMedida);
            stmt.execute();
            conexao.commit();
            return true;
        }catch (Exception ex){
            System.out.println("Problemas ao excluir o UnidadeMedida! Erro:" +ex.getMessage());
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
        int idUnidadeMedida = numero;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        UnidadeMedida oUnidadeMedida = null;
        String sql="select * from unidademedida where idUnidadeMedida=?";
        
        try{
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, idUnidadeMedida);
            rs=stmt.executeQuery();
            while(rs.next()){
                oUnidadeMedida = new UnidadeMedida();
                oUnidadeMedida.setIdUnidadeMedida(rs.getInt("idUnidadeMedida"));
                oUnidadeMedida.setDescricao(rs.getString("descricao"));
                oUnidadeMedida.setSigla(rs.getString("sigla"));
            }
            return oUnidadeMedida;
        }catch(Exception ex){
            System.out.println("Problemas ao carregar UnidadeMedida! Erro:" + ex.getMessage());
            return  false;
        }
    }

    @Override
    public List<Object> listar() {
        List<Object> resultado = new ArrayList<>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "Select * from unidademedida order by idUnidadeMedida";
        try{
        stmt = conexao.prepareStatement(sql);
        rs=stmt.executeQuery();
        while (rs.next()){
        UnidadeMedida oUnidadeMedida = new UnidadeMedida();
        oUnidadeMedida.setIdUnidadeMedida(rs.getInt("idUnidadeMedida"));
        oUnidadeMedida.setDescricao(rs.getString("descricao"));
        oUnidadeMedida.setSigla(rs.getString("sigla"));
              
       resultado.add(oUnidadeMedida);
          }
       }catch (SQLException ex){
          System.out.println("Problemas ao listar UnidadeMedida! Erro: "+ex.getMessage());
       }
      return resultado;
    }
    
}
