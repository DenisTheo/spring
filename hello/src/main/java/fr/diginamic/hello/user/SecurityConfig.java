package fr.diginamic.hello.user;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;

@Configuration
//@EnableWebSecurity(debug=true)
public class SecurityConfig
{
    @Bean
    UserDetailsService userDetailsService()
	{
		UserDetailsManager userDetailsManager = new InMemoryUserDetailsManager();
		
		userDetailsManager.createUser(User.withDefaultPasswordEncoder()
				.username("user")
				.password("password")
				.roles("USER")
				.build());
		userDetailsManager.createUser(User.withDefaultPasswordEncoder()
				.username("admin")
				.password("password")
				.roles ("ADMIN")
				.build());
		
		return userDetailsManager;
	}
}
