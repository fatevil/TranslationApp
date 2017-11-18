package cz.fel.cvut.translationapp.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;

/**
 * @author Marek Kozlovsky
 */
@Entity
@Data
public class LoggedEvent {

	@Id
	@GeneratedValue
	private Long id;

	private String description;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private Admin admin;

	public LoggedEvent() {

	}

}
