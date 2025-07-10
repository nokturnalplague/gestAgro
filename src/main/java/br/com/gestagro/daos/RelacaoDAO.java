package br.com.gestagro.daos;

import br.com.gestagro.entities.Relacao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RelacaoDAO {

    public void inserir(Relacao relacao) throws SQLException {
        String sql = "INSERT INTO relacao (codigo_manutencao, codigo_mecanico, codigo_equipamento) VALUES (?, ?, ?)";
        try (Connection conn = Conexao.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, relacao.getCodigoManutencao());
            stmt.setInt(2, relacao.getCodigoMecanico());
            stmt.setInt(3, relacao.getCodigoEquipamento());
            stmt.executeUpdate();
        }
    }

    public List<Relacao> listarTodos() throws SQLException {
        List<Relacao> lista = new ArrayList<>();
        String sql = "SELECT * FROM relacao";
        try (Connection conn = Conexao.conectar(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Relacao r = new Relacao();
                r.setCodigo(rs.getInt("codigo"));
                r.setCodigoManutencao(rs.getInt("codigo_manutencao"));
                r.setCodigoMecanico(rs.getInt("codigo_mecanico"));
                r.setCodigoEquipamento(rs.getInt("codigo_equipamento"));
                lista.add(r);
            }
        }
        return lista;
    }

    public Relacao buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM relacao WHERE codigo = ?";
        try (Connection conn = Conexao.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Relacao r = new Relacao();
                    r.setCodigo(rs.getInt("codigo"));
                    r.setCodigoManutencao(rs.getInt("codigo_manutencao"));
                    r.setCodigoMecanico(rs.getInt("codigo_mecanico"));
                    r.setCodigoEquipamento(rs.getInt("codigo_equipamento"));
                    return r;
                }
            }
        }
        return null;
    }

    public void atualizar(Relacao relacao) throws SQLException {
        String sql = "UPDATE relacao SET codigo_manutencao = ?, codigo_mecanico = ?, codigo_equipamento = ? WHERE codigo = ?";
        try (Connection conn = Conexao.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, relacao.getCodigoManutencao());
            stmt.setInt(2, relacao.getCodigoMecanico());
            stmt.setInt(3, relacao.getCodigoEquipamento());
            stmt.setInt(4, relacao.getCodigo());
            stmt.executeUpdate();
        }
    }

    public void deletar(int id) throws SQLException {
        String sql = "DELETE FROM relacao WHERE codigo = ?";
        try (Connection conn = Conexao.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}
