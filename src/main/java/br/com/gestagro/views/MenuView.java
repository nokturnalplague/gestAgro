package br.com.gestagro.views;

import javax.swing.*;

public class MenuView {

    public static void exibirMenu() {
        int opcao;
        do {
            String menu = """
                    🛠️  GESTAGRO - MENU PRINCIPAL
                    
                    1 - Gerenciar Usuários
                    2 - Gerenciar Equipamentos
                    3 - Gerenciar Mecânicos
                    4 - Gerenciar Manutenções
                    0 - Sair
                    """;

            String input = JOptionPane.showInputDialog(null, menu, "GestAgro", JOptionPane.QUESTION_MESSAGE);

            if (input == null) break; // Cancelar

            switch (input) {
                case "1" -> UsuarioView.menu();
                case "2" -> EquipamentoView.menu();
                case "3" -> MecanicoView.menu();
                case "4" -> ManutencaoView.menu();
                case "0" -> JOptionPane.showMessageDialog(null, "Encerrando o sistema...");
                default  -> JOptionPane.showMessageDialog(null, "Opção inválida!");
            }

        } while (true);
    }
}
