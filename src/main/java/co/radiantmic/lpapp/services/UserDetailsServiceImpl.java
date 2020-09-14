package co.radiantmic.lpapp.services;

import co.radiantmic.lpapp.domain.User;
import co.radiantmic.lpapp.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    private final Logger log = LoggerFactory.getLogger(UserDetailsServiceImpl.class.getName());

    public UserDetailsServiceImpl() {

    }

    @Override
    public UserDetails loadUserByUsername(String username)  {

        User currentUser = userRepository.findByUsername(username);
        if (currentUser == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Could not find user with username " + username);
        }
        String[] roleNames = currentUser.getRoles().stream().map(role -> role.getRoleName()).toArray(String[]::new);
        UserDetails userDetails = new org.springframework.security.core.userdetails.User(username, currentUser.getPassword(), true, true, true, true,
                AuthorityUtils.createAuthorityList(roleNames));
        return userDetails;
    }
}
