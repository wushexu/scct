package foross.scctbi.web.common;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserDetailsService implements
		org.springframework.security.core.userdetails.UserDetailsService {

	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {

		String password = "";
		GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_USER");
		Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(authority);
		User user = new User(username, password, authorities);

		return user;
	}
}
