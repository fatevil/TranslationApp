package cz.fel.cvut.translationapp.api;

import cz.fel.cvut.translationapp.model.Translation;
import cz.fel.cvut.translationapp.model.User;

public class DummyTranslation extends Translation {

	public DummyTranslation() {
	}

	public DummyTranslation(User user, String textFrom, String textTo, String text) {
		super(user, textFrom, textTo, text);
	}
	
}
