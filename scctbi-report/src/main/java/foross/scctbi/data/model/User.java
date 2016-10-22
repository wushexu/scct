package foross.scctbi.data.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
@Table(name="users")
public class User extends AbstractPersistable<Long> {

	private static final long serialVersionUID = 743235655653L;

	@Column(unique = true)
	@NotNull
	private String name;

	public User() {

	}

	public User(Long id) {
		this.setId(id);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
