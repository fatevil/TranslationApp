package cz.fel.cvut.translationapp.model.dto;

import java.util.Date;

import lombok.Data;

@Data
public class LoggedEventDto {

	private Long id;
	
	private String description;
	
	private String admin;
	
	private Date creationDate;

	public LoggedEventDto(Long id, String description, String admin, Date creationDate) {
		super();
		this.id = id;
		this.description = description;
		this.admin = admin;
		this.creationDate = creationDate;
	}
	
	public LoggedEventDto() {
	}
}
