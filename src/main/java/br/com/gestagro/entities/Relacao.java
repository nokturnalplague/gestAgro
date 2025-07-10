package br.com.gestagro.entities;

public class Relacao {
    private int codigo;
    private int codigoManutencao;
    private int codigoMecanico;
    private int codigoEquipamento;

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public int getCodigoManutencao() {
        return codigoManutencao;
    }

    public void setCodigoManutencao(int codigoManutencao) {
        this.codigoManutencao = codigoManutencao;
    }

    public int getCodigoMecanico() {
        return codigoMecanico;
    }

    public void setCodigoMecanico(int codigoMecanico) {
        this.codigoMecanico = codigoMecanico;
    }

    public int getCodigoEquipamento() {
        return codigoEquipamento;
    }

    public void setCodigoEquipamento(int codigoEquipamento) {
        this.codigoEquipamento = codigoEquipamento;
    }
}
