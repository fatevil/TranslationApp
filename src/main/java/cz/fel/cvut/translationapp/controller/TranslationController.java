package cz.fel.cvut.translationapp.controller;

import org.springframework.beans.factory.annotation.Autowired;

import cz.fel.cvut.translationapp.api.DummyTranslation;
import cz.fel.cvut.translationapp.api.MicrosoftTranslation;
import cz.fel.cvut.translationapp.api.TranslationProvider;

// TODO: @Controller
public class TranslationController {

	private TranslationProvider<DummyTranslation> dummyTranslationProvider;

	private TranslationProvider<MicrosoftTranslation> msTranslationProvider;

	@Autowired
	public TranslationController(TranslationProvider<MicrosoftTranslation> msTranslationProvider,
			TranslationProvider<DummyTranslation> dummyTranslationProvider) {
		this.dummyTranslationProvider = dummyTranslationProvider;
		this.msTranslationProvider = msTranslationProvider;
	}

}
