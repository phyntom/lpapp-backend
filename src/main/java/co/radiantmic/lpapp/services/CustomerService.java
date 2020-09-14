package co.radiantmic.lpapp.services;

import co.radiantmic.lpapp.domain.Customer;
import co.radiantmic.lpapp.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    /**
     * method to create customer
     *
     * @param customer
     * @return
     */
    public Customer createCustomer(Customer customer) {
        try {
            return customerRepository.save(customer);
        }
        catch(Exception ex){
            ex.printStackTrace();
            return null;
        }
    }

    public Customer updateCustomer(Customer customer){
        try {
            Optional<Customer> optionalCustomer = customerRepository.findByNationalId(customer.getNationalId());
            if (optionalCustomer.isPresent()) {
                Customer existingCustomer = optionalCustomer.get();
                existingCustomer.setFirstName(customer.getFirstName());
                existingCustomer.setLastName(customer.getLastName());
                existingCustomer.setBirthDate(customer.getBirthDate());
                existingCustomer.setEmail(customer.getEmail());
                existingCustomer.setNationalId(customer.getNationalId());
                existingCustomer.setPhoneNumber(customer.getPhoneNumber());
                existingCustomer.setAge(customer.getAge());
                existingCustomer.setSpouse(customer.getSpouse());
                return customerRepository.save(existingCustomer);
            }
            return null;
        }
        catch(Exception ex){
            ex.printStackTrace();
            return null;
        }
    }

    /**
     * method to get all customers
     *
     * @return
     */
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Optional<Customer> getCustomerByNationalId(String nationalId){
        return customerRepository.findByNationalId(nationalId);
    }
}
