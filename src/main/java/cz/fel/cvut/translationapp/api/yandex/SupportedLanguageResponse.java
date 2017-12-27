package cz.fel.cvut.translationapp.api.yandex;

import java.util.HashMap;

import groovy.transform.EqualsAndHashCode;
import lombok.Data;

@Data
@EqualsAndHashCode(callSuper=false)
public class SupportedLanguageResponse extends BaseYandexResponse {

	private static final long serialVersionUID = 1L;

	private HashMap<String, String> langs;

	private String[] dirs;

}