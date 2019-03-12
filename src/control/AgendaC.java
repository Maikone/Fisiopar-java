package control;

import dao.AgendaDao;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import model.Agenda;

/**
 * Classe para controle de Agenda
 *
 * @author Juan Galvão
 */
public class AgendaC {

    /**
     * Variável global para acessar métodos da classe
     */
    public static final AgendaC CONTROL = new AgendaC();

    // Instância do DAO
    AgendaDao ageDao = new AgendaDao();

    public void listarPorData(JTable tabela, Date data) {
        // Atualizar Tabela
        List<Agenda> listAgenda = ageDao.listarData(data);

        // Clear
        DefaultTableModel model = (DefaultTableModel) tabela.getModel();
        model.setRowCount(0);
        for (Agenda h : listAgenda) {
            //CI_Roupa rpa = new CI_Roupa(r.getNome(), r);
            model = (DefaultTableModel) tabela.getModel();
            model.addRow(new Object[]{h.getHora_ag(), h.getTipo(), h.getFk_consulta(), "EOQ"});
        }
    }

    public boolean create(Agenda agenda) {
        if (ageDao.salvarAtendimentoAgenda(agenda)) {
            JOptionPane.showMessageDialog(null, "Registro salvo com sucesso na agenda!");
            return true;
        } else {
            return false;
        }
    }

    public void update(Agenda agenda) {
        if (ageDao.alterar(agenda)) {
            JOptionPane.showMessageDialog(null, "Registro alterado com sucesso na agenda!");

        }
    }

    public void delete(int id) {
        if (ageDao.deletar(id)) {
            JOptionPane.showMessageDialog(null, "Registro removido com sucesso da agenda!");
        }
    }
}
