package cz.fel.cvut.translationapp.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author Marek Kozlovsky
 */
@Entity
@Data
@ToString(exclude = "admin")
@EqualsAndHashCode(exclude="admin")
public class LoggedEvent {

	@Id
	@GeneratedValue
	private Long id;

	private String description;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private Admin admin;

	@CreationTimestamp
	private Date creationDate;

	public LoggedEvent() {

	}

}
