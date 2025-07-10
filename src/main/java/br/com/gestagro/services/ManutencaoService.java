package br.com.gestagro.services;

import br.com.gestagro.daos.ManutencaoDAO;
import br.com.gestagro.entities.Manutencao;

import java.sql.SQLException;
import java.util.List;

public class ManutencaoService {
    private final ManutencaoDAO dao = new ManutencaoDAO();

    public void cadastrarManutencao(Manutencao manutencao) throws SQLException {
        dao.inserir(manutencao);
    }

    public List<Manutencao> listarManutencoes() throws SQLException {
        return dao.listarTodos();
    }

    public Manutencao buscarPorId(int id) throws SQLException {
        return dao.buscarPorId(id);
    }

    public void atualizar(Manutencao manutencao) throws SQLException {
        dao.atualizar(manutencao);
    }

    public void deletar(int id) throws SQLException {
        dao.deletar(id);
    }
}
