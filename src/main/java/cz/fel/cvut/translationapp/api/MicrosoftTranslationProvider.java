package cz.fel.cvut.translationapp.api;

import org.springframework.stereotype.Service;

import com.memetix.mst.language.Language;
import com.memetix.mst.translate.Translate;

@Service(value = "microsoft")
public class MicrosoftTranslationProvider implements TranslationProvider<MicrosoftTranslation> {

	@Override
	public MicrosoftTranslation translate(String text, String from, String to) throws Exception {

		Translate.setClientId(CLIENT_ID);
		Translate.setClientSecret(CLIENT_SECRET);

		String translatedText = null;
		translatedText = Translate.execute(text, Language.CZECH,Language.ENGLISH);

//		return new MicrosoftTranslation(null, "cz", "en", translatedText);
		return null;
	}

	private final String CLIENT_ID = "12345789741852963";
	private final String CLIENT_SECRET = "heslonamicrosoftapi123";

	public static void main(String[] args) throws Exception {
		new MicrosoftTranslationProvider().translate("Ahoj", null, null);
	}
}
