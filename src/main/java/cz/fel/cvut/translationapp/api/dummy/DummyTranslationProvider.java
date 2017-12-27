package cz.fel.cvut.translationapp.api.dummy;

import org.springframework.stereotype.Service;

import cz.fel.cvut.translationapp.api.TranslationProvider;

@Service(value = "dummy")
public class DummyTranslationProvider implements TranslationProvider<DummyTranslation> {

	public DummyTranslationProvider() {

	}
	
	@Override
	public DummyTranslation translate(String text, String from, String to) throws Exception {
		return new DummyTranslation(null, from, to, text, "Dummy: Hi there!");
	}
}
