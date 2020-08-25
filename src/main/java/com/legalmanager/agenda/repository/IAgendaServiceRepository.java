package com.legalmanager.agenda.repository;

import com.legalmanager.agenda.model.Agenda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface IAgendaServiceRepository extends CrudRepository<Agenda, Integer> {
}
