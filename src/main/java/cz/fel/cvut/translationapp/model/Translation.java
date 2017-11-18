package cz.fel.cvut.translationapp.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import cz.fel.cvut.translationapp.api.TranslationProvider;
import lombok.Data;

@Data
@Entity
public abstract class Translation {

	@Id
	@GeneratedValue
	private Long id;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	private String textFrom;

	private String textTo;
	
	private String text;

	public Translation() {
	}

	public Translation(User user, String textFrom, String textTo, String text) {
		this.user = user;
		this.textFrom = textFrom;
		this.textTo = textTo;
		this.text = text;
	}

}
