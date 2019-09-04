package co.radiantmic.lpapp.services;

import co.radiantmic.lpapp.domain.User;
import co.radiantmic.lpapp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User createUser(User user){
        return userRepository.save(user);
    }

    public void deleteUser(User user){
        userRepository.delete(user);
    }

    public Iterable<User> findAllUsers(){
        return userRepository.findAll();
    }

    public Optional<User> findUserById(Long userId){
        return userRepository.findById(userId);
    }

    public User findByUserNameAndPassword(String userName,String password){
        return userRepository.findByUserNameAndPassword(userName,password);
    }

}
