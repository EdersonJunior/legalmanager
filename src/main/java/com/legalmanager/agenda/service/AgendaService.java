package com.legalmanager.agenda.service;

import com.google.gson.Gson;
import com.legalmanager.agenda.adapter.AgendaContent;
import com.legalmanager.agenda.adapter.AgendaToAdapt;
import com.legalmanager.agenda.exception.AgendaException;
import com.legalmanager.agenda.model.Agenda;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

@Service
public class AgendaService {

    public void getOfficeAgenda() throws AgendaException {
        getAgendas();
    }

    public List<Agenda> getAgendas() throws AgendaException {
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

            adaptar(sb.toString());

        } catch (Exception e) {
            throw new AgendaException("Erro ao tentar buscar agendas");
        }

        return null;
    }

    public void adaptar(String agendaString) {
        Gson gson = new Gson();
        AgendaContent agendaToAdapt = gson.fromJson(agendaString, AgendaContent.class);
        System.out.println(agendaToAdapt);
    }
}
