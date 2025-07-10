package br.com.gestagro.controllers;

import br.com.gestagro.entities.Relacao;
import br.com.gestagro.services.RelacaoService;

import java.sql.SQLException;
import java.util.List;

public class RelacaoController {
    private final RelacaoService service = new RelacaoService();

    public void adicionarRelacao(Relacao relacao) throws SQLException {
        service.cadastrarRelacao(relacao);
    }

    public List<Relacao> listarRelacoes() throws SQLException {
        return service.listarRelacoes();
    }

    public Relacao buscarPorId(int id) throws SQLException {
        return service.buscarPorId(id);
    }

    public void atualizarRelacao(Relacao relacao) throws SQLException {
        service.atualizar(relacao);
    }

    public void deletarRelacao(int id) throws SQLException {
        service.deletar(id);
    }
}
