package cz.fel.cvut.translationapp.service.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.prepost.PreAuthorize;

import cz.fel.cvut.translationapp.model.User;

/**
 * @author Marek Kozlovsky
 */
public interface UserRepository extends PagingAndSortingRepository<User, Long> {

	User findByEmail(String email);

	@PreAuthorize("hasRole('ROLE_ADMIN') AND @myCustomJpaLogger.doLog(#id)")
	public void delete(@Param("id") Long id);
	
}
