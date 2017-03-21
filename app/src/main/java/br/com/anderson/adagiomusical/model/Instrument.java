package br.com.anderson.adagiomusical.model;

import java.io.Serializable;

/**
 * Created by andersonmacedo on 19/03/17.
 */

public class Instrument implements Serializable {

    private Long id;
    private String modelo;
    private String marca;
    private String qtde;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQtde() {
        return qtde;
    }

    public void setQtde(String qtde) {
        this.qtde = qtde;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

}
