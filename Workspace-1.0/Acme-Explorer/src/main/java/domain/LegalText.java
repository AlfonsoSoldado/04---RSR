package domain;

import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Access(AccessType.PROPERTY)
public class LegalText extends DomainEntity {

	// Constructors

	public LegalText() {
		super();
	}

	// Attributes

	private String title;
	private String body;
	private Integer numberLaw;
	private Date moment;

	@NotBlank
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@NotBlank
	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public Integer getNumberLaw() {
		return numberLaw;
	}

	public void setNumberLaw(Integer numberLaw) {
		this.numberLaw = numberLaw;
	}

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@Past
	public Date getMoment() {
		return moment;
	}

	public void setMoment(Date moment) {
		this.moment = moment;
	}

	// Relationships

	private Trip trip;

	@Valid
	@OneToOne(optional = true)
	public Trip getTrip() {
		return trip;
	}

	public void setTrip(Trip trip) {
		this.trip = trip;
	}

}
