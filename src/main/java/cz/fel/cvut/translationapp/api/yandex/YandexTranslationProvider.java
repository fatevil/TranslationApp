package cz.fel.cvut.translationapp.api.yandex;

import org.springframework.stereotype.Service;

import cz.fel.cvut.translationapp.api.TranslationProvider;

@Service(value = "yandex")
public class YandexTranslationProvider implements TranslationProvider<YandexTranslation> {

	public YandexTranslationProvider() {

	}
	
	@Override
	public YandexTranslation translate(String text, String from, String to) throws Exception {
		if (text == "") {
			return new YandexTranslation(from, to, "", "");
		}
		YandexAPI api = new YandexAPI();
		String matchingLanguageFrom = new YandexLanguageShortcutList().get(from);
		String matchingLanguageTo = new YandexLanguageShortcutList().get(to);
		String matchingPair = matchingLanguageFrom.concat("-").concat(matchingLanguageTo);
		String[] translated = api.translateText(text, matchingPair).getText();
//		System.out.println("===========Translation========\n");
//		for (String string : translated) {
//			System.out.println(string);
//			System.out.println("\n");
//		}
//		System.out.println("===========-----------========\n");
		
		YandexTranslation tr = new YandexTranslation(from, to, text, translated[0]);
		System.out.println(tr);
		return tr;
	}
}
