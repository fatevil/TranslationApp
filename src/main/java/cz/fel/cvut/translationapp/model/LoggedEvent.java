package cz.fel.cvut.translationapp.model;

import java.io.IOException;
import java.util.Date;

import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SqlResultSetMapping;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.NamedQuery;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import cz.fel.cvut.translationapp.model.dto.LoggedEventDto;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author Marek Kozlovsky
 */
@Entity
@Data
@ToString(exclude = "admin")
@EqualsAndHashCode(exclude = "admin")
@NamedQuery(name = "LoggedEvent.getAllWithAdmins", query = "SELECT new cz.fel.cvut.translationapp.model.dto.LoggedEventDto(e.id, e.description,a.email, e.creationDate) FROM LoggedEvent e, Admin a  WHERE e.admin.id = a.id")
@SqlResultSetMapping(name = "LoggedEventDtoNativeMapping", classes = {
		@ConstructorResult(targetClass = LoggedEventDto.class, columns = {
				@ColumnResult(name = "id", type = Long.class), 
				@ColumnResult(name = "email", type = String.class),
				@ColumnResult(name = "description", type = String.class),
				@ColumnResult(name = "creationDate", type = Date.class), }) })
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

	static class AdminToJustEmailSerializer extends JsonSerializer<Admin> {

		@Override
		public void serialize(Admin admin, JsonGenerator jsonGenerator, SerializerProvider serializerProvider)
				throws IOException, JsonProcessingException {
			jsonGenerator.writeObject(admin.getEmail());
		}
	}

}
