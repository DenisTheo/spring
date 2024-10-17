package fr.diginamic.hello.user;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class UserAccount
{
	@Id
	@GeneratedValue
	private Long id;
	
	private String username;
	
	private String password;
	
	@ElementCollection(fetch=FetchType.EAGER)
	private List<GrantedAuthority> authorities = new ArrayList<>();
	
	public UserAccount(String username, String password, String... authorities)
	{
		setUserName(username);
		setPassword(password);
		
		this.authorities = Arrays.stream(authorities)
				.map(SimpleGrantedAuthority::new)
				.map(GrantedAuthority.class::cast)
				.toList();
	}
	
	public String getUserName()
	{
		return username;
	}
	
	protected void setUserName(String username)
	{
		this.username = username;
	}
	
	protected void setPassword(String password)
	{
		this.password = password;
	}
}
