package com.legalmanager.agenda.enums;

public enum TipoAgenda {

    ESCRITORIO("1"),
    CLIENTE("2"),
    CARTORIO("3"),
    FORUM("4");

    private String tipoAgenda;

    TipoAgenda(String tipoAgenda) {
        this.tipoAgenda = tipoAgenda;
    }

    public String getTipoAgenda() {
        return tipoAgenda;
    }
}
