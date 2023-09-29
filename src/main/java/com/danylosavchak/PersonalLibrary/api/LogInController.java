package com.danylosavchak.PersonalLibrary.api;

import com.danylosavchak.PersonalLibrary.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Optional;

@RestController("logInController")
@RequestMapping("api/logIn")
public class LogInController {

    private final UserService service;

    @Autowired
    public LogInController(UserService service) {
        this.service = service;
    }

    @PostMapping("/logInOrCreate")
    public Integer logInOrCreate(@RequestBody Map<String, String> json) {
        String firstName = json.get("firstName").toLowerCase();
        String lastName = json.get("lastName").toLowerCase();
        String email = json.get("email").toLowerCase();
        Optional<Integer> userId = this.service.logIn(firstName, lastName, email);
        return userId.orElseGet(() -> this.service.register(firstName, lastName, email));
    }


}
