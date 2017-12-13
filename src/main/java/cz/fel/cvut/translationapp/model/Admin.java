package cz.fel.cvut.translationapp.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import lombok.Data;

/**
 * @author Marek Kozlovsky
 */
@Entity
@Data
public class Admin extends User {

	@OneToMany(mappedBy = "admin", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Set<LoggedEvent> loggedEvents;

	public Admin() {
		super();
	}

	public Admin(String email, String password) {
		super(email, password);
	}

	@Override
	public String toString() {
		return "Admin [loggedEvents=" + loggedEvents + ", getId()=" + getId() + ", getEmail()=" + getEmail()
				+ ", getTranslations()=" + getTranslations() + ", getFriends()=" + getFriends() + ", getCreationDate()="
				+ getCreationDate() + ", toString()=" + super.toString() + ", getClass()=" + getClass() + "]";
	}
}
