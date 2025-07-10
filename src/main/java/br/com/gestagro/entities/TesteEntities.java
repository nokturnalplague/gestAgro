package br.com.gestagro.entities;

import br.com.gestagro.entities.*;

import java.sql.Date;

public class TesteEntities {
    public static void main(String[] args) {
        Usuario u = new Usuario();
        u.setCodigo(1);
        u.setNome("João");
        u.setFuncao("Gerente");

        Equipamento e = new Equipamento();
        e.setCodigo(10);
        e.setTipo("Trator");
        e.setMarca("John Deere");
        e.setModelo("JD-X500");
        e.setManutencaoFeitas("Troca de óleo");
        e.setManutencaoFazer("Revisão de freios");

        Mecanico m = new Mecanico();
        m.setCodigo(2);
        m.setNome("Carlos Mecânico");

        Manutencao manut = new Manutencao();
        manut.setCodigo(5);
        manut.setPecasManutencao("Filtro, óleo");
        manut.setDataManutencao(Date.valueOf("2025-07-09"));
        manut.setExecutada(true);

        Relacao r = new Relacao();
        r.setCodigo(3);
        r.setCodigoManutencao(manut.getCodigo());
        r.setCodigoMecanico(m.getCodigo());
        r.setCodigoEquipamento(e.getCodigo());

        System.out.println("Teste das entidades executado com sucesso.");
        System.out.println("Usuário: " + u.getNome() + " - " + u.getFuncao());
        System.out.println("Equipamento: " + e.getTipo() + " - " + e.getMarca());
        System.out.println("Mecanico: " + m.getNome());
        System.out.println("Manutenção: " + manut.getPecasManutencao());
        System.out.println("Relacionamento: Mecânico " + r.getCodigoMecanico() + ", Equipamento " + r.getCodigoEquipamento());
    }
}
