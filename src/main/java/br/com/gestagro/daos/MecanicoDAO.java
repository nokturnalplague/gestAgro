package br.com.gestagro.daos;

import br.com.gestagro.entities.Mecanico;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MecanicoDAO {

    public void inserir(Mecanico mecanico) throws SQLException {
        String sql = "INSERT INTO mecanico (nome) VALUES (?)";
        try (Connection conn = Conexao.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, mecanico.getNome());
            stmt.executeUpdate();
        }
    }

    public List<Mecanico> listarTodos() throws SQLException {
        List<Mecanico> lista = new ArrayList<>();
        String sql = "SELECT * FROM mecanico";
        try (Connection conn = Conexao.conectar(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Mecanico m = new Mecanico();
                m.setCodigo(rs.getInt("codigo"));
                m.setNome(rs.getString("nome"));
                lista.add(m);
            }
        }
        return lista;
    }

    public Mecanico buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM mecanico WHERE codigo = ?";
        try (Connection conn = Conexao.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Mecanico m = new Mecanico();
                    m.setCodigo(rs.getInt("codigo"));
                    m.setNome(rs.getString("nome"));
                    return m;
                }
            }
        }
        return null;
    }

    public void atualizar(Mecanico mecanico) throws SQLException {
        String sql = "UPDATE mecanico SET nome = ? WHERE codigo = ?";
        try (Connection conn = Conexao.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, mecanico.getNome());
            stmt.setInt(2, mecanico.getCodigo());
            stmt.executeUpdate();
        }
    }

    public void deletar(int id) throws SQLException {
        String sql = "DELETE FROM mecanico WHERE codigo = ?";
        try (Connection conn = Conexao.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}
