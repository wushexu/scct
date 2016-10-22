package foross.scctbi.data.repository;

import org.springframework.data.repository.CrudRepository;

import foross.scctbi.data.model.User;

public interface UserRepository extends CrudRepository<User, Long> {
	
	User findByName(String name);
}
