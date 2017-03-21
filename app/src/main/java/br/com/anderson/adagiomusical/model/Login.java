package br.com.anderson.adagiomusical.model;

/**
 * Created by andersonmacedo on 12/03/17.
 */

public class Login {

    private long id;
    private String usuario;
    private String senha;

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
