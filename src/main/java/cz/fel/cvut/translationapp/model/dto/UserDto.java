package cz.fel.cvut.translationapp.model.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

import cz.fel.cvut.translationapp.model.User;
import lombok.Data;

@Data
public class UserDto {
	private Long id;

	private String email;

	private boolean admin = false;

	private Date creationDate;

	@JsonIgnore
	private String password;

	public UserDto() {

	}

	public UserDto(User user) {
		this.id = user.getId();
		this.email = user.getEmail();
		this.admin = user.isAdmin();
		this.creationDate = user.getCreationDate();
		this.password = user.getPassword();
	}

	public UserDto(Long id, String email, boolean admin, Date creationDate, String password) {
		super();
		this.id = id;
		this.email = email;
		this.admin = admin;
		this.creationDate = creationDate;
		this.password = password;
	}

	public UserDto(Long id, String email, String password, boolean admin) {
		super();
		this.id = id;
		this.email = email;
		this.password = password;
		this.admin = admin;
	}

}
