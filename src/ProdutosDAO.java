/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Adm
 */

import java.sql.PreparedStatement;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class ProdutosDAO {
    
    Connection conn;
    PreparedStatement prep;
    ResultSet resultset;
    conectaDAO conecta;
    
    public ProdutosDAO() {
        this.conecta = new conectaDAO();
        this.conn = this.conecta.connectDB();
    }
    
    
    public void cadastrarProduto (ProdutosDTO produto){
                
        String sql = "INSERT INTO produtos (nome,valor,status)VALUES(?,?,?)";
        
        try {
            PreparedStatement prep = this.conn.prepareStatement(sql);
            prep.setString(1, produto.getNome());
            prep.setInt(2, produto.getValor());
            prep.setString(3, produto.getStatus());
            prep.executeUpdate();
            JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso");
            
        } catch (SQLException ex) {
            System.out.println("Não foi possível cadastrar o produto " + ex.getMessage());
        }
        
        
    }
    
    public ArrayList<ProdutosDTO> listarProdutos(){
        ArrayList<ProdutosDTO> listagem = new ArrayList<>();
        
        String sql = "select * from produtos";
        try {
            PreparedStatement prep = this.conn.prepareStatement(sql);
            resultset = prep.executeQuery();

            while(resultset.next()) {
                ProdutosDTO p = new ProdutosDTO();
                p.setId(resultset.getInt("ID"));
                p.setNome(resultset.getString("Nome"));
                p.setValor(resultset.getInt("valor"));
                p.setStatus(resultset.getString("status"));
                listagem.add(p);
            }
            
        } catch (SQLException ex) {
            System.out.println("Não foi possível listar os produtos " + ex.getMessage());
            
        }        
        return listagem;
    }
    
    
    
        
}

