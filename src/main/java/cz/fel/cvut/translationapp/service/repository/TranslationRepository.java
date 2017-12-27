package cz.fel.cvut.translationapp.service.repository;

import org.springframework.data.repository.CrudRepository;

import cz.fel.cvut.translationapp.model.Translation;

public interface TranslationRepository extends CrudRepository<Translation, Long> {

}
