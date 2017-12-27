package cz.fel.cvut.translationapp.api.yandex;

import groovy.transform.EqualsAndHashCode;
import lombok.Data;

@Data
@EqualsAndHashCode(callSuper=false)
public class TranslateTextResponse extends DetectLanguageResponse {

	private static final long serialVersionUID = 1L;

	private String[] text;

}