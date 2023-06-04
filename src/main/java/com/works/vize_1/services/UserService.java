package com.works.vize_1.services;

import com.works.vize_1.entities.User;
import com.works.vize_1.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.rmi.ServerError;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    final UserRepository userRepository;

    public User userLogin(User user){
        Optional<User> u = userRepository.findByEmailEqualsAndPasswordEquals(user.getEmail(),user.getPassword());
        try{
            if(u.isPresent()){
                return u.get();
            }

        }catch (Exception ex){
            System.err.println(ex.getMessage());
        }
        return null;
    }
}
