/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author michael
 */
public class StatusAtendimento {

    private int id_status;
    private int fk_atendimento;
    private int fk_patendimento;
    private int status;

    public int getId_status() {
        return id_status;
    }

    public void setId_status(int id_status) {
        this.id_status = id_status;
    }

    public int getFk_atendimento() {
        return fk_atendimento;
    }

    public void setFk_atendimento(int fk_atendimento) {
        this.fk_atendimento = fk_atendimento;
    }

    public int getFk_patendimento() {
        return fk_patendimento;
    }

    public void setFk_patendimento(int fk_patendimento) {
        this.fk_patendimento = fk_patendimento;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    
}
