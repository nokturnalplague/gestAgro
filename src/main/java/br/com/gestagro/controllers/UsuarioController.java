package br.com.gestagro.controllers;

import br.com.gestagro.entities.Usuario;
import br.com.gestagro.services.UsuarioService;

import java.sql.SQLException;
import java.util.List;

public class UsuarioController {
    private final UsuarioService service = new UsuarioService();

    public void adicionarUsuario(Usuario usuario) throws SQLException {
        service.cadastrarUsuario(usuario);
    }

    public List<Usuario> listarUsuarios() throws SQLException {
        return service.listarUsuarios();
    }

    public Usuario buscarPorId(int id) throws SQLException {
        return service.buscarPorId(id);
    }

    public void atualizarUsuario(Usuario usuario) throws SQLException {
        service.atualizar(usuario);
    }

    public void deletarUsuario(int id) throws SQLException {
        service.deletar(id);
    }
}
