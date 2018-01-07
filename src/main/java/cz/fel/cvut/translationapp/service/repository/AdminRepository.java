package cz.fel.cvut.translationapp.service.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.RepositoryDefinition;
import org.springframework.security.access.prepost.PreAuthorize;

import cz.fel.cvut.translationapp.model.Admin;
import cz.fel.cvut.translationapp.model.User;

/**
 * @author Marek Kozlovsky
 */
@PreAuthorize("hasRole('ROLE_ADMIN')")
public interface AdminRepository extends PagingAndSortingRepository<Admin, Long> {

	Admin findByEmail(String email);

}
