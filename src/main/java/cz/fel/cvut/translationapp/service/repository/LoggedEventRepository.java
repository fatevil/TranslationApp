package cz.fel.cvut.translationapp.service.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.security.access.prepost.PreAuthorize;

import cz.fel.cvut.translationapp.model.LoggedEvent;
import cz.fel.cvut.translationapp.model.dto.LoggedEventDto;


@PreAuthorize("hasRole('ROLE_ADMIN')")
public interface LoggedEventRepository extends PagingAndSortingRepository<LoggedEvent, Long> {

	List<LoggedEventDto> getAllWithAdmins();
}
