package com.legalmanager.agenda.service;

import com.legalmanager.agenda.adapter.AgendaToAdapt;
import com.legalmanager.agenda.model.Agenda;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class AgendaServiceTest {

    @Autowired
    AgendaService service;

    @Test
    void DeveGerarAgendaClienteComDataInicioComUmDiaAMenos() {
        service = new AgendaService();
        AgendaToAdapt agendaToAdapt = new AgendaToAdapt();
        LocalDate localDate = LocalDate.parse("2020-05-31");
        agendaToAdapt.setData(localDate.toString());
        Agenda agenda = service.gerarAgendasCliente(agendaToAdapt);
        LocalDate expectedDate = agenda.getDataInicio();
        Assert.assertTrue(expectedDate.equals(localDate.minusDays(1)));
    }

    @Test
    void DeveGerarAgendasCartorioComDataInicioComDoisDiasAMenos() {
        service = new AgendaService();
        AgendaToAdapt agendaToAdapt = new AgendaToAdapt();
        LocalDate localDate = LocalDate.parse("2020-05-31");
        agendaToAdapt.setData(localDate.toString());
        Agenda agenda = service.gerarAgendasCartorio(agendaToAdapt);
        LocalDate expectedDate = agenda.getDataInicio();
        Assert.assertTrue(expectedDate.equals(localDate.minusDays(2)));
    }

    @Test
    void DeveGerarAgendaForumComDataInicioComTresDiasAMenos() {
        service = new AgendaService();
        AgendaToAdapt agendaToAdapt = new AgendaToAdapt();
        LocalDate localDate = LocalDate.parse("2020-05-31");
        agendaToAdapt.setData(localDate.toString());
        Agenda agenda = service.gerarAgendasForum(agendaToAdapt);
        LocalDate expectedDate = agenda.getDataInicio();
        Assert.assertTrue(expectedDate.equals(localDate.minusDays(3)));
    }
}