package singleton;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author zampiroli
 */
public class ConexaoS {
    public Connection conexao;
    private String url = "jdbc:mysql://127.0.0.1:3306/db_fisiobase";    
    private   String usuario = "root";
    private   String senha = "";
    private static ConexaoS instancy;
    
    public ConexaoS(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            JOptionPane.showMessageDialog(null, "Drive Ok");
        }catch(Exception e){              
            JOptionPane.showMessageDialog(null, "Drive Falhou");
     }
        try{
            this.conexao = DriverManager.getConnection(url, usuario, senha);
       JOptionPane.showMessageDialog(null, "Conexão OK");
        }catch(Exception e){ 
            JOptionPane.showMessageDialog(null, "Conexão Falhou");
        }
    }
    
    public static ConexaoS getInstancy(){
         if (instancy == null){
             instancy = new ConexaoS();
         } 
         return instancy;
       } 
    
    public Connection getConexao(){
        return this.conexao;
    }
}





