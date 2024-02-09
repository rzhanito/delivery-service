//package ru.rzhanito.dc.controller;
//
//import lombok.AllArgsConstructor;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//import ru.rzhanito.dc.entity.UserEntity;
//import ru.rzhanito.dc.exception.EntityAlreadyExistsException;
//import ru.rzhanito.dc.service.UserService;
//
//@RestController
//@AllArgsConstructor
//@RequestMapping("user")
//public class UserController {
//    private final UserService userService;
//
//    @PostMapping("new_user")
//    public ResponseEntity<String> addUser(@RequestBody UserEntity user) throws EntityAlreadyExistsException {
//        userService.addUser(user);
//        return ResponseEntity.ok("Юзер " + user.getUsername() + " успешно создан.");
//    }
//}
