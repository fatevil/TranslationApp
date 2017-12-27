package cz.fel.cvut.translationapp.api.yandex;

import javax.persistence.Entity;

import cz.fel.cvut.translationapp.model.Translation;
import cz.fel.cvut.translationapp.model.User;
import groovy.transform.EqualsAndHashCode;
import groovy.transform.ToString;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class YandexTranslation extends Translation {

	private final String provider = "Yandex";
	
	public YandexTranslation(User user, String textFrom, String textTo, String textOriginal, String textTranslated) {
		super(user, textFrom, textTo, textOriginal, textTranslated);
	}

	public YandexTranslation(String textFrom, String textTo, String textOriginal, String textTranslated) {
		super(textFrom, textTo, textOriginal, textTranslated);
	}
	
	public YandexTranslation() {
	}
	
}
