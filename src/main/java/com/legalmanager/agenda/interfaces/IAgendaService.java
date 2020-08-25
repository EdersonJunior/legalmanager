package com.legalmanager.agenda.interfaces;

import com.legalmanager.agenda.adapter.AgendaToAdapt;
import com.legalmanager.agenda.model.Agenda;

import java.util.List;

public interface IAgendaService {

    public Agenda gerarAgendasEscritorio(AgendaToAdapt agendaToAdapt);

    public Agenda gerarAgendasCartorio(AgendaToAdapt agendaToAdapt);

    public Agenda gerarAgendasForum(AgendaToAdapt agendaToAdapt);

    public Agenda gerarAgendasCliente(AgendaToAdapt agendaToAdapt);
}
