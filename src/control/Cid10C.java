/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import dao.Cid10Dao;

import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import model.Cid10;

/**
 *
 * @author david
 */
public class Cid10C {
    public static final Cid10C CONTROL = new Cid10C();
    
    // Instância do DAO
    Cid10Dao cid10Dao = new Cid10Dao();
    
   public void listTable(JTable tabela, boolean where, String idCid,String descricaoCid ) {
        List<Cid10> listCid10;
        
        // Atualizar Tabela
        if(where == false) {
            listCid10 = cid10Dao.listar();
        } else {
            listCid10 = cid10Dao.listarCid10Where(idCid, descricaoCid);
        }
        
        
        // Clear
        DefaultTableModel model = (DefaultTableModel) tabela.getModel();
        model.setRowCount(0);
        
        for(Cid10 h : listCid10) {
            model = (DefaultTableModel) tabela.getModel();
            model.addRow(new Object[]{
                h.getIdCid(),
                h.getDescricaocid()
            });
        }
    }
    
    
    

}
