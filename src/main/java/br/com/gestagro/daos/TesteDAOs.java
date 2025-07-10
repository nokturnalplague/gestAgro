package br.com.gestagro;

import br.com.gestagro.entities.*;
import br.com.gestagro.daos.*;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public class TesteDAOs {
    public static void main(String[] args) {
        try {
            // ===================== USUÁRIO =====================
            Usuario usuario = new Usuario();
            usuario.setNome("João da Silva");
            usuario.setFuncao("Supervisor");

            UsuarioDAO usuarioDAO = new UsuarioDAO();
            usuarioDAO.inserir(usuario);
            List<Usuario> usuarios = usuarioDAO.listarTodos();
            Usuario usuarioSalvo = usuarios.get(usuarios.size() - 1);
            System.out.println("Usuário inserido: " + usuarioSalvo.getNome());

            // ===================== EQUIPAMENTO =====================
            Equipamento equipamento = new Equipamento();
            equipamento.setTipo("Trator");
            equipamento.setMarca("Valtra");
            equipamento.setModelo("A850");
            equipamento.setManutencaoFeitas("Revisão geral");
            equipamento.setManutencaoFazer("Troca de óleo");

            EquipamentoDAO equipamentoDAO = new EquipamentoDAO();
            equipamentoDAO.inserir(equipamento);
            List<Equipamento> equipamentos = equipamentoDAO.listarTodos();
            Equipamento equipamentoSalvo = equipamentos.get(equipamentos.size() - 1);
            System.out.println("Equipamento inserido: " + equipamentoSalvo.getModelo());

            // ===================== MECÂNICO =====================
            Mecanico mecanico = new Mecanico();
            mecanico.setNome("Carlos Técnico");

            MecanicoDAO mecanicoDAO = new MecanicoDAO();
            mecanicoDAO.inserir(mecanico);
            List<Mecanico> mecanicos = mecanicoDAO.listarTodos();
            Mecanico mecanicoSalvo = mecanicos.get(mecanicos.size() - 1);
            System.out.println("Mecânico inserido: " + mecanicoSalvo.getNome());

            // ===================== MANUTENÇÃO =====================
            Manutencao manutencao = new Manutencao();
            manutencao.setPecasManutencao("Filtro, Correia");
            manutencao.setDataManutencao(Date.valueOf("2025-07-10"));
            manutencao.setExecutada(false);

            ManutencaoDAO manutencaoDAO = new ManutencaoDAO();
            manutencaoDAO.inserir(manutencao);
            List<Manutencao> manutencoes = manutencaoDAO.listarTodos();
            Manutencao manutencaoSalva = manutencoes.get(manutencoes.size() - 1);
            System.out.println("Manutenção inserida: " + manutencaoSalva.getPecasManutencao());

            // ===================== RELAÇÃO =====================
            Relacao relacao = new Relacao();
            relacao.setCodigoEquipamento(equipamentoSalvo.getCodigo());
            relacao.setCodigoMecanico(mecanicoSalvo.getCodigo());
            relacao.setCodigoManutencao(manutencaoSalva.getCodigo());

            RelacaoDAO relacaoDAO = new RelacaoDAO();
            relacaoDAO.inserir(relacao);
            List<Relacao> relacoes = relacaoDAO.listarTodos();
            Relacao relacaoSalva = relacoes.get(relacoes.size() - 1);
            System.out.println("Relação inserida com código: " + relacaoSalva.getCodigo());

            // ===================== ATUALIZAR MANUTENÇÃO =====================
            manutencaoSalva.setExecutada(true);
            manutencaoDAO.atualizar(manutencaoSalva);
            System.out.println("Manutenção atualizada para executada.");

            // ===================== BUSCAR POR ID =====================
            Usuario usuarioBuscado = usuarioDAO.buscarPorId(usuarioSalvo.getCodigo());
            System.out.println("Usuário buscado: " + usuarioBuscado.getNome());

            // ===================== DELETAR TUDO =====================
            relacaoDAO.deletar(relacaoSalva.getCodigo());
            manutencaoDAO.deletar(manutencaoSalva.getCodigo());
            mecanicoDAO.deletar(mecanicoSalvo.getCodigo());
            equipamentoDAO.deletar(equipamentoSalvo.getCodigo());
            usuarioDAO.deletar(usuarioSalvo.getCodigo());

            System.out.println("Todos os registros foram deletados com sucesso!");

        } catch (SQLException e) {
            System.out.println("Erro SQL: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Erro inesperado: " + e.getMessage());
        }
    }
}
