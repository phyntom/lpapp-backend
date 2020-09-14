package co.radiantmic.lpapp.web;

import co.radiantmic.lpapp.domain.Branch;
import co.radiantmic.lpapp.domain.User;
import co.radiantmic.lpapp.services.BranchService;
import co.radiantmic.lpapp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*",maxAge = 36000000)
@RestController
@RequestMapping("/api/branches")
public class BranchController {

    @Autowired
    BranchService branchService;

    @Autowired
    UserService userService;

    @GetMapping("")
    public ResponseEntity<?> getAllBranches(){
        List<Branch> branches = branchService.getAllBranches();
        return new ResponseEntity<>(branches, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<?> createNewBranch(@Valid @RequestBody Branch branch, Authentication auth){
        String username = ((org.springframework.security.core.userdetails.User)auth.getPrincipal()).getUsername();
        branch.setCreatedBy(username);
        Branch createdBranch = branchService.createBranch(branch);
        return new ResponseEntity<>(createdBranch, HttpStatus.OK);
    }

    @PutMapping("")
    public ResponseEntity<?> updateBranch(@Valid @RequestBody Branch branch){
        Branch updatedBranch= branchService.updateBranch(branch);
        return new ResponseEntity<>(updatedBranch, HttpStatus.OK);
    }
}
