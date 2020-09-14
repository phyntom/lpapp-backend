package co.radiantmic.lpapp.web;

import co.radiantmic.lpapp.domain.*;
import co.radiantmic.lpapp.exception.BadRequestException;
import co.radiantmic.lpapp.services.RoleService;
import co.radiantmic.lpapp.services.UserService;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.io.IOException;
import java.io.StringReader;
import java.util.*;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    List<Integer> roleIds;


    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('SUPER-ADMIN')")
    @PostMapping("")
    public ResponseEntity<?> createUser(@RequestBody String body) {

        try {
            StringReader reader = new StringReader(body);
            ObjectMapper mapperReader = new ObjectMapper();
            mapperReader.registerModule(new JavaTimeModule());
            mapperReader.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            mapperReader.configure(DeserializationFeature.FAIL_ON_INVALID_SUBTYPE, false);
            JsonNode root = mapperReader.readTree(reader);
            User user = new User(root.get("username").textValue(), new BCryptPasswordEncoder().encode(root.get("password").textValue()), root.get("email").textValue());
            List<Role> roles = new ArrayList<>(Arrays.asList(mapperReader.treeToValue(root.get("roles"), Role[].class)));
            User createdUser = userService.createUser(user,roles);
            return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new BadRequestException("Cannot create customer policy | " + ex.toString());
        }

    }

    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('SUPER-ADMIN')")
    @PutMapping("")
    public ResponseEntity<?> editUser(@Valid @RequestBody String body) throws IOException {
        StringReader reader = new StringReader(body);
        ObjectMapper mapperReader = new ObjectMapper();
        mapperReader.registerModule(new JavaTimeModule());
        mapperReader.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapperReader.configure(DeserializationFeature.FAIL_ON_INVALID_SUBTYPE, false);
//        mapperReader.configure(DeserializationFeature.USE_JAVA_ARRAY_FOR_JSON_ARRAY,true);
        JsonNode root = mapperReader.readTree(reader);
        User user = userService.findUserById(root.get("userId").longValue());
        user.setUsername(root.get("username").textValue());
        user.setEmail(root.get("email").textValue());
        user.setUpdatedAt(new Date());
        Branch branch = mapperReader.treeToValue(root.get("branch"), Branch.class);
        List<Role> roles = new ArrayList<>(Arrays.asList(mapperReader.treeToValue(root.get("roles"), Role[].class)));
//        user.setBranch(branch);
        if(!roles.isEmpty())
        user.setRoles(roles);
        User editedUser = userService.editUser(user);
        return new ResponseEntity<>(editedUser, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable Long userId) {

        User user = userService.findUserById(userId);
        if (user != null) {
            userService.deleteUser(user);
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
    @GetMapping("")
    public ResponseEntity<?> getAllUsers() {

        Iterable<User> users = userService.findAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyAuthority('SUPER-ADMIN','ADMIN','USER')")
    @GetMapping("/{userId}")
    public ResponseEntity<?> getUserById(@PathVariable Long userId) {

        User foundUser = userService.findUserById(userId);
        if (foundUser == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User with id " + userId + " cannot be found !!");
        }
        return new ResponseEntity<>(foundUser, HttpStatus.FOUND);
    }


}
