package co.radiantmic.lpapp.web;

import co.radiantmic.lpapp.domain.Customer;
import co.radiantmic.lpapp.domain.CustomerPolicy;
import co.radiantmic.lpapp.domain.Policy;
import co.radiantmic.lpapp.domain.User;
import co.radiantmic.lpapp.exception.BadRequestException;
import co.radiantmic.lpapp.services.CustomerService;
import co.radiantmic.lpapp.services.PolicyService;
import co.radiantmic.lpapp.services.UserService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.io.StringReader;
import java.lang.reflect.GenericArrayType;
import java.util.*;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/policies")
public class PolicyController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private PolicyService policyService;

    @Autowired
    private UserService userService;

    private final Logger log = LoggerFactory.getLogger(PolicyController.class.getName());

    private StringReader reader;

    private ObjectMapper mapperReader;

    private JsonNode root;

    private String username;

    @PostMapping("")
    public ResponseEntity<?> createNewSinglePolicy(@RequestBody String body, Authentication auth) {

        try {
            reader = new StringReader(body);
            mapperReader = new ObjectMapper();
            mapperReader = mapperReader.registerModule(new JavaTimeModule());
            mapperReader.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            root = mapperReader.readTree(reader);
            System.out.println(root);
            username = ((org.springframework.security.core.userdetails.User) auth.getPrincipal()).getUsername();
            User createdBy = userService.findByUsername(username);
            Policy policy = mapperReader.treeToValue(root, Policy.class);
//            policy.setBranch(createdBy.getBranch());
            policy.setCreatedBy(username);
            List<CustomerPolicy> customerPolicies = Arrays.asList(mapperReader.treeToValue(root.get("customerPolicies"), CustomerPolicy[].class));
            Policy createdPolicy = policyService.createPolicy(policy, customerPolicies);
            System.out.println(createdPolicy);
            return new ResponseEntity<>(createdPolicy, HttpStatus.CREATED);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new BadRequestException("Cannot create customer policy | " + ex.toString());
        }
    }

    @PutMapping("")
    public ResponseEntity<?> updatePolicy(@RequestBody String body, Authentication auth) {

        try {
            reader = new StringReader(body);
            mapperReader = new ObjectMapper();
            mapperReader.registerModule(new JavaTimeModule());
            mapperReader.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            JsonNode root = mapperReader.readTree(reader);
            String username = ((org.springframework.security.core.userdetails.User) auth.getPrincipal()).getUsername();
            User createdBy = userService.findByUsername(username);
            Policy policy = mapperReader.treeToValue(root, Policy.class);
            policy.setCreatedBy(username);
            List<CustomerPolicy> customerPolicies = Arrays.asList(mapperReader.treeToValue(root.get("customerPolicies"), CustomerPolicy[].class));
            Policy updatedPolicy = policyService.updatePolicy(policy, customerPolicies);
            return new ResponseEntity<>(updatedPolicy, HttpStatus.OK);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new BadRequestException("Cannot create customer policy | " + ex.toString());
        }
    }

    @GetMapping("/{policyId}")
    public ResponseEntity<?> getSinglePolicyBy(@PathVariable Long policyId) {

        Policy foundPolicy = policyService.getPolicyById(policyId);
        if (foundPolicy == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(foundPolicy, HttpStatus.FOUND);
    }

    @GetMapping("")
    public ResponseEntity<?> getAllPolicies() {

        List<Policy> policyList = policyService.getAllPolicies();
        return new ResponseEntity<>(policyList, HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<?> getPoliciesByPolicyType(@RequestParam Integer policyType, Authentication auth) {

        List<Policy> policyList;
        String username = ((org.springframework.security.core.userdetails.User) auth.getPrincipal()).getUsername();
        User createdBy = userService.findByUsername(username);
        Boolean isAdmin = createdBy.getRoles().stream().filter(role -> role.getRoleName().equals("ADMIN")).findAny().isPresent();
        if (isAdmin) {
            policyList = policyService.getPoliciesByType(policyType);
            policyList.stream().forEach(item -> {
                sortList(item.getCustomerPolicies());
            });
            return new ResponseEntity<>(policyList, HttpStatus.OK);
        } else {
            policyList = policyService.getPoliciesByCreatedByAndPolicyType(createdBy.getUsername(), policyType);
            policyList.stream().forEach(item -> {
                sortList(item.getCustomerPolicies());
            });
            return new ResponseEntity<>(policyList, HttpStatus.OK);
        }

    }

    /**
     * @param customerPolicies
     * @return
     */
    public Collection<CustomerPolicy> sortList(Collection<CustomerPolicy> customerPolicies) {
        return customerPolicies.stream().sorted(Comparator.comparing(CustomerPolicy::getCustomerPolicyId).reversed()).collect(Collectors.toList());
    }


}
