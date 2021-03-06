package cz.fel.cvut.translationapp.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Table;

import org.dom4j.util.UserDataAttribute;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;
import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.Email;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.fasterxml.jackson.annotation.JsonIgnore;

import cz.fel.cvut.translationapp.model.dto.UserDto;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author Marek Kozlovsky
 */
@Data
@Entity
@Inheritance
@ToString(exclude = { "password", "friends" })
@Table(name = "user")
@EqualsAndHashCode(exclude = "friends")
@NamedQueries({ @NamedQuery(name = "User.DeleteAllUsers", query = "DELETE FROM User"),
			 @NamedQuery(name = "User.findByEmail", query = "SELECT u FROM User u WHERE u.email = ?"),
		// @NamedQuery(name = "User.deletelAll", query = "DELETE FROM User"),
		// @NamedQuery(name = "User.deletelAll", query = "DELETE FROM User")
})
public class User {

	public static final PasswordEncoder PASSWORD_ENCODER = new BCryptPasswordEncoder();

	@Id
	@GeneratedValue
	private Long id;

	@Email
	@Column(unique = true)
	private String email;

	@JsonIgnore
	private String password;

	@OrderBy("creationDate")
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Set<Translation> translations;

	@JsonIgnore
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "friends", joinColumns = @JoinColumn(name = "user_1_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "user_2_id", referencedColumnName = "id"))
	private Set<User> friends;

	private final boolean admin = false;

	@CreationTimestamp
	private Date creationDate;

	public User() {
	}

	public User(String email, String password) {

		this.email = email;
		setPassword(password);
	}

	public void setPassword(String password) {
		this.password = PASSWORD_ENCODER.encode(password);
	}

	public boolean isAdmin() {
		return admin;
	}
}