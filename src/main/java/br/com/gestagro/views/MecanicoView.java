package br.com.gestagro.views;

import br.com.gestagro.controllers.MecanicoController;
import br.com.gestagro.entities.Mecanico;

import javax.swing.*;
import java.sql.SQLException;
import java.util.List;

public class MecanicoView {

    private static final MecanicoController controller = new MecanicoController();

    public static void menu() {
        String menu = """
                🔧 MENU MECÂNICO
                
                1 - Cadastrar
                2 - Listar todos
                3 - Buscar por ID
                4 - Atualizar
                5 - Remover
                0 - Voltar
                """;

        while (true) {
            String opcao = JOptionPane.showInputDialog(null, menu, "GestAgro - Mecânicos", JOptionPane.PLAIN_MESSAGE);
            if (opcao == null || opcao.equals("0")) break;

            try {
                switch (opcao) {
                    case "1" -> cadastrar();
                    case "2" -> listarTodos();
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
        String nome = JOptionPane.showInputDialog("Nome do mecânico:");
        if (nome != null && !nome.isBlank()) {
            Mecanico m = new Mecanico();
            m.setNome(nome);
            controller.adicionarMecanico(m);
            JOptionPane.showMessageDialog(null, "✅ Mecânico cadastrado com sucesso!");
        }
    }

    private static void listarTodos() throws SQLException {
        List<Mecanico> lista = controller.listarMecanicos();
        StringBuilder sb = new StringBuilder("📋 Lista de Mecânicos:\n\n");
        for (Mecanico m : lista) {
            sb.append("ID: ").append(m.getCodigo())
                    .append(" | Nome: ").append(m.getNome())
                    .append("\n");
        }
        JOptionPane.showMessageDialog(null, sb.toString());
    }

    private static void buscarPorId() throws SQLException {
        String input = JOptionPane.showInputDialog("ID do mecânico:");
        if (input != null) {
            Mecanico m = controller.buscarPorId(Integer.parseInt(input));
            if (m != null) {
                JOptionPane.showMessageDialog(null, """
                        🔍 Mecânico encontrado:
                        ID: %d
                        Nome: %s
                        """.formatted(m.getCodigo(), m.getNome()));
            } else {
                JOptionPane.showMessageDialog(null, "Mecânico não encontrado.");
            }
        }
    }

    private static void atualizar() throws SQLException {
        String input = JOptionPane.showInputDialog("ID do mecânico a atualizar:");
        if (input != null) {
            int id = Integer.parseInt(input);
            Mecanico m = controller.buscarPorId(id);
            if (m != null) {
                String novoNome = JOptionPane.showInputDialog("Novo nome:", m.getNome());
                if (novoNome != null && !novoNome.isBlank()) {
                    m.setNome(novoNome);
                    controller.atualizarMecanico(m);
                    JOptionPane.showMessageDialog(null, "✅ Mecânico atualizado!");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Mecânico não encontrado.");
            }
        }
    }

    private static void deletar() throws SQLException {
        String input = JOptionPane.showInputDialog("ID do mecânico a remover:");
        if (input != null) {
            int id = Integer.parseInt(input);
            Mecanico m = controller.buscarPorId(id);
            if (m != null) {
                int confirm = JOptionPane.showConfirmDialog(null,
                        "Tem certeza que deseja remover o mecânico:\n" + m.getNome() + "?",
                        "Confirmação", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    controller.deletarMecanico(id);
                    JOptionPane.showMessageDialog(null, "🗑️ Mecânico removido com sucesso.");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Mecânico não encontrado.");
            }
        }
    }
}
