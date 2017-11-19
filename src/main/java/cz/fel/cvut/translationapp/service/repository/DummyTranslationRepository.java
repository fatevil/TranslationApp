package cz.fel.cvut.translationapp.service.repository;

import org.springframework.data.repository.CrudRepository;

import cz.fel.cvut.translationapp.api.DummyTranslation;

public interface DummyTranslationRepository extends CrudRepository<DummyTranslation, Long> {

}
