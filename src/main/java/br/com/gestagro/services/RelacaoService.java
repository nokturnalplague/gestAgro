package br.com.gestagro.services;

import br.com.gestagro.daos.RelacaoDAO;
import br.com.gestagro.entities.Relacao;

import java.sql.SQLException;
import java.util.List;

public class RelacaoService {
    private final RelacaoDAO dao = new RelacaoDAO();

    public void cadastrarRelacao(Relacao relacao) throws SQLException {
        dao.inserir(relacao);
    }

    public List<Relacao> listarRelacoes() throws SQLException {
        return dao.listarTodos();
    }

    public Relacao buscarPorId(int id) throws SQLException {
        return dao.buscarPorId(id);
    }

    public void atualizar(Relacao relacao) throws SQLException {
        dao.atualizar(relacao);
    }

    public void deletar(int id) throws SQLException {
        dao.deletar(id);
    }
}
