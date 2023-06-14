package ws.api.wsapi.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ws.api.wsapi.dto.model.UserDto;
import ws.api.wsapi.model.User;
import ws.api.wsapi.service.UserService;

import java.util.List;

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
    @GetMapping("/all-uses")
    public ResponseEntity<List<User>> findAll(){
        return ResponseEntity.status(HttpStatus.OK).body(userService.getAll());
    }
}
