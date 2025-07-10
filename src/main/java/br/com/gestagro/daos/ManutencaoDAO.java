package br.com.gestagro.daos;

import br.com.gestagro.entities.Manutencao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ManutencaoDAO {

    public void inserir(Manutencao manutencao) throws SQLException {
        String sql = "INSERT INTO manutencao (pecas_manutencao, data_manutencao, executada) VALUES (?, ?, ?)";
        try (Connection conn = Conexao.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, manutencao.getPecasManutencao());
            stmt.setDate(2, manutencao.getDataManutencao());
            stmt.setBoolean(3, manutencao.isExecutada());
            stmt.executeUpdate();
        }
    }

    public List<Manutencao> listarTodos() throws SQLException {
        List<Manutencao> lista = new ArrayList<>();
        String sql = "SELECT * FROM manutencao";
        try (Connection conn = Conexao.conectar(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Manutencao m = new Manutencao();
                m.setCodigo(rs.getInt("codigo"));
                m.setPecasManutencao(rs.getString("pecas_manutencao"));
                m.setDataManutencao(rs.getDate("data_manutencao"));
                m.setExecutada(rs.getBoolean("executada"));
                lista.add(m);
            }
        }
        return lista;
    }

    public Manutencao buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM manutencao WHERE codigo = ?";
        try (Connection conn = Conexao.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Manutencao m = new Manutencao();
                    m.setCodigo(rs.getInt("codigo"));
                    m.setPecasManutencao(rs.getString("pecas_manutencao"));
                    m.setDataManutencao(rs.getDate("data_manutencao"));
                    m.setExecutada(rs.getBoolean("executada"));
                    return m;
                }
            }
        }
        return null;
    }

    public void atualizar(Manutencao manutencao) throws SQLException {
        String sql = "UPDATE manutencao SET pecas_manutencao = ?, data_manutencao = ?, executada = ? WHERE codigo = ?";
        try (Connection conn = Conexao.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, manutencao.getPecasManutencao());
            stmt.setDate(2, manutencao.getDataManutencao());
            stmt.setBoolean(3, manutencao.isExecutada());
            stmt.setInt(4, manutencao.getCodigo());
            stmt.executeUpdate();
        }
    }

    public void deletar(int id) throws SQLException {
        String sql = "DELETE FROM manutencao WHERE codigo = ?";
        try (Connection conn = Conexao.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}
