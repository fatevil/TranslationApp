package cz.fel.cvut.translationapp.api;

import cz.fel.cvut.translationapp.api.yandex.UnsupportedLanguageException;

public interface LanguageShortcutList {

	boolean contains(String language) throws UnsupportedLanguageException;

	String get(String language) throws UnsupportedLanguageException;

}
