package cz.fel.cvut.translationapp.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

import lombok.Data;

/**
 * @author Marek Kozlovsky
 */
@Entity
@Data
@NamedQueries({ @NamedQuery(name = "Admin.findByEmail", query = "SELECT u FROM Admin u WHERE u.email = ?") })
public class Admin extends User {

	@OneToMany(mappedBy = "admin", cascade = { CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH,
			CascadeType.PERSIST }, fetch = FetchType.LAZY)
	private Set<LoggedEvent> loggedEvents;

	private final boolean admin = true;

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
				+ getCreationDate() + ", admin=true]";
	}

	public boolean isAdmin() {
		return admin;
	}
}
