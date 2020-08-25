package com.legalmanager.agenda.model;

import com.legalmanager.agenda.enums.TipoAgenda;
import com.legalmanager.agenda.enums.Status;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
public class Agenda implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Getter
    private int id;

    @Getter
    @Setter
    private LocalDate agendaDate;

    @Getter
    @Setter
    private LocalDate dataInicio;

    @Getter
    @Setter
    private TipoAgenda tipo;

    @Getter
    @Setter
    private String descricao;

    @Getter
    @Setter
    private Status status;
}