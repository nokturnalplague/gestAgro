package br.com.gestagro.services;

import br.com.gestagro.daos.UsuarioDAO;
import br.com.gestagro.entities.Usuario;

import java.sql.SQLException;
import java.util.List;

public class UsuarioService {
    private final UsuarioDAO dao = new UsuarioDAO();

    public void cadastrarUsuario(Usuario usuario) throws SQLException {
        dao.inserir(usuario);
    }

    public List<Usuario> listarUsuarios() throws SQLException {
        return dao.listarTodos();
    }

    public Usuario buscarPorId(int id) throws SQLException {
        return dao.buscarPorId(id);
    }

    public void atualizar(Usuario usuario) throws SQLException {
        dao.atualizar(usuario);
    }

    public void deletar(int id) throws SQLException {
        dao.deletar(id);
    }
}
