/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Timestamp;
import java.util.Date;

/**
 *
 * @author Flavio
 */
public class Venda {
    private int codigo;
    private Double total;
    private Timestamp datav;
    private int codcli;

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
    public void setCodigo(String codigo) {
        this.codigo = Integer.parseInt(codigo);
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

      public Timestamp getDatav() {
        return datav;
    }
    public void setDatav(Timestamp datav) {
        this.datav = datav;
    }


    public int getCodcli() {
        return codcli;
    }

    public void setCodcli(int codcli) {
        this.codcli = codcli;
    }
    public void setCodcli(String codcli) {
        this.codcli = Integer.parseInt(codcli);
    }

    
}
