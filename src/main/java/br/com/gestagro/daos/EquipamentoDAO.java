package br.com.gestagro.daos;

import br.com.gestagro.entities.Equipamento;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EquipamentoDAO {

    public void inserir(Equipamento equipamento) throws SQLException {
        String sql = "INSERT INTO equipamento (tipo, marca, modelo, manutencao_feitas, manutencao_fazer) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = Conexao.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, equipamento.getTipo());
            stmt.setString(2, equipamento.getMarca());
            stmt.setString(3, equipamento.getModelo());
            stmt.setString(4, equipamento.getManutencaoFeitas());
            stmt.setString(5, equipamento.getManutencaoFazer());
            stmt.executeUpdate();
        }
    }

    public List<Equipamento> listarTodos() throws SQLException {
        List<Equipamento> lista = new ArrayList<>();
        String sql = "SELECT * FROM equipamento";
        try (Connection conn = Conexao.conectar(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Equipamento e = new Equipamento();
                e.setCodigo(rs.getInt("codigo"));
                e.setTipo(rs.getString("tipo"));
                e.setMarca(rs.getString("marca"));
                e.setModelo(rs.getString("modelo"));
                e.setManutencaoFeitas(rs.getString("manutencao_feitas"));
                e.setManutencaoFazer(rs.getString("manutencao_fazer"));
                lista.add(e);
            }
        }
        return lista;
    }

    public Equipamento buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM equipamento WHERE codigo = ?";
        try (Connection conn = Conexao.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Equipamento e = new Equipamento();
                    e.setCodigo(rs.getInt("codigo"));
                    e.setTipo(rs.getString("tipo"));
                    e.setMarca(rs.getString("marca"));
                    e.setModelo(rs.getString("modelo"));
                    e.setManutencaoFeitas(rs.getString("manutencao_feitas"));
                    e.setManutencaoFazer(rs.getString("manutencao_fazer"));
                    return e;
                }
            }
        }
        return null;
    }

    public void atualizar(Equipamento equipamento) throws SQLException {
        String sql = "UPDATE equipamento SET tipo = ?, marca = ?, modelo = ?, manutencao_feitas = ?, manutencao_fazer = ? WHERE codigo = ?";
        try (Connection conn = Conexao.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, equipamento.getTipo());
            stmt.setString(2, equipamento.getMarca());
            stmt.setString(3, equipamento.getModelo());
            stmt.setString(4, equipamento.getManutencaoFeitas());
            stmt.setString(5, equipamento.getManutencaoFazer());
            stmt.setInt(6, equipamento.getCodigo());
            stmt.executeUpdate();
        }
    }

    public void deletar(int id) throws SQLException {
        String sql = "DELETE FROM equipamento WHERE codigo = ?";
        try (Connection conn = Conexao.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}
