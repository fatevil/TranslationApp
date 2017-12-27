package cz.fel.cvut.translationapp.api.yandex;

import groovy.transform.EqualsAndHashCode;
import lombok.Data;

@Data
@EqualsAndHashCode(callSuper=false)
public class DetectLanguageResponse extends BaseYandexResponse {

	private static final long serialVersionUID = 1L;

	private String lang;
}