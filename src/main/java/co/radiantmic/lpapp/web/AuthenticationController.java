package co.radiantmic.lpapp.web;

import co.radiantmic.lpapp.domain.AuthToken;
import co.radiantmic.lpapp.domain.User;
import co.radiantmic.lpapp.security.SecurityConstant;
import co.radiantmic.lpapp.security2.TokenProvider;
import co.radiantmic.lpapp.services.UserDetailsServiceImpl;
import co.radiantmic.lpapp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/token")
public class AuthenticationController {

    @Autowired
    private TokenProvider jwtTokenUtil;

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @PostMapping("/generate")
    public ResponseEntity<?> register(@RequestBody User loginUser) throws AuthenticationException {

        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginUser.getUsername(), loginUser.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        final String token = jwtTokenUtil.generateToken(authentication);
        User user = userService.findByUsername(loginUser.getUsername());
        AuthToken authToken = new AuthToken(token);
        authToken.setUser(user);
        return ResponseEntity.ok(authToken);
    }

    @PostMapping("/token/refresh")
    public ResponseEntity<?> refresh(@RequestBody User loginUser) throws AuthenticationException {
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

}
