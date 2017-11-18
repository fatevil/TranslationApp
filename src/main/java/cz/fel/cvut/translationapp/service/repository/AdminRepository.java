package cz.fel.cvut.translationapp.service.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import cz.fel.cvut.translationapp.model.User;

/**
 * @author Marek Kozlovsky
 */
public interface AdminRepository extends PagingAndSortingRepository<User, Long> {

}
