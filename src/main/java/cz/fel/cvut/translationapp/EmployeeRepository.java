package cz.fel.cvut.translationapp;

import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @author Marek Kozlovsky
 */
public interface EmployeeRepository extends PagingAndSortingRepository<Employee, Long> {

}
