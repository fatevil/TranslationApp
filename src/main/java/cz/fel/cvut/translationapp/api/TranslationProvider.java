package cz.fel.cvut.translationapp.api;

import cz.fel.cvut.translationapp.model.Translation;

public interface TranslationProvider<T extends Translation> {

	T translate(String text, String from, String to) throws Exception;

}
