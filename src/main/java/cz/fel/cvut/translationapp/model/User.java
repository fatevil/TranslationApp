package cz.fel.cvut.translationapp.model;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.validator.constraints.Email;

import lombok.Data;
import lombok.ToString;

/**
 * @author Marek Kozlovsky
 */
@Data
@Entity
@Inheritance
@ToString(exclude = "password")
@Table(name = "user")
public class User {

	// public static final PasswordEncoder PASSWORD_ENCODER = new
	// BCryptPasswordEncoder();

	@Id
	@GeneratedValue
	private Long id;

	@Email
	@Column(unique = true)
	private String email;

	private String password;

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<Translation> translations;

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "friends", 	
	joinColumns = @JoinColumn(name = "user_1_id", referencedColumnName = "id"), 
	inverseJoinColumns = @JoinColumn(name = "user_2_id", referencedColumnName = "id"))
	private List<User> friends;

	public User() {
	}

	public User(String email, String password) {

		this.email = email;
		this.password = password;
	}

	// public void setPassword(String password) {
	// this.password = PASSWORD_ENCODER.encode(password);
	// }

}