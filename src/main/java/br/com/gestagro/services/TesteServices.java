package br.com.gestagro.services;

import br.com.gestagro.entities.*;
import br.com.gestagro.services.*;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public class TesteServices {
    public static void main(String[] args) {
        try {
            // Serviços
            UsuarioService usuarioService = new UsuarioService();
            EquipamentoService equipamentoService = new EquipamentoService();
            MecanicoService mecanicoService = new MecanicoService();
            ManutencaoService manutencaoService = new ManutencaoService();
            RelacaoService relacaoService = new RelacaoService();

            // Usuário
            Usuario usuario = new Usuario();
            usuario.setNome("Ana Service");
            usuario.setFuncao("Coordenadora");
            usuarioService.cadastrarUsuario(usuario);
            Usuario u = usuarioService.listarUsuarios().getLast();
            System.out.println("Usuário criado: " + u.getNome());

            // Equipamento
            Equipamento equipamento = new Equipamento();
            equipamento.setTipo("Pulverizador");
            equipamento.setMarca("Jacto");
            equipamento.setModelo("XP 3020");
            equipamento.setManutencaoFeitas("Correia trocada");
            equipamento.setManutencaoFazer("Filtro novo");
            equipamentoService.cadastrarEquipamento(equipamento);
            Equipamento e = equipamentoService.listarEquipamentos().getLast();
            System.out.println("Equipamento criado: " + e.getModelo());

            // Mecânico
            Mecanico mecanico = new Mecanico();
            mecanico.setNome("ServiceTech");
            mecanicoService.cadastrarMecanico(mecanico);
            Mecanico m = mecanicoService.listarMecanicos().getLast();
            System.out.println("Mecânico criado: " + m.getNome());

            // Manutenção
            Manutencao manutencao = new Manutencao();
            manutencao.setPecasManutencao("Válvula, mangueira");
            manutencao.setDataManutencao(Date.valueOf("2025-07-10"));
            manutencao.setExecutada(false);
            manutencaoService.cadastrarManutencao(manutencao);
            Manutencao man = manutencaoService.listarManutencoes().getLast();
            System.out.println("Manutenção criada: " + man.getPecasManutencao());

            // Relação
            Relacao relacao = new Relacao();
            relacao.setCodigoEquipamento(e.getCodigo());
            relacao.setCodigoMecanico(m.getCodigo());
            relacao.setCodigoManutencao(man.getCodigo());
            relacaoService.cadastrarRelacao(relacao);
            Relacao r = relacaoService.listarRelacoes().getLast();
            System.out.println("Relação criada: " + r.getCodigo());

            // Limpeza
            relacaoService.deletar(r.getCodigo());
            manutencaoService.deletar(man.getCodigo());
            mecanicoService.deletar(m.getCodigo());
            equipamentoService.deletar(e.getCodigo());
            usuarioService.deletar(u.getCodigo());

            System.out.println("Registros removidos com sucesso.");

        } catch (SQLException e) {
            System.out.println("Erro SQL: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Erro inesperado: " + e.getMessage());
        }
    }
}
