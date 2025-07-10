package br.com.gestagro.daos;

import java.sql.Connection;
import br.com.gestagro.daos.Conexao;

public class TesteConexao {
    public static void main(String[] args) {
        try (Connection conn = Conexao.conectar()) {
            if (conn != null) {
                System.out.println("Conexão realizada com sucesso!");
            } else {
                System.out.println("Conexão falhou (retornou null).");
            }
        } catch (Exception e) {
            System.out.println("Erro ao tentar conectar: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
