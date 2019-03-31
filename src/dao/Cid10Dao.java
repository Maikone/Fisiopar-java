/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import engine.MysqlConn;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import model.Cid10;
import util.Yagami;

/**
 *
 * @author david
 */
public class Cid10Dao {
    
   public List<Cid10> listar() {
        String sql = "SELECT * FROM cid10";

        List<Cid10> cid10p = new ArrayList<>();

        Connection conn = null;
        PreparedStatement pstm = null;

        //Classe que vai recuperar os dados do banco de dados
        ResultSet rset = null;

        try {
            conn = MysqlConn.createConnectionToMySQL();
            pstm = conn.prepareStatement(sql);
            rset = pstm.executeQuery();

            //Enquanto existir dados no banco de dados, faça
            while (rset.next()) {
                Cid10 cid10 = new Cid10();

                //Recupera o id do banco e atribui ele ao objeto
               
                cid10.setIdCid("idCid");
                cid10.setDescricaocid("descricaoCid");
                
                //Adiciono o contato recuperado, a lista de contatos
                cid10p.add(cid10);
            }
            
        } catch (Exception e) {
            //e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro inexesperado.\n" + e, "Erro", JOptionPane.ERROR_MESSAGE);
            
        } finally {
            try {
                if (rset != null) {
                    rset.close();
                }

                if (pstm != null) {
                    pstm.close();
                }

                if (conn != null) {
                    conn.close();
                }

            } catch (SQLException e) {
                Yagami.mensagemErro(e);
            }
        }

        return cid10p;
    }

   public List<Cid10> listarCid10Where(String idCid,String descricaoCid) {
        String sql = "SELECT idCid, descricaoCid\n" +
                     "FROM cid10\n" +                     
                     "WHERE idCid LIKE " + "'%"+ idCid +"%'" + "OR descricaoCid LIKE" + "'%"+ descricaoCid + "%'" ;
        
        List<Cid10> cid10l = new ArrayList<>();

        Connection conn = null;
        PreparedStatement pstm = null;

        //Classe que vai recuperar os dados do banco de dados
        ResultSet rset = null;

        try {
            conn = MysqlConn.createConnectionToMySQL();
            pstm = conn.prepareStatement(sql);
            rset = pstm.executeQuery();

            //Enquanto existir dados no banco de dados, faça
            while (rset.next()) {
                Cid10 cid10 = new Cid10();

                //Recupera o id do banco e atribui ele ao objeto
                cid10.setIdCid(rset.getString("idCid"));
                cid10.setDescricaocid(rset.getString("descricaoCid"));
                
                
                //Adiciono o contato recuperado, a lista de contatos
                cid10l.add(cid10);
            }
            
        } catch (Exception e) {
            Yagami.mensagemErro(e);
            
        } finally {
            try {
                if (rset != null) {
                    rset.close();
                }

                if (pstm != null) {
                    pstm.close();
                }

                if (conn != null) {
                    conn.close();
                }

            } catch (SQLException e) {
                Yagami.mensagemErro(e);
            }
        }

        return cid10l;
    }
}