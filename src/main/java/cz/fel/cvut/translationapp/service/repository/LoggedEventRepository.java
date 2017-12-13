package cz.fel.cvut.translationapp.service.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import cz.fel.cvut.translationapp.model.LoggedEvent;

public interface LoggedEventRepository extends PagingAndSortingRepository<LoggedEvent, Long> {

}
