package com.legalmanager.agenda.controller;

import com.legalmanager.agenda.exception.AgendaException;
import com.legalmanager.agenda.service.AgendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/agendas")
@CrossOrigin(origins = "*")
public class AgendaController {

    @Autowired
    private AgendaService agendaService;

    @GetMapping
    public HttpStatus getOfficeAgenda(@RequestParam(required = true) String tipoAgenda) throws AgendaException {
        agendaService.getAgendas(tipoAgenda);
        return HttpStatus.OK;
    }
}
