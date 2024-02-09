//package ru.rzhanito.dc.service;
//
//import lombok.AllArgsConstructor;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//import ru.rzhanito.dc.entity.UserEntity;
//import ru.rzhanito.dc.exception.EntityAlreadyExistsException;
//import ru.rzhanito.dc.repo.UserRepo;
//import ru.rzhanito.dc.security.UserEntityDetails;
//
//import java.util.Optional;
//
//@Service
//@AllArgsConstructor
//public class UserService implements UserDetailsService {
//    private final UserRepo userRepo;
//    private final PasswordEncoder passwordEncoder;
//
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        Optional<UserEntity> user = userRepo.findUserEntityByUsername(username);
//        return user.map(UserEntityDetails::new)
//                .orElseThrow(() -> new UsernameNotFoundException(username + " not found"));
//    }
//
//    public void addUser(UserEntity user) throws EntityAlreadyExistsException {
//        Optional<UserEntity> existingUser = userRepo.findUserEntityByUsername(user.getUsername());
//        if(existingUser.isPresent()){
//            throw new EntityAlreadyExistsException("Юзер " + user.getUsername() + " уже существует.");
//        } else{
//            user.setPassword(passwordEncoder.encode(user.getPassword()));
//            userRepo.save(user);
//        }
//    }
//
//}
