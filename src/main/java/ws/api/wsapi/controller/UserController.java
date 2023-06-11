package ws.api.wsapi.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ws.api.wsapi.dto.model.UserDto;
import ws.api.wsapi.model.User;
import ws.api.wsapi.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/create")
    public ResponseEntity<User> create(@Valid @RequestBody UserDto dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(dto));
    }
}
