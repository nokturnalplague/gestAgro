package br.com.gestagro.entities;

public class Equipamento {
    private int codigo;
    private String tipo;
    private String marca;
    private String modelo;
    private String manutencaoFeitas;
    private String manutencaoFazer;

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getManutencaoFeitas() {
        return manutencaoFeitas;
    }

    public void setManutencaoFeitas(String manutencaoFeitas) {
        this.manutencaoFeitas = manutencaoFeitas;
    }

    public String getManutencaoFazer() {
        return manutencaoFazer;
    }

    public void setManutencaoFazer(String manutencaoFazer) {
        this.manutencaoFazer = manutencaoFazer;
    }
}
