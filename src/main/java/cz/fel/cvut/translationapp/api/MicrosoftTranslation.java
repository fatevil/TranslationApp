package cz.fel.cvut.translationapp.api;

import javax.persistence.Entity;

import cz.fel.cvut.translationapp.model.Translation;
import cz.fel.cvut.translationapp.model.User;

@Entity
public class MicrosoftTranslation extends Translation{

	public MicrosoftTranslation() {
		super();
	}

	public MicrosoftTranslation(User user, String textFrom, String textTo, String text) {
		super(user, textFrom, textTo, text);
	}
	
	
}
