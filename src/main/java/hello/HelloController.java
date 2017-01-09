package hello;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

	@RequestMapping("/")
	public String index() {
		return "Index Greetings from Spring Boot!";
	}

	@RequestMapping("/Hello")
	public String hello() {
		return "Hello from Spring Boot!";
	}

	@RequestMapping("/Hallo")
	public String hallo(
			@RequestParam(value = "firstName", required = false) String firstName) {
		if (firstName != null && !firstName.equals("")) {
			return "Hallo " + firstName;
		}
		return "Hallo from Spring Boot!";
	}
}