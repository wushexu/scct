package foross.scctbi.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContextHolder;

import foross.scctbi.data.model.User;
import foross.scctbi.data.repository.UserRepository;

public class AuditorAwareImpl implements AuditorAware<User> {

	@Autowired
	UserRepository userRepository;

	@Override
	public User getCurrentAuditor() {
		// User user =new User(0L);
		// String name="SYSTEM";
		String name = SecurityContextHolder.getContext().getAuthentication()
				.getName();
		User user = userRepository.findByName(name);
//		if (user == null) {
//			user = new User();
//			user.setName(name);
//			userRepository.save(user);
//		}
		return user;
	}

}