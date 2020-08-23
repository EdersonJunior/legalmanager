package com.legalmanager.agenda.adapter;

import com.google.gson.Gson;
import com.legalmanager.agenda.enums.AgendaType;
import com.legalmanager.agenda.model.Agenda;

public class AgendaAdapter {

    public Agenda adaptStringToAgenda(String agendaString, AgendaType tipoDeAgenda) {
        Gson gson = new Gson();
        AgendaToAdapt agendaToAdapt = gson.fromJson(agendaString, AgendaToAdapt.class);
//        if (null != agendaToAdapt && agendaToAdapt.getId() != null) {
//
//        }

        return null;
    }
}
