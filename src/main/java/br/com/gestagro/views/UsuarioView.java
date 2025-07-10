package br.com.gestagro.views;

import br.com.gestagro.controllers.UsuarioController;
import br.com.gestagro.entities.Usuario;

import javax.swing.*;
import java.sql.SQLException;
import java.util.List;

public class UsuarioView {

    private static final UsuarioController controller = new UsuarioController();

    public static void menu() {
        String menu = """
                👤 MENU USUÁRIO
                
                1 - Cadastrar
                2 - Listar todos
                3 - Buscar por ID
                4 - Atualizar
                5 - Remover
                0 - Voltar
                """;

        while (true) {
            String opcao = JOptionPane.showInputDialog(null, menu, "GestAgro - Usuários", JOptionPane.PLAIN_MESSAGE);
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
        String nome = JOptionPane.showInputDialog("Nome:");
        String funcao = JOptionPane.showInputDialog("Função:");

        if (nome != null && funcao != null) {
            Usuario u = new Usuario();
            u.setNome(nome);
            u.setFuncao(funcao);
            controller.adicionarUsuario(u);
            JOptionPane.showMessageDialog(null, "✅ Usuário cadastrado com sucesso!");
        }
    }

    private static void listarTodos() throws SQLException {
        List<Usuario> lista = controller.listarUsuarios();
        StringBuilder sb = new StringBuilder("📋 Lista de Usuários:\n\n");
        for (Usuario u : lista) {
            sb.append("ID: ").append(u.getCodigo())
                    .append(" | Nome: ").append(u.getNome())
                    .append(" | Função: ").append(u.getFuncao())
                    .append("\n");
        }
        JOptionPane.showMessageDialog(null, sb.toString());
    }

    private static void buscarPorId() throws SQLException {
        String input = JOptionPane.showInputDialog("ID do usuário:");
        if (input != null) {
            Usuario u = controller.buscarPorId(Integer.parseInt(input));
            if (u != null) {
                JOptionPane.showMessageDialog(null, """
                        🔍 Usuário encontrado:
                        ID: %d
                        Nome: %s
                        Função: %s
                        """.formatted(u.getCodigo(), u.getNome(), u.getFuncao()));
            } else {
                JOptionPane.showMessageDialog(null, "Usuário não encontrado.");
            }
        }
    }

    private static void atualizar() throws SQLException {
        String input = JOptionPane.showInputDialog("ID do usuário a atualizar:");
        if (input != null) {
            int id = Integer.parseInt(input);
            Usuario u = controller.buscarPorId(id);
            if (u != null) {
                String novoNome = JOptionPane.showInputDialog("Novo nome:", u.getNome());
                String novaFuncao = JOptionPane.showInputDialog("Nova função:", u.getFuncao());

                if (novoNome != null && novaFuncao != null) {
                    u.setNome(novoNome);
                    u.setFuncao(novaFuncao);
                    controller.atualizarUsuario(u);
                    JOptionPane.showMessageDialog(null, "✅ Usuário atualizado!");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Usuário não encontrado.");
            }
        }
    }

    private static void deletar() throws SQLException {
        String input = JOptionPane.showInputDialog("ID do usuário a remover:");
        if (input != null) {
            int id = Integer.parseInt(input);
            Usuario u = controller.buscarPorId(id);
            if (u != null) {
                int confirm = JOptionPane.showConfirmDialog(null,
                        "Tem certeza que deseja remover o usuário:\n" + u.getNome() + "?", "Confirmação", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    controller.deletarUsuario(id);
                    JOptionPane.showMessageDialog(null, "🗑️ Usuário removido com sucesso.");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Usuário não encontrado.");
            }
        }
    }
}
