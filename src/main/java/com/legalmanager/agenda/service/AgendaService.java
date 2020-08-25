package com.legalmanager.agenda.service;

import com.google.gson.Gson;
import com.legalmanager.agenda.adapter.AgendaContent;
import com.legalmanager.agenda.adapter.AgendaToAdapt;
import com.legalmanager.agenda.enums.Status;
import com.legalmanager.agenda.enums.TipoAgenda;
import com.legalmanager.agenda.exception.AgendaException;
import com.legalmanager.agenda.exception.TipoAgendaException;
import com.legalmanager.agenda.interfaces.IAgendaService;
import com.legalmanager.agenda.model.Agenda;
import com.legalmanager.agenda.repository.IAgendaServiceRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class AgendaService implements IAgendaService {

    @Autowired
    private IAgendaServiceRepository repository;

    public void getAgendas(String tipoAgenda) throws AgendaException {
        List<AgendaToAdapt> agendasToAdapt = requestAgendas();
        gerarAgendas(tipoAgenda, agendasToAdapt);
    }

    private List<AgendaToAdapt> requestAgendas() throws AgendaException {
        try {
            URL url = new URL("http://test.legalmanager.com.br:8080/forum/v1/agendas");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Accept", "application/json");

            if (connection.getResponseCode() != 200) {
                throw new RuntimeException("Erro : "
                        + connection.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (connection.getInputStream())));

            StringBuffer sb = new StringBuffer("");

            String output;
            while ((output = br.readLine()) != null) {
                System.out.println(output);
                sb.append(output);
            }

            connection.disconnect();

            return adaptar(sb.toString());

        } catch (Exception e) {
            throw new AgendaException("Erro ao tentar buscar agendas");
        }
    }

    @NotNull
    private void gerarAgendas(String tipoAgenda, List<AgendaToAdapt> agendaContent) throws TipoAgendaException {
        List<Agenda> agendas = new ArrayList<>();

        if (tipoAgenda.equalsIgnoreCase(TipoAgenda.ESCRITORIO.name())) {
            agendaContent.forEach(toAdapt -> {
                agendas.add(gerarAgendasEscritorio(toAdapt));
            });

        } else if (tipoAgenda.equalsIgnoreCase(TipoAgenda.CLIENTE.name())) {
            agendaContent.forEach(toAdapt -> {
                agendas.add(gerarAgendasCliente(toAdapt));
            });

        } else if (tipoAgenda.equalsIgnoreCase(TipoAgenda.CARTORIO.name())) {
            agendaContent.forEach(toAdapt -> {
                agendas.add(gerarAgendasCartorio(toAdapt));
            });

        } else if (tipoAgenda.equalsIgnoreCase(TipoAgenda.FORUM.name())) {
            agendaContent.forEach(toAdapt -> {
                agendas.add(gerarAgendasForum(toAdapt));
            });

        } else {
            throw new TipoAgendaException("Tipo de agenda inválido");
        }

        repository.saveAll(agendas);
    }

    @Override
    public Agenda gerarAgendasEscritorio(AgendaToAdapt agendaToAdapt) {
        Agenda agenda = new Agenda() ;
        LocalDate dataInicio = LocalDate.parse(agendaToAdapt.getData());
        agenda.setDataInicio(dataInicio);
        agenda.setAgendaDate(dataInicio);

        return agenda;
    }

    @Override
    public Agenda gerarAgendasCliente(AgendaToAdapt agendaToAdapt) {
        Agenda agenda = new Agenda() ;
        LocalDate dataInicio = LocalDate.parse(agendaToAdapt.getData());
        agenda.setAgendaDate(dataInicio);
        dataInicio = dataInicio.minusDays(1);
        agenda.setStatus(Status.ENVIAR_EMAIL);
        agenda.setDataInicio(dataInicio);

        return agenda;
    }

    @Override
    public Agenda gerarAgendasCartorio(AgendaToAdapt agendaToAdapt) {
        Agenda agenda = new Agenda() ;
        LocalDate dataInicio = LocalDate.parse(agendaToAdapt.getData());
        agenda.setAgendaDate(dataInicio);
        dataInicio = dataInicio.minusDays(2);
        agenda.setStatus(Status.AGENDAR_CARTORIO);
        agenda.setDataInicio(dataInicio);

        return agenda;
    }

    @Override
    public Agenda gerarAgendasForum(AgendaToAdapt agendaToAdapt) {
        Agenda agenda = new Agenda() ;
        LocalDate dataInicio = LocalDate.parse(agendaToAdapt.getData());
        agenda.setAgendaDate(dataInicio);
        dataInicio = dataInicio.minusDays(3);
        agenda.setStatus(Status.ENVIADO_FORUM);
        agenda.setDataInicio(dataInicio);

        return agenda;
    }

    public List<AgendaToAdapt> adaptar(String agendaString) {
        AgendaContent agendaContent = converterParaGson(agendaString);
        return Arrays.asList(agendaContent.getContent());
    }

    private AgendaContent converterParaGson(String agendaString) {
        Gson gson = new Gson();
        AgendaContent agendaToAdapt = gson.fromJson(agendaString, AgendaContent.class);
        return agendaToAdapt;
    }
}
