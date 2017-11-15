package domain;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
public class Finder extends DomainEntity {

	// Constructors

	public Finder() {
		super();
	}

	// Attributes

	private String singleKey;
	private Double minPrice;
	private Double maxPrice;
	private Date start;
	private Date end;
	private Date cache;

	public String getSingleKey() {
		return singleKey;
	}

	public void setSingleKey(String singleKey) {
		this.singleKey = singleKey;
	}

	@Range(min = 0)
	public Double getMinPrice() {
		return minPrice;
	}

	public void setMinPrice(Double minPrice) {
		this.minPrice = minPrice;
	}

	@Range(min = 0)
	public Double getMaxPrice() {
		return maxPrice;
	}

	public void setMaxPrice(Double maxPrice) {
		this.maxPrice = maxPrice;
	}

	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	public Date getStart() {
		return start;
	}

	public void setStart(Date start) {
		this.start = start;
	}

	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	public Date getEnd() {
		return end;
	}

	public void setEnd(Date end) {
		this.end = end;
	}
	
	@Past
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
	public Date getCache() {
		return cache;
	}

	public void setCache(Date cache) {
		this.cache = cache;
	}

	// Relationships

	private Collection<Trip> trip;

	@Valid
	@OneToMany
	public Collection<Trip> getTrip() {
		return trip;
	}

	public void setTrip(Collection<Trip> trip) {
		this.trip = trip;
	}
}
