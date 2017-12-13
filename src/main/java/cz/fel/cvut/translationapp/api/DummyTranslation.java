package cz.fel.cvut.translationapp.api;

import javax.persistence.Entity;

import cz.fel.cvut.translationapp.model.Translation;
import cz.fel.cvut.translationapp.model.User;

@Entity
public class DummyTranslation extends Translation {

	public DummyTranslation() {
	}

	public DummyTranslation(User user, String textFrom, String textTo, String textOriginal, String textTranslated) {
		super(user, textFrom, textTo, textOriginal, textTranslated);
		// TODO Auto-generated constructor stub
	}

}
