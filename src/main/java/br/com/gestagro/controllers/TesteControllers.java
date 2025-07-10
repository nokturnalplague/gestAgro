package br.com.gestagro.controllers;

import br.com.gestagro.entities.*;
import br.com.gestagro.controllers.*;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public class TesteControllers {
    public static void main(String[] args) {
        try {
            // Controllers
            UsuarioController usuarioController = new UsuarioController();
            EquipamentoController equipamentoController = new EquipamentoController();
            MecanicoController mecanicoController = new MecanicoController();
            ManutencaoController manutencaoController = new ManutencaoController();
            RelacaoController relacaoController = new RelacaoController();

            // Usuário
            Usuario usuario = new Usuario();
            usuario.setNome("Ana Controladora");
            usuario.setFuncao("Fiscal");
            usuarioController.adicionarUsuario(usuario);
            Usuario u = usuarioController.listarUsuarios().getLast();
            System.out.println("Usuário: " + u.getNome());

            // Equipamento
            Equipamento equipamento = new Equipamento();
            equipamento.setTipo("Colheitadeira");
            equipamento.setMarca("Case IH");
            equipamento.setModelo("Axial Flow 7150");
            equipamento.setManutencaoFeitas("Atualização de firmware");
            equipamento.setManutencaoFazer("Troca de óleo");
            equipamentoController.adicionarEquipamento(equipamento);
            Equipamento e = equipamentoController.listarEquipamentos().getLast();
            System.out.println("Equipamento: " + e.getModelo());

            // Mecânico
            Mecanico mecanico = new Mecanico();
            mecanico.setNome("ControlTec");
            mecanicoController.adicionarMecanico(mecanico);
            Mecanico m = mecanicoController.listarMecanicos().getLast();
            System.out.println("Mecânico: " + m.getNome());

            // Manutenção
            Manutencao manutencao = new Manutencao();
            manutencao.setPecasManutencao("Filtro ar, vela");
            manutencao.setDataManutencao(Date.valueOf("2025-07-11"));
            manutencao.setExecutada(false);
            manutencaoController.adicionarManutencao(manutencao);
            Manutencao man = manutencaoController.listarManutencoes().getLast();
            System.out.println("Manutenção: " + man.getPecasManutencao());

            // Relação
            Relacao relacao = new Relacao();
            relacao.setCodigoEquipamento(e.getCodigo());
            relacao.setCodigoMecanico(m.getCodigo());
            relacao.setCodigoManutencao(man.getCodigo());
            relacaoController.adicionarRelacao(relacao);
            Relacao r = relacaoController.listarRelacoes().getLast();
            System.out.println("Relação: " + r.getCodigo());

            // Remoção
            relacaoController.deletarRelacao(r.getCodigo());
            manutencaoController.deletarManutencao(man.getCodigo());
            mecanicoController.deletarMecanico(m.getCodigo());
            equipamentoController.deletarEquipamento(e.getCodigo());
            usuarioController.deletarUsuario(u.getCodigo());

            System.out.println("🧹 Limpeza finalizada com sucesso.");

        } catch (SQLException e) {
            System.out.println("❌ Erro SQL: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("❌ Erro inesperado: " + e.getMessage());
        }
    }
}
