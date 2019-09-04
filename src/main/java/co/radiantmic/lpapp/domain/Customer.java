package co.radiantmic.lpapp.domain;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customerId;

    private String customerName;

    private Date birthDate;

    private String gender;

    private String mobileNumber;

    private String nationalId;

    private Date createdOn;

    private Date updatedOn;

    public Customer() {

    }

    public Long getCustomerId() {

        return customerId;
    }

    public void setCustomerId(Long customerId) {

        this.customerId = customerId;
    }

    public String getCustomerName() {

        return customerName;
    }

    public void setCustomerName(String customerName) {

        this.customerName = customerName;
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

    @PrePersist
    protected void onCreate() {

        this.createdOn = new Date();
    }

    @PreUpdate
    protected void onUpdate() {

        this.updatedOn = new Date();
    }

}
