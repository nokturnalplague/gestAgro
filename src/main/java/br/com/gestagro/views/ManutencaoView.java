package br.com.gestagro.views;

import br.com.gestagro.controllers.*;
import br.com.gestagro.entities.*;

import javax.swing.*;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public class ManutencaoView {

    private static final ManutencaoController controller = new ManutencaoController();

    public static void menu() {
        String menu = """
                🛠️ MENU MANUTENÇÃO
                
                1 - Cadastrar
                2 - Listar todas
                3 - Buscar por ID
                4 - Atualizar
                5 - Remover
                0 - Voltar
                """;

        while (true) {
            String opcao = JOptionPane.showInputDialog(null, menu, "GestAgro - Manutenções", JOptionPane.PLAIN_MESSAGE);
            if (opcao == null || opcao.equals("0")) break;

            try {
                switch (opcao) {
                    case "1" -> cadastrar();
                    case "2" -> listarTodas();
                    case "3" -> buscarPorId();
                    case "4" -> atualizar();
                    case "5" -> deletar();
                    default -> JOptionPane.showMessageDialog(null, "Opção inválida.");
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Erro no banco de dados:\n" + e.getMessage());
            }
        }
    }

    private static void cadastrar() throws SQLException {
        String pecas = JOptionPane.showInputDialog("Peças utilizadas na manutenção:");
        String data = JOptionPane.showInputDialog("Data (AAAA-MM-DD):");
        int executada = JOptionPane.showConfirmDialog(null, "A manutenção foi executada?", "Executada", JOptionPane.YES_NO_OPTION);

        if (pecas != null && data != null) {
            Manutencao m = new Manutencao();
            m.setPecasManutencao(pecas);
            m.setDataManutencao(Date.valueOf(data));
            m.setExecutada(executada == JOptionPane.YES_OPTION);

            controller.adicionarManutencao(m);

            List<Manutencao> manutencoes = controller.listarManutencoes();
            Manutencao manutencaoSalva = manutencoes.getLast();

            int desejaRelacionar = JOptionPane.showConfirmDialog(null,
                    "Deseja vincular essa manutenção a um equipamento e mecânico?",
                    "Relacionar?", JOptionPane.YES_NO_OPTION);

            if (desejaRelacionar == JOptionPane.YES_OPTION) {
                // EQUIPAMENTO
                List<Equipamento> equipamentos = new EquipamentoController().listarEquipamentos();
                String[] nomesEquipamentos = equipamentos.stream()
                        .map(e -> "ID " + e.getCodigo() + " - " + e.getTipo() + " " + e.getModelo())
                        .toArray(String[]::new);
                String equipSelecionado = (String) JOptionPane.showInputDialog(null, "Escolha o equipamento:",
                        "Equipamentos", JOptionPane.QUESTION_MESSAGE, null, nomesEquipamentos, nomesEquipamentos[0]);
                if (equipSelecionado == null) return;
                int idEquipamento = Integer.parseInt(equipSelecionado.split(" ")[1]);

                // MECÂNICO
                List<Mecanico> mecanicos = new MecanicoController().listarMecanicos();
                String[] nomesMecanicos = mecanicos.stream()
                        .map(mec -> "ID " + mec.getCodigo() + " - " + mec.getNome())
                        .toArray(String[]::new);
                String mecSelecionado = (String) JOptionPane.showInputDialog(null, "Escolha o mecânico:",
                        "Mecânicos", JOptionPane.QUESTION_MESSAGE, null, nomesMecanicos, nomesMecanicos[0]);
                if (mecSelecionado == null) return;
                int idMecanico = Integer.parseInt(mecSelecionado.split(" ")[1]);

                Relacao r = new Relacao();
                r.setCodigoEquipamento(idEquipamento);
                r.setCodigoMecanico(idMecanico);
                r.setCodigoManutencao(manutencaoSalva.getCodigo());

                new RelacaoController().adicionarRelacao(r);
                JOptionPane.showMessageDialog(null, "✅ Relação vinculada com sucesso!");
            }

            JOptionPane.showMessageDialog(null, "✅ Manutenção registrada!");
        }
    }

    private static void listarTodas() throws SQLException {
        List<Manutencao> lista = controller.listarManutencoes();
        StringBuilder sb = new StringBuilder("📋 Lista de Manutenções:\n\n");
        for (Manutencao m : lista) {
            sb.append("ID: ").append(m.getCodigo())
                    .append(" | Data: ").append(m.getDataManutencao())
                    .append(" | Executada: ").append(m.isExecutada() ? "Sim" : "Não")
                    .append(" | Peças: ").append(m.getPecasManutencao())
                    .append("\n");
        }
        JOptionPane.showMessageDialog(null, sb.toString());
    }

    private static void buscarPorId() throws SQLException {
        String input = JOptionPane.showInputDialog("ID da manutenção:");
        if (input != null) {
            Manutencao m = controller.buscarPorId(Integer.parseInt(input));
            if (m != null) {
                JOptionPane.showMessageDialog(null, """
                        🔍 Manutenção encontrada:
                        ID: %d
                        Data: %s
                        Executada: %s
                        Peças: %s
                        """.formatted(
                        m.getCodigo(), m.getDataManutencao(),
                        m.isExecutada() ? "Sim" : "Não",
                        m.getPecasManutencao()));
            } else {
                JOptionPane.showMessageDialog(null, "Manutenção não encontrada.");
            }
        }
    }

    private static void atualizar() throws SQLException {
        String input = JOptionPane.showInputDialog("ID da manutenção a atualizar:");
        if (input != null) {
            int id = Integer.parseInt(input);
            Manutencao m = controller.buscarPorId(id);
            if (m != null) {
                String pecas = JOptionPane.showInputDialog("Peças utilizadas:", m.getPecasManutencao());
                String data = JOptionPane.showInputDialog("Data (AAAA-MM-DD):", m.getDataManutencao().toString());
                int executada = JOptionPane.showConfirmDialog(null, "A manutenção foi executada?", "Executada", JOptionPane.YES_NO_OPTION);

                m.setPecasManutencao(pecas);
                m.setDataManutencao(Date.valueOf(data));
                m.setExecutada(executada == JOptionPane.YES_OPTION);

                controller.atualizarManutencao(m);
                JOptionPane.showMessageDialog(null, "✅ Manutenção atualizada!");
            } else {
                JOptionPane.showMessageDialog(null, "Manutenção não encontrada.");
            }
        }
    }

    private static void deletar() throws SQLException {
        String input = JOptionPane.showInputDialog("ID da manutenção a remover:");
        if (input != null) {
            int id = Integer.parseInt(input);
            Manutencao m = controller.buscarPorId(id);
            if (m != null) {
                int confirm = JOptionPane.showConfirmDialog(null,
                        "Tem certeza que deseja remover esta manutenção realizada em:\n" + m.getDataManutencao() + "?",
                        "Confirmação", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    controller.deletarManutencao(id);
                    JOptionPane.showMessageDialog(null, "🗑️ Manutenção removida com sucesso.");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Manutenção não encontrada.");
            }
        }
    }
}
