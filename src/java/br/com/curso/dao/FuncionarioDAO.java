/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.curso.dao;

import br.com.curso.model.Funcionario;
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
public class FuncionarioDAO implements GenericDAO{
    
    private Connection conexao;
    
    public FuncionarioDAO() throws Exception{
        conexao = SingleConnection.getConnection();
    }

    @Override
    public boolean cadastrar(Object objeto) {
        Funcionario oFuncionario = (Funcionario)objeto;
        boolean retorno=false;
        if(oFuncionario.getIdFuncionario()==0){
            retorno = this.inserir(oFuncionario);
        }else{
            retorno = this.alterar(oFuncionario);
        }
        return retorno;
    }

    @Override
    public boolean inserir(Object objeto) {
        Funcionario oFuncionario = (Funcionario)objeto;
        PreparedStatement stmt = null;
        String sql = "Insert into funcionario (nomeFuncionario) values (?)";
        try{
          stmt = conexao.prepareStatement(sql);
          stmt.setString(1, oFuncionario.getNomeFuncionario());
          stmt.execute();
          conexao.commit();
          return true;
        }catch (Exception ex){
            try{
            System.out.println("Problemas ao cadastrar a Funcionario! Erro:" + ex.getMessage());
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
       Funcionario oFuncionario = (Funcionario)objeto;    
        PreparedStatement stmt = null;
        String sql = "update funcionario set nomeFuncionario=? where idFuncionario=?";
        try{
          stmt = conexao.prepareStatement(sql);
          stmt.setString(1, oFuncionario.getNomeFuncionario());
          stmt.setInt(2, oFuncionario.getIdFuncionario());
          stmt.execute();
          conexao.commit();
          return  true;
        } catch (Exception ex){
            try{
                System.out.println("Problemas ao alterar a Funcionario! Erro:" +ex.getMessage());
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
        int idFuncionario = numero;
        PreparedStatement stmt = null;
        
        String sql = "delete from funcionario where idfuncionario=?";
        try{
            stmt=conexao.prepareStatement(sql);
            stmt.setInt(1, idFuncionario);
            stmt.execute();
            conexao.commit();
            return true;
        }catch (Exception ex){
            System.out.println("Problemas ao excluir o Funcionario! Erro:" +ex.getMessage());
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
        int idFuncionario = numero;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Funcionario oFuncionario = null;
        String sql="select * from funcionario where idFuncionario=?";
        
        try{
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, idFuncionario);
            rs=stmt.executeQuery();
            while(rs.next()){
                oFuncionario = new Funcionario();
                oFuncionario.setIdFuncionario(rs.getInt("idFuncionario"));
                oFuncionario.setNomeFuncionario(rs.getString("nomeFuncionario"));
            }
            return oFuncionario;
        }catch(Exception ex){
            System.out.println("Problemas ao carregar Funcionario! Erro:" + ex.getMessage());
            return  false;
        }
    }

    @Override
    public List<Object> listar() {
        List<Object> resultado = new ArrayList<>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "Select * from funcionario order by idFuncionario";
        try{
        stmt = conexao.prepareStatement(sql);
        rs=stmt.executeQuery();
        while (rs.next()){
        Funcionario oFuncionario = new Funcionario();
        oFuncionario.setIdFuncionario(rs.getInt("idFuncionario"));
        oFuncionario.setNomeFuncionario(rs.getString("nomeFuncionario"));
              
       resultado.add(oFuncionario);
          }
       }catch (SQLException ex){
          System.out.println("Problemas ao listar Funcionario! Erro: "+ex.getMessage());
       }
      return resultado;
    }
    
}
