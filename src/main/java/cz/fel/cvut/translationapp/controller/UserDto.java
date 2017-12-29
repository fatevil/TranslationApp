package cz.fel.cvut.translationapp.controller;

import java.util.Date;

import cz.fel.cvut.translationapp.model.User;
import lombok.Data;

@Data
public class UserDto {
	private Long id;

	private String email;

	private boolean admin = false;

	private Date creationDate;

	public UserDto() {

	}

	public UserDto(User user) {
		this.id = user.getId();
		this.email = user.getEmail();
		this.admin = user.isAdmin();
		this.creationDate = user.getCreationDate();
	}

}
