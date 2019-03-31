package control;

import dao.AtendimentoDao;
import java.util.List;
import javax.swing.JOptionPane;
import model.Atendimento;

/**
 * Classe de Controle de Atendimento
 * @author Juan Galvão
 */
public class AtendimentoC {
    /** Variável global para acessar métodos da classe */
    public static final AtendimentoC CONTROL = new AtendimentoC();
    
    // Instância do DAO
    AtendimentoDao ateDao = new AtendimentoDao();
    
    /**
     * Método para salvar no banco de dados
     * @param atendimento Recebe objeto do atendimento
     * @return True ou false para saber se salvou com sucesso
     */
    public boolean create(Atendimento atendimento) {
        if(ateDao.salvar(atendimento)) {
            JOptionPane.showMessageDialog(null, "Atendimento registrado com sucesso!");
            return true;
        } else {
            return false;
        }
    }
    
    
     public boolean update(Atendimento atendimento) {
        if(ateDao.update(atendimento)) {
            JOptionPane.showMessageDialog(null, "Atendimento atualizado com sucesso!");
            return true;
        } else {
            return false;
        }
    }
    
    /**
     * Método para retornar lista de usuários direto da tabela
     * @return Lista com os atendimentos retornados da tabela
     */
    
    public List<Atendimento> read(boolean like, String param) {
         String sql;
        List<Atendimento> atendimentos;
        atendimentos = ateDao.listar(false); 
        
       
       if(like == true) {
            sql = "SELECT * FROM atendimento WHERE fk_patendimento LIKE '%" + param + "%'";
            atendimentos = ateDao.listarCustom(sql);
        } else {
            sql = "SELECT * FROM atendimento";
            atendimentos = ateDao.listarCustom(sql);
        }
        
        
        return atendimentos;
    }
    
    
   
}
