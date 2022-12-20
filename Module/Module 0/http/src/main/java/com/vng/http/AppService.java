package com.vng.http;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

import org.springframework.cache.annotation.Cacheable;

@Service
public class AppService { 
    @Autowired
    CacheManager cacheManager;

    @Autowired
    UserRepository userRepo; 


    private final static Logger LOG = LoggerFactory.getLogger(AppService.class);

    public String login(String username, String password) { 
		User user = userRepo.findByUsername(username);
		if (user == null || !user.authenticate(password)) {
			return "fail";
		}
        return "success";
    }

    public String register(String username, String password, String email, String fullname) { 
		User user = userRepo.findByUsername(username);
		if (user == null) {
			try {
				password = Utils.toHexString(Utils.getSHA(password));
			} catch (NoSuchAlgorithmException e) {
				System.out.println("Exception thrown for incorrect algorithm: " + e);
			}
			userRepo.save(new User(username, password, email, fullname));
			return "success to register";
		}
		return "fail to register";
    }

	@Cacheable(cacheNames="searchByEmail") 
    public List<String> searchUserByEmail(String email) { 
        LOG.info("Email: " + email + ". Cache MISS");

        List<User> userList = userRepo.findByUserEmail(email);
        List<String> usernameList = new ArrayList<>();
        for (User user: userList)
            usernameList.add(user.getFullname());
        return usernameList;
    }

    public List<String> searchUserByEmailManual(String email) { 
        if (cacheManager.getCache("searchByEmail").get(email) != null) { 
            LOG.info("Email: " + email + ". Cache HIT");
            return (List<String>) cacheManager.getCache("searchByEmail").get(email).get(); 
        }
        LOG.info("Email: " + email + ". Cache MISS");
        List<User> userList = userRepo.findByUserEmail(email);
        List<String> usernameList = new ArrayList<>();
        for (User user: userList)
            usernameList.add(user.getFullname());
        cacheManager.getCache("searchByEmail").put(email, usernameList);
        return usernameList;
    }
}