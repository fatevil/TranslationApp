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

	@OneToMany(mappedBy = "admin", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<LoggedEvent> loggedEvents;

	public Admin() {
		super();
	}

	public Admin(String email, String password) {
		super(email, password);
	}

}
