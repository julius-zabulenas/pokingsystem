package lt.vtmc.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "pokes")
public class Poke {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "user_id")
	private Long userId;

	@Column(name = "user_from_id")
	private Long userFromId;

	@Column(name = "poke_amount")
	private Integer pokeAmount;

//	@Embedded
//	private User user;

	public Poke(Long userId, Long userFromId, Integer pokeAmount) {
		this.userId = userId;
		this.userFromId = userFromId;
		this.pokeAmount = pokeAmount;
	}

//	public Poke(Long userId, Long userFromId, Integer pokeAmount, User user) {
//		this.userId = userId;
//		this.userFromId = userFromId;
//		this.pokeAmount = pokeAmount;
//		this.user = user;
//	}

//	@ManyToOne
//	private User user;

	public Poke() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getUserFromId() {
		return userFromId;
	}

	public void setUserFromId(Long userFromId) {
		this.userFromId = userFromId;
	}

	public Integer getPokeAmount() {
		return pokeAmount;
	}

	public void setPokeAmount(Integer pokeAmount) {
		this.pokeAmount = pokeAmount;
	}

//	public User getUser() {
//		return user;
//	}
//
//	public void setUser(User user) {
//		this.user = user;
//	}

}
