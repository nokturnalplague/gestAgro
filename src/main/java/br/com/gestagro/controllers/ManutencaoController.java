package br.com.gestagro.controllers;

import br.com.gestagro.entities.Manutencao;
import br.com.gestagro.services.ManutencaoService;

import java.sql.SQLException;
import java.util.List;

public class ManutencaoController {
    private final ManutencaoService service = new ManutencaoService();

    public void adicionarManutencao(Manutencao manutencao) throws SQLException {
        service.cadastrarManutencao(manutencao);
    }

    public List<Manutencao> listarManutencoes() throws SQLException {
        return service.listarManutencoes();
    }

    public Manutencao buscarPorId(int id) throws SQLException {
        return service.buscarPorId(id);
    }

    public void atualizarManutencao(Manutencao manutencao) throws SQLException {
        service.atualizar(manutencao);
    }

    public void deletarManutencao(int id) throws SQLException {
        service.deletar(id);
    }
}
