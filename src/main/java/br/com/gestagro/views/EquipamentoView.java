package br.com.gestagro.views;

import br.com.gestagro.controllers.EquipamentoController;
import br.com.gestagro.entities.Equipamento;

import javax.swing.*;
import java.sql.SQLException;
import java.util.List;

public class EquipamentoView {

    private static final EquipamentoController controller = new EquipamentoController();

    public static void menu() {
        String menu = """
                🧰 MENU EQUIPAMENTO
                
                1 - Cadastrar
                2 - Listar todos
                3 - Buscar por ID
                4 - Atualizar
                5 - Remover
                0 - Voltar
                """;

        while (true) {
            String opcao = JOptionPane.showInputDialog(null, menu, "GestAgro - Equipamentos", JOptionPane.PLAIN_MESSAGE);
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
        String tipo = JOptionPane.showInputDialog("Tipo:");
        String marca = JOptionPane.showInputDialog("Marca:");
        String modelo = JOptionPane.showInputDialog("Modelo:");
        String feitas = JOptionPane.showInputDialog("Manutenções feitas:");
        String fazer = JOptionPane.showInputDialog("Manutenções a fazer:");

        if (tipo != null && marca != null && modelo != null) {
            Equipamento e = new Equipamento();
            e.setTipo(tipo);
            e.setMarca(marca);
            e.setModelo(modelo);
            e.setManutencaoFeitas(feitas != null ? feitas : "");
            e.setManutencaoFazer(fazer != null ? fazer : "");
            controller.adicionarEquipamento(e);
            JOptionPane.showMessageDialog(null, "✅ Equipamento cadastrado com sucesso!");
        }
    }

    private static void listarTodos() throws SQLException {
        List<Equipamento> lista = controller.listarEquipamentos();
        StringBuilder sb = new StringBuilder("📋 Lista de Equipamentos:\n\n");
        for (Equipamento e : lista) {
            sb.append("ID: ").append(e.getCodigo())
                    .append(" | Tipo: ").append(e.getTipo())
                    .append(" | Marca: ").append(e.getMarca())
                    .append(" | Modelo: ").append(e.getModelo())
                    .append("\n");
        }
        JOptionPane.showMessageDialog(null, sb.toString());
    }

    private static void buscarPorId() throws SQLException {
        String input = JOptionPane.showInputDialog("ID do equipamento:");
        if (input != null) {
            Equipamento e = controller.buscarPorId(Integer.parseInt(input));
            if (e != null) {
                JOptionPane.showMessageDialog(null, """
                        🔍 Equipamento encontrado:
                        ID: %d
                        Tipo: %s
                        Marca: %s
                        Modelo: %s
                        Feitas: %s
                        A fazer: %s
                        """.formatted(
                        e.getCodigo(), e.getTipo(), e.getMarca(), e.getModelo(),
                        e.getManutencaoFeitas(), e.getManutencaoFazer()));
            } else {
                JOptionPane.showMessageDialog(null, "Equipamento não encontrado.");
            }
        }
    }

    private static void atualizar() throws SQLException {
        String input = JOptionPane.showInputDialog("ID do equipamento a atualizar:");
        if (input != null) {
            int id = Integer.parseInt(input);
            Equipamento e = controller.buscarPorId(id);
            if (e != null) {
                String tipo = JOptionPane.showInputDialog("Tipo:", e.getTipo());
                String marca = JOptionPane.showInputDialog("Marca:", e.getMarca());
                String modelo = JOptionPane.showInputDialog("Modelo:", e.getModelo());
                String feitas = JOptionPane.showInputDialog("Manutenções feitas:", e.getManutencaoFeitas());
                String fazer = JOptionPane.showInputDialog("Manutenções a fazer:", e.getManutencaoFazer());

                e.setTipo(tipo);
                e.setMarca(marca);
                e.setModelo(modelo);
                e.setManutencaoFeitas(feitas);
                e.setManutencaoFazer(fazer);

                controller.atualizarEquipamento(e);
                JOptionPane.showMessageDialog(null, "✅ Equipamento atualizado!");
            } else {
                JOptionPane.showMessageDialog(null, "Equipamento não encontrado.");
            }
        }
    }

    private static void deletar() throws SQLException {
        String input = JOptionPane.showInputDialog("ID do equipamento a remover:");
        if (input != null) {
            int id = Integer.parseInt(input);
            Equipamento e = controller.buscarPorId(id);
            if (e != null) {
                int confirm = JOptionPane.showConfirmDialog(null,
                        "Tem certeza que deseja remover o equipamento:\n" + e.getModelo() + "?", "Confirmação", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    controller.deletarEquipamento(id);
                    JOptionPane.showMessageDialog(null, "🗑️ Equipamento removido com sucesso.");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Equipamento não encontrado.");
            }
        }
    }
}
