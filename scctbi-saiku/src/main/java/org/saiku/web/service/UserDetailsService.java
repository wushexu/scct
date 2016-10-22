package org.saiku.web.service;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserDetailsService implements
		org.springframework.security.core.userdetails.UserDetailsService {

	public static final String ROLE_ADMINISTRATOR = "ROLE_ADMIN";

	@Value("${saiku.administrator.users:}")
	private String administratorUsers;

	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {

		Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

		authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
		if (administratorUsers != null && !administratorUsers.equals("")) {
			String[] usernames = administratorUsers.split(",");
			for (String name : usernames) {
				if (username.equals(name)) {
					authorities.add(new SimpleGrantedAuthority(
							ROLE_ADMINISTRATOR));
					break;
				}
			}
		}

		String password = "";
		User user = new User(username, password, authorities);

		return user;
	}
}
