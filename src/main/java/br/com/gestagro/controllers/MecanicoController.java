package br.com.gestagro.controllers;

import br.com.gestagro.entities.Mecanico;
import br.com.gestagro.services.MecanicoService;

import java.sql.SQLException;
import java.util.List;

public class MecanicoController {
    private final MecanicoService service = new MecanicoService();

    public void adicionarMecanico(Mecanico mecanico) throws SQLException {
        service.cadastrarMecanico(mecanico);
    }

    public List<Mecanico> listarMecanicos() throws SQLException {
        return service.listarMecanicos();
    }

    public Mecanico buscarPorId(int id) throws SQLException {
        return service.buscarPorId(id);
    }

    public void atualizarMecanico(Mecanico mecanico) throws SQLException {
        service.atualizar(mecanico);
    }

    public void deletarMecanico(int id) throws SQLException {
        service.deletar(id);
    }
}
