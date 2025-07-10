package br.com.gestagro.entities;

import java.sql.Date;

public class Manutencao {
    private int codigo;
    private String pecasManutencao;
    private Date dataManutencao;
    private boolean executada;

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getPecasManutencao() {
        return pecasManutencao;
    }

    public void setPecasManutencao(String pecasManutencao) {
        this.pecasManutencao = pecasManutencao;
    }

    public Date getDataManutencao() {
        return dataManutencao;
    }

    public void setDataManutencao(Date dataManutencao) {
        this.dataManutencao = dataManutencao;
    }

    public boolean isExecutada() {
        return executada;
    }

    public void setExecutada(boolean executada) {
        this.executada = executada;
    }
}
