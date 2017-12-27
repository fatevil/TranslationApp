package cz.fel.cvut.translationapp.api.yandex;

import java.io.Serializable;

import lombok.Data;

@Data
public class BaseYandexResponse implements Serializable {

	private static final long serialVersionUID = 1L;

	private int code;

	private String message;

}