package cz.fel.cvut.translationapp.api;

import org.springframework.stereotype.Service;

@Service(value = "dummy")
public class DummyTranslationProvider implements TranslationProvider<DummyTranslation> {

	public DummyTranslationProvider() {

	}
	
	@Override
	public DummyTranslation translate(String text, String from, String to) throws Exception {
		return new DummyTranslation(null, "cz", "en", "Nazdárek!");
	}

}
