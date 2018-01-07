package cz.fel.cvut.translationapp.service.repository;

import java.util.Set;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.prepost.PreAuthorize;

import cz.fel.cvut.translationapp.model.Translation;

public interface TranslationRepository extends CrudRepository<Translation, Long> {

	@PreAuthorize("@translationRepository.findOne(#id).getUser().getEmail() == authentication?.getName()")
	public void delete(@Param("id")Long id);

	public Set<Translation> findAllByOrderByCreationDateAsc();

}
