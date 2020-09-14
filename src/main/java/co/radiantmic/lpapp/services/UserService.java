package co.radiantmic.lpapp.services;

import co.radiantmic.lpapp.domain.Bank;
import co.radiantmic.lpapp.domain.Branch;
import co.radiantmic.lpapp.domain.Role;
import co.radiantmic.lpapp.domain.User;
import co.radiantmic.lpapp.repositories.BankRepository;
import co.radiantmic.lpapp.repositories.BranchRepository;
import co.radiantmic.lpapp.repositories.RoleRepository;
import co.radiantmic.lpapp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BranchRepository branchRepository;

    /**
     * method used to create branch
     * @param user
     * @param roles
     * @return
     */
    public User createUser(User user, List<Role> roles) {
        List<Role> foundRoles = new ArrayList<>();
        for (Role role : roles) {
            if (role.getRoleId() != null) {
                Optional<Role> optionalRole = roleRepository.findById(role.getRoleId());
                if (optionalRole.isPresent())
                    foundRoles.add(optionalRole.get());
            }
        }
//        Optional<Branch> optionalBranch = branchRepository.findById(branchId);
//        if (optionalBranch.isPresent())
//            user.setBranch(optionalBranch.get());
        user.setRoles(foundRoles);
        return userRepository.save(user);
    }

    public User editUser(User user) {
        try{
            return userRepository.save(user);
        }
        catch(Exception ex){
            ex.printStackTrace();
            return null;
        }
    }

    public void deleteUser(User user) {

        userRepository.delete(user);
    }

    public List<User> findAllUsers() {

        return userRepository.findAll();
    }

    public User findUserById(Long userId) {

        return userRepository.findById(userId).orElse(null);
    }

    public User findByUsernameAndPassword(String userName, String password) {

        return userRepository.findByUsernameAndPassword(userName, password);
    }

    public User findByUsername(String userName) {
        return userRepository.findByUsername(userName);
    }

}
