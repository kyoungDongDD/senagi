package com.ssafy.b105.config.auth;

import com.ssafy.b105.entity.User;
import com.ssafy.b105.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PrincipalDetailsService implements UserDetailsService{

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> user = userRepository.findByPrincipal(username);
		if(user == null) {
			return null;
		}else {
			return new PrincipalDetails(user.get());
		}
		
	}

}
