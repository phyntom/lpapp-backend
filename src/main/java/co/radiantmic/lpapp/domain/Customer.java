package co.radiantmic.lpapp.domain;

import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customerId;

    @JsonProperty("firstName")
    private String firstName;

    @JsonProperty("lastName")
    private String lastName;

    @JsonProperty("gender")
    private String gender;

    @JsonProperty("birthDate")
    @JsonFormat(pattern = "MM/dd/yyyy")
    private Date birthDate;

    @JsonProperty("age")
    private int age;

    @JsonProperty("spouse")
    private String spouse;

    @JsonProperty("phoneNumber")
    private String phoneNumber;

    @JsonProperty("email")
    private String email;

    @JsonProperty("nationalId")
    private String nationalId;

    private Date createdOn;

    private Date updatedOn;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "customer")
    private Set<CustomerPolicy> customerPolicies;

    public Customer() {

    }

    public Long getCustomerId() {

        return customerId;
    }

    public void setCustomerId(Long customerId) {

        this.customerId = customerId;
    }

    public String getFirstName() {

        return firstName;
    }

    public void setFirstName(String firstName) {

        this.firstName = firstName;
    }

    public String getLastName() {

        return lastName;
    }

    public void setLastName(String lastName) {

        this.lastName = lastName;
    }

    public int getAge() {

        return age;
    }

    public void setAge(int age) {

        this.age = age;
    }

    public String getSpouse() {

        return spouse;
    }

    public void setSpouse(String spouse) {

        this.spouse = spouse;
    }

    public String getPhoneNumber() {

        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {

        this.phoneNumber = phoneNumber;
    }

    public String getNationalId() {

        return nationalId;
    }

    public void setNationalId(String nationalId) {

        this.nationalId = nationalId;
    }

    public String getEmail() {

        return email;
    }

    public void setEmail(String email) {

        this.email = email;
    }

    public Date getBirthDate() {

        return birthDate;
    }

    public void setBirthDate(Date birthDate) {

        this.birthDate = birthDate;
    }

    public String getGender() {

        return gender;
    }

    public void setGender(String gender) {

        this.gender = gender;
    }

    public Date getCreatedOn() {

        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {

        this.createdOn = createdOn;
    }

    public Date getUpdatedOn() {

        return updatedOn;
    }

    public void setUpdatedOn(Date updatedOn) {

        this.updatedOn = updatedOn;
    }

    public Set<CustomerPolicy> getCustomerPolicies() {

        return customerPolicies;
    }

    public void setCustomerPolicies(CustomerPolicy... customerPolicies) {

        for (CustomerPolicy customerPolicy : customerPolicies) {
            customerPolicy.setCustomer(this);
        }
        this.customerPolicies = Stream.of(customerPolicies).collect(Collectors.toSet());
    }

    @PrePersist
    protected void onCreate() {

        this.createdOn = new Date();
    }

    @PreUpdate
    protected void onUpdate() {

        this.updatedOn = new Date();
    }

    @Override
    public String toString() {

        return "Customer{" +
                "customerId=" + customerId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", gender='" + gender + '\'' +
                '}';
    }
}
