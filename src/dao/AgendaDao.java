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
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import model.Agenda;
import model.Funcionario;
import model.Paciente;
import util.Yagami;

/**
 *
 * @author Juan
 */
public class AgendaDao {
    
    public List<Agenda> listarData(Date data) {
        
        String sql = String.format("SELECT * FROM agenda WHERE data_ag = '%s'"
                + "order by hora_ag asc", data);

        List<Agenda> agendas = new ArrayList<>();
        
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
                Agenda agenda = new Agenda();

                //Recupera o id do banco e atribui ele ao objeto
                agenda.setId_agenda(rset.getInt("id_agenda"));
                agenda.setData_ag(rset.getDate("data_ag"));
                agenda.setHora_ag(rset.getTime("hora_ag"));
                agenda.setTipo(rset.getString("tipo"));
                agenda.setFk_atendimento(rset.getInt("fk_atendimento"));
                agenda.setFk_consulta(rset.getInt("fk_consulta"));
                agenda.setDetalhes(rset.getString("detalhes"));                
                
                //Adiciono o contato recuperado, a lista de contatos
                agendas.add(agenda);
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
                //e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Erro inexesperado.\n" + e, "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }

        return agendas;
    }
    
    public List<Agenda> buscaPacienteAtendimento(int id_atendimento) {
        
        String sql = String.format("SELECT * FROM agenda WHERE data_ag = '%s'"
                + "order by hora_ag asc", id_atendimento);

        List<Agenda> agendas = new ArrayList<>();
        
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
                Agenda agenda = new Agenda();

                //Recupera o id do banco e atribui ele ao objeto
                agenda.setId_agenda(rset.getInt("id_agenda"));
                agenda.setData_ag(rset.getDate("data_ag"));
                agenda.setHora_ag(rset.getTime("hora_ag"));
                agenda.setTipo(rset.getString("tipo"));
                agenda.setFk_atendimento(rset.getInt("fk_atendimento"));
                agenda.setFk_consulta(rset.getInt("fk_consulta"));
                agenda.setDetalhes(rset.getString("detalhes"));
                
                if(agenda.getFk_atendimento() == 0){
                    
                }
                
                
                //Adiciono o contato recuperado, a lista de contatos
                agendas.add(agenda);
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
                //e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Erro inexesperado.\n" + e, "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }

        return agendas;
    }

    public boolean salvarAtendimentoAgenda(Agenda agenda) {
        boolean retorno = true;
        /*
         * Isso é uma sql comum, os ? são os parâmetros que nós vamos adicionar
         * na base de dados
         */
        String sql = "INSERT INTO db_fisiobase.agenda\n"
                + "(data_ag, hora_ag, tipo, fk_atendimento, fk_consulta, detalhes)\n"
                + "VALUES(?, ?, ?, ?, NULL, ?);";

        Connection conn = null;
        PreparedStatement pstm = null;

        try {
            //Cria uma conexão com o banco
            conn = MysqlConn.createConnectionToMySQL();

            //Cria um PreparedStatment, classe usada para executar a query
            pstm = conn.prepareStatement(sql);

            //Adiciona o valor do primeiro parâmetro da sql
            pstm.setDate(1, agenda.getData_ag());
            pstm.setTime(2, agenda.getHora_ag());
            pstm.setString(3, agenda.getTipo());
            pstm.setInt(4, agenda.getFk_atendimento());
            pstm.setString(5, agenda.getDetalhes());
            //Executa a sql para inserção dos dados
            pstm.execute();

        } catch (Exception e) {
            Yagami.mensagemErro(e);
            retorno = false;

        } finally {
            //Fecha as conexões
            try {
                if (pstm != null) {
                    pstm.close();
                }
                if (conn != null) {
                    conn.close();
                }

            } catch (SQLException e) {
                Yagami.mensagemErro(e);
                retorno = false;
            }
        }
        return retorno;
    }

    public boolean alterar(Agenda agenda) {
        boolean retorno = true;
        /*
         * Isso é uma sql comum, os ? são os parâmetros que nós vamos adicionar
         * na base de dados
         */
        String sql = "UPDATE agenda SET"
                + "data_ag = ?, hora_ag = ?, tipo = ?, fk_atendimento = ?,"
                + "fk_consulta = ?, detalhes = ? WHERE id_agenda = ?";

        Connection conn = null;
        PreparedStatement pstm = null;

        try {
            //Cria uma conexão com o banco
            conn = MysqlConn.createConnectionToMySQL();

            //Cria um PreparedStatment, classe usada para executar a query
            pstm = conn.prepareStatement(sql);

            //Adiciona o valor do primeiro parâmetro da sql
            pstm.setDate(1, agenda.getData_ag());
            pstm.setTime(2, agenda.getHora_ag());
            pstm.setString(3, agenda.getTipo());
            pstm.setInt(4, agenda.getFk_atendimento());
            pstm.setInt(5, agenda.getFk_consulta());
            pstm.setString(6, agenda.getDetalhes());

            //Executa a sql para inserção dos dados
            pstm.execute();

        } catch (Exception e) {
            Yagami.mensagemErro(e);
            retorno = false;

        } finally {
            //Fecha as conexões
            try {
                if (pstm != null) {
                    pstm.close();
                }
                if (conn != null) {
                    conn.close();
                }

            } catch (SQLException e) {
                //e.printStackTrace();
                Yagami.mensagemErro(e);
                retorno = false;
            }
        }
        return retorno;
    }

    public boolean deletar(int id) {
        boolean retorno = true;
        /*
         * Isso é uma sql comum, os ? são os parâmetros que nós vamos adicionar
         * na base de dados
         */
        String sql = "DELETE FROM agenda "
                + "WHERE id_agenda = " + id;

        Connection conn = null;
        PreparedStatement pstm = null;

        try {
            //Cria uma conexão com o banco
            conn = MysqlConn.createConnectionToMySQL();

            //Cria um PreparedStatment, classe usada para executar a query
            pstm = conn.prepareStatement(sql);

            //Executa a sql para inserção dos dados
            pstm.execute();

        } catch (Exception e) {
            Yagami.mensagemErro(e);
            retorno = false;

        } finally {
            //Fecha as conexões
            try {
                if (pstm != null) {
                    pstm.close();
                }
                if (conn != null) {
                    conn.close();
                }

            } catch (SQLException e) {
                Yagami.mensagemErro(e);
                retorno = false;
            }
        }
        return retorno;
    }

}
