package br.com.gestagro.controllers;

import br.com.gestagro.entities.Equipamento;
import br.com.gestagro.services.EquipamentoService;

import java.sql.SQLException;
import java.util.List;

public class EquipamentoController {
    private final EquipamentoService service = new EquipamentoService();

    public void adicionarEquipamento(Equipamento equipamento) throws SQLException {
        service.cadastrarEquipamento(equipamento);
    }

    public List<Equipamento> listarEquipamentos() throws SQLException {
        return service.listarEquipamentos();
    }

    public Equipamento buscarPorId(int id) throws SQLException {
        return service.buscarPorId(id);
    }

    public void atualizarEquipamento(Equipamento equipamento) throws SQLException {
        service.atualizar(equipamento);
    }

    public void deletarEquipamento(int id) throws SQLException {
        service.deletar(id);
    }
}
