package br.com.gestagro.services;

import br.com.gestagro.daos.MecanicoDAO;
import br.com.gestagro.entities.Mecanico;

import java.sql.SQLException;
import java.util.List;

public class MecanicoService {
    private final MecanicoDAO dao = new MecanicoDAO();

    public void cadastrarMecanico(Mecanico mecanico) throws SQLException {
        dao.inserir(mecanico);
    }

    public List<Mecanico> listarMecanicos() throws SQLException {
        return dao.listarTodos();
    }

    public Mecanico buscarPorId(int id) throws SQLException {
        return dao.buscarPorId(id);
    }

    public void atualizar(Mecanico mecanico) throws SQLException {
        dao.atualizar(mecanico);
    }

    public void deletar(int id) throws SQLException {
        dao.deletar(id);
    }
}
