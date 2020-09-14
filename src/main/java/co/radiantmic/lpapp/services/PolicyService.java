package co.radiantmic.lpapp.services;

import co.radiantmic.lpapp.domain.Customer;
import co.radiantmic.lpapp.domain.CustomerPolicy;
import co.radiantmic.lpapp.domain.Policy;
import co.radiantmic.lpapp.exception.EntityNotFoundException;
import co.radiantmic.lpapp.repositories.CustomerPolicyRepository;
import co.radiantmic.lpapp.repositories.PolicyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class PolicyService {

    @Autowired
    private PolicyRepository policyRepository;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private CustomerPolicyRepository customerPolicyRepository;

    private Policy createdPolicy;

    private Set<CustomerPolicy> setCustomerPolicies;

    /**
     * method to create a policy
     *
     * @param policy
     * @return
     */
    public Policy createPolicy(Policy policy, List<CustomerPolicy> customersPolicies) {

        createdPolicy = policyRepository.save(policy);
        setCustomerPolicies= new HashSet<>();
        for (CustomerPolicy customerPolicy : customersPolicies) {
            Optional<Customer> optionalCustomer = customerService.getCustomerByNationalId(customerPolicy.getCustomer().getNationalId());
            if (!optionalCustomer.isPresent()) {
                Customer newCustomer = customerService.createCustomer(customerPolicy.getCustomer());
                customerPolicy.setPolicy(createdPolicy);
                customerPolicy.setCustomer(newCustomer);
                customerPolicy.setTotalPremium(customerPolicy.getTotalPremium());
                customerPolicy.setLoanAmount(customerPolicy.getLoanAmount());
                CustomerPolicy createdCustomerPolicy = customerPolicyRepository.save(customerPolicy);
                setCustomerPolicies.add(createdCustomerPolicy);
            }
            // if the customer is in the database we dont create him again. Instead we assign CustomerPolicy
            else {
//                Customer existingCustomer = optionalCustomer.get();
                Customer updatedCustomer = customerService.updateCustomer(customerPolicy.getCustomer());
                customerPolicy.setPolicy(createdPolicy);
                customerPolicy.setCustomer(updatedCustomer);
                customerPolicy.setTotalPremium(customerPolicy.getTotalPremium());
                customerPolicy.setLoanAmount(customerPolicy.getLoanAmount());
                CustomerPolicy createdCustomerPolicy = customerPolicyRepository.save(customerPolicy);
                setCustomerPolicies.add(createdCustomerPolicy);
            }
        }
        createdPolicy.setCustomerPolicies(setCustomerPolicies);
        return createdPolicy;
    }

    /**
     * method to update a policy
     *
     * @param policy
     * @return
     */
    public Policy updatePolicy(Policy policy,List<CustomerPolicy> customerPolicies) {
        Policy updatedPolicy = policyRepository.save(policy);
        setCustomerPolicies= new HashSet<>();
        for (CustomerPolicy customerPolicy : customerPolicies) {
            Optional<Customer> optionalCustomer = customerService.getCustomerByNationalId(customerPolicy.getCustomer().getNationalId());
            if (!optionalCustomer.isPresent()) {
                Customer newCustomer = customerService.createCustomer(customerPolicy.getCustomer());
                customerPolicy.setPolicy(updatedPolicy);
                customerPolicy.setCustomer(newCustomer);
                CustomerPolicy createdCustomerPolicy = customerPolicyRepository.save(customerPolicy);
                setCustomerPolicies.add(createdCustomerPolicy);
            }
            // if the customer is in the database we dont create him again. Instead we assign CustomerPolicy
            else {
                Customer existingCustomer = customerService.updateCustomer(customerPolicy.getCustomer());
                customerPolicy.setPolicy(updatedPolicy);
                customerPolicy.setCustomer(existingCustomer);
                CustomerPolicy createdCustomerPolicy = customerPolicyRepository.save(customerPolicy);
                setCustomerPolicies.add(createdCustomerPolicy);
            }
        }
        updatedPolicy.setCustomerPolicies(setCustomerPolicies);
        return createdPolicy;
    }

    /**
     * method to delete a policy
     *
     * @param policyId
     * @return
     */
    public Long deletePolicy(Long policyId) {

        Optional<Policy> optionalPolicy = policyRepository.findById(policyId);
        if (optionalPolicy.isPresent()) {
            Policy toDeletePolicy = optionalPolicy.get();
            policyRepository.delete(toDeletePolicy);
            return toDeletePolicy.getPolicyId();
        } else {
            throw new EntityNotFoundException("Policy not found !!!");
        }
    }

    /**
     * method to retrieve all policies
     *
     * @return
     */
    public List<Policy> getAllPolicies() {
        return policyRepository.findAll();
    }

    /**
     * method to retrieve all policies by createdBy
     *
     * @return
     */
    public List<Policy> getPoliciesByCreatedByAndPolicyType(String createdBy,Integer policyType) {

        return policyRepository.getPoliciesByCreatedByAndPolicyTypeOrderByCreatedOnDesc(createdBy,policyType);
    }

    /**
     * method to find a policy
     *
     * @return
     */
    public Policy getPolicyById(Long policyId) {

        Optional<Policy> optionalPolicy = policyRepository.findById(policyId);
        if (optionalPolicy.isPresent()) {
            Policy foundPolicy = optionalPolicy.get();
            return foundPolicy;
        } else {
            return null;
        }
    }

    /**
     * method to retrieve all policies by type Id
     *
     * @return
     */
    public List<Policy> getPoliciesByType(Integer policyType) {
        return policyRepository.getPoliciesByPolicyTypeOrderByCreatedOnDesc(policyType);
    }

}
