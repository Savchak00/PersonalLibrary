package com.danylosavchak.PersonalLibrary.api;

import com.danylosavchak.PersonalLibrary.response.ResponseHandler;
import com.danylosavchak.PersonalLibrary.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
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
    public ResponseEntity<Object> logInOrCreate(@RequestBody Map<String, String> json) {
        String firstName = json.get("firstName").toLowerCase();
        String lastName = json.get("lastName").toLowerCase();
        String email = json.get("email").toLowerCase();
        Optional<Integer> userId = this.service.logIn(firstName, lastName, email);
        Map<String, Integer> responseMap = new HashMap<>();
        if (userId.isPresent()) {
            responseMap.put("userId", userId.get());
            return ResponseHandler.responseBuilder("User is logged in.", HttpStatus.OK, responseMap);
        } else {
            userId = Optional.ofNullable(this.service.register(firstName, lastName, email));
            if (userId.isPresent()) {
                responseMap.put("userId", userId.get());
                return ResponseHandler.responseBuilder(
                        "New user. Registered.", HttpStatus.CREATED, responseMap);
            }
            else {
                return ResponseHandler.responseBuilder("Can't log in or create",
                        HttpStatus.INTERNAL_SERVER_ERROR, null);
            }
        }
    }
}
