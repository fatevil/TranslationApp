package cz.fel.cvut.translationapp.controller;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cz.fel.cvut.translationapp.api.TranslationProvider;
import cz.fel.cvut.translationapp.api.dummy.DummyTranslation;
import cz.fel.cvut.translationapp.api.yandex.YandexTranslation;
import cz.fel.cvut.translationapp.model.Translation;
import cz.fel.cvut.translationapp.security.Authentication;
import cz.fel.cvut.translationapp.service.repository.TranslationRepository;

@Controller
public class TranslationController {

	@Autowired
	private BeanFactory beanFactory;
	@Autowired
	private TranslationProvider<DummyTranslation> dummyTranslationProvider;
	@Autowired
	private TranslationRepository translationRepository;
	@Autowired
	private TranslationProvider<YandexTranslation> yandexTranslationProvider;

	@PostMapping("/translate")
	public @ResponseBody Translation create(@ModelAttribute TranslationCreateRequest request) throws Exception {
		Translation t;
		switch (request.getProvider()) {
		case "yandex":
			t = yandexTranslationProvider.translate(request.getOriginalText(), request.getTextFrom(), request.getTextTo());
			break;
		case "dummy":
			t = dummyTranslationProvider.translate(request.getOriginalText(), request.getTextFrom(), request.getTextTo());
			break;
		default:
			throw new UnsupportedTranslationProviderException("Selected translation provider is not supported.");
		}

		Authentication auth = beanFactory.getBean(Authentication.class);
		t.setUser(auth.getCurrentUser());
		translationRepository.save(t);
		return t;
	}

}
