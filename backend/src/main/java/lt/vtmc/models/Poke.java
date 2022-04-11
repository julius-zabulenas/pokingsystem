package lt.vtmc.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "pokes")
public class Poke {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "user_to_id")
	private User userTo;

	@ManyToOne
	@JoinColumn(name = "user_from_id")
	private User userFrom;

	@Column(name = "poke_amount")
	private Integer pokeAmount;

	public Poke(User userTo, User userFrom, Integer pokeAmount) {
		this.userTo = userTo;
		this.userFrom = userFrom;
		this.pokeAmount = pokeAmount;
	}

	public Poke() {

	}

	public User getUserTo() {
		return userTo;
	}

	public void setUserTo(User userTo) {
		this.userTo = userTo;
	}

	public User getUserFrom() {
		return userFrom;
	}

	public void setUserFrom(User userFrom) {
		this.userFrom = userFrom;
	}

	public Integer getPokeAmount() {
		return pokeAmount;
	}

	public void setPokeAmount(Integer pokeAmount) {
		this.pokeAmount = pokeAmount;
	}

}
