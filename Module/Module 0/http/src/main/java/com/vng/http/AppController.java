package com.vng.http;

import java.security.NoSuchAlgorithmException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class AppController {

    @Autowired
    private UserRepository userRepo;

	@RequestMapping(path = "/login/{username}/{password}", method = RequestMethod.GET)
	public String login(@PathVariable String username, @PathVariable String password) {
		User user = userRepo.findByUsername(username);
		if (user == null) {
			return "fail";
		}
		if (user.authenticate(password)) {
			return "success";
		}
		return "fail";
	}

	@RequestMapping(path = "/register/{username}/{password}", method = RequestMethod.GET)
	public String register(@PathVariable String username, @PathVariable String password) {
		User user = userRepo.findByUsername(username);
		if (user == null) {
			try {
				password = Utils.toHexString(Utils.getSHA(password));
			} catch (NoSuchAlgorithmException e) {
				System.out.println("Exception thrown for incorrect algorithm: " + e);
			}
			userRepo.save(new User(username, password));
			return "success to register";
		}
		return "fail to register";
	}
}
