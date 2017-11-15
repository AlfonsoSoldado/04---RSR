package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.Valid;

@Entity
@Access(AccessType.PROPERTY)
public class Value extends DomainEntity{

	//Constructors
	
	public Value(){
		super();
	}
	
	//Relationships
	
	private Trip trip;
	private Collection<Tag> tag;
	
	@Valid
	@ManyToOne(optional = true)
	public Trip getTrip() {
		return trip;
	}
	public void setTrip(Trip trip) {
		this.trip = trip;
	}
	
	@Valid
	@OneToMany
	public Collection<Tag> getTag() {
		return tag;
	}
	public void setTag(Collection<Tag> tag) {
		this.tag = tag;
	}
	
	
}
