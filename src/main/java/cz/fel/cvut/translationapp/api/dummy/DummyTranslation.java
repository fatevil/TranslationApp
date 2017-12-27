package cz.fel.cvut.translationapp.api.dummy;

import javax.persistence.Entity;

import cz.fel.cvut.translationapp.model.Translation;
import cz.fel.cvut.translationapp.model.User;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class DummyTranslation extends Translation {

	private final String provider = "Dummy";
	
	public DummyTranslation() {
	}

	public DummyTranslation(User user, String textFrom, String textTo, String textOriginal, String textTranslated) {
		super(user, textFrom, textTo, textOriginal, textTranslated);
	}

	public DummyTranslation(String textFrom, String textTo, String textOriginal, String textTranslated) {
		super(textFrom, textTo, textOriginal, textTranslated);
	}
}
