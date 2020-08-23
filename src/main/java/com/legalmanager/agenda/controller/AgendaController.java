package com.legalmanager.agenda.controller;

import com.legalmanager.agenda.exception.AgendaException;
import com.legalmanager.agenda.service.AgendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/agenda")
@CrossOrigin(origins = "*")
public class AgendaController {

    @Autowired
    private AgendaService agendaService;

    @GetMapping("/test")
    public void getOfficeAgenda() throws AgendaException {
        agendaService.getAgendas();
    }
}
