package com.vng.http;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class AppController {

	@Autowired
	private AppService appService;

	@RequestMapping(path = "/login", method = RequestMethod.GET)
	public String login(@RequestBody LoginForm loginForm) {
		String username = loginForm.getUsername();
		String password = loginForm.getPassword();

		return appService.login(username, password);
	}

	@RequestMapping(path = "/register", method = RequestMethod.GET)
	public String register(@RequestBody RegisterForm registerForm) {
		String 	username = registerForm.getUsername(),
				password = registerForm.getPassword(),
				email = registerForm.getEmail(),
				fullname = registerForm.getFullname();

		return appService.register(username, password, email, fullname);
	}

	@RequestMapping(path = "/filter", method = RequestMethod.GET)
	public String searchByEmail(@RequestParam String email) {
		return appService.searchUserByEmailManual(email).toString();
	}
}
