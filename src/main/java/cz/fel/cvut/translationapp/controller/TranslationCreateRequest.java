package cz.fel.cvut.translationapp.controller;

import lombok.Data;

@Data
public class TranslationCreateRequest {

	private String textFrom;
	private String textTo;
	private String originalText;
	private String provider;

}
