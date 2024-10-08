package fr.diginamic.hello.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for the Hello stuff
 */
@RestController
//@RequestMapping("/hello")
public class GreetingsController
{
	/**
	 * Says Hello.
	 * 
	 * @return "Hello"
	 */
	@GetMapping("/hello")
	public String sayHello()
	{
		return "Hello";
	}
	
	/**
	 * Says goodbye
	 * 
	 * @return "goodbye"
	 */
	@GetMapping("/goodbye")
	public String sayGoodbye()
	{
		return "goodbye";
	}
}
