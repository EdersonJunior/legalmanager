package com.legalmanager.agenda.model;

import com.legalmanager.agenda.enums.AgendaType;
import com.legalmanager.agenda.enums.Status;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
public class Agenda {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Getter
    private int id;

    @Getter
    @Setter
    private LocalDate agendaDate;

    @Getter
    @Setter
    private LocalDate startDate;

    @Getter
    @Setter
    private AgendaType type;

    @Getter
    @Setter
    private String description;

    @Getter
    @Setter
    private Status status;
}