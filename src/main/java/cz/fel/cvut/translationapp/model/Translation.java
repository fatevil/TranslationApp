package cz.fel.cvut.translationapp.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;

@Data
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Translation {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
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
