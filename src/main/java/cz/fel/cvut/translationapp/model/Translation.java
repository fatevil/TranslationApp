package cz.fel.cvut.translationapp.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(exclude = "user")
@EqualsAndHashCode(exclude="user")
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

	private String textTranslated;
	

	private String textOriginal;

	@CreationTimestamp
	private Date creationDate;

	public Translation() {
	}

	public Translation(User user, String textFrom, String textTo, String textOriginal, String textTranslated) {
		this.user = user;
		this.textFrom = textFrom;
		this.textTo = textTo;
		this.textOriginal = textOriginal;
		this.textTranslated = textTranslated;
	}

}
