package cz.fel.cvut.translationapp.api.yandex;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.filter.LoggingFilter;

public class YandexAPI {

	public final static String BASE_ENDPOINT = "https://translate.yandex.net/api/v1.5/tr.json/";

	public static final String API_KEY = "trnsl.1.1.20171227T130459Z.f88762a14c12a722.2d997e8b876b77e04cf9f34054578a457875ef12";

	private Client client;

	private WebTarget target;

	public YandexAPI() {
		this.client = ClientBuilder.newClient();
	}

	public DetectLanguageResponse detectLanguage(String text) {
		target = client.target(BASE_ENDPOINT + "detect").queryParam("key", API_KEY).queryParam("text", text);
		Response response = target.request().get();
		DetectLanguageResponse responseDto = response.readEntity(DetectLanguageResponse.class);
		return responseDto;
	}

	public TranslateTextResponse translateText(String text, String targetLanguage) {
		target = client.target(BASE_ENDPOINT + "translate").queryParam("key", API_KEY).queryParam("text", text)
				.queryParam("lang", targetLanguage);
		Response response = target.request().get();
		TranslateTextResponse responseDto = response.readEntity(TranslateTextResponse.class);
		return responseDto;
	}	

	public SupportedLanguageResponse supportedLanguages() {
		target = client.target(BASE_ENDPOINT + "getLangs").queryParam("key", API_KEY).queryParam("ui", "en");
		Response response = target.request().get();
		System.out.println(response.toString());
		SupportedLanguageResponse responseDto = response.readEntity(SupportedLanguageResponse.class);
		return responseDto;
	}

	public static void main(String ...args) {
		System.out.println(new YandexAPI().translateText("zadek", "en"));
		
		
	}
}