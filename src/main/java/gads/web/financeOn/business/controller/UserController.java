package gads.web.financeOn.business.controller;

import gads.web.financeOn.business.services.UserService;
import gads.web.financeOn.infrastructure.entity.UserEntity;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    //ResponseEntity retorna um Objeto com uma lista de usuários
    @GetMapping
    public ResponseEntity<List<UserEntity>> getAllUsers() {
            var users = this.userService.findAll();
            return ResponseEntity.ok(users);
    }

    //Aqui não utilizei o List pois vamos retornar apenas um usuário
    @GetMapping("/{id}")
    public ResponseEntity<UserEntity> getById(@PathVariable String id){
        var user = this.userService.findId(id);
        return ResponseEntity.ok(user);
    }

    @PostMapping
    public ResponseEntity<UserEntity> save(@RequestBody UserEntity user) {
        user = this.userService.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @PutMapping
    public ResponseEntity<UserEntity> update(@RequestBody UserEntity user) {
        user = this.userService.update(user);
        return ResponseEntity.ok(user);
    }

    // Build controi o objeto
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(String id) {
        this.userService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
