package co.radiantmic.lpapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class CustomerPolicy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customerPolicyId;

    @ManyToOne
    @JoinColumn(name = "policy_id")
    @JsonIgnoreProperties("customerPolicies")
    private Policy policy;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    @JsonIgnoreProperties("customerPolicies")
    private Customer customer;

    @JsonProperty("netPremium")
    private Long netPremium;

    @JsonProperty("accessories")
    private Long accessories;

    @JsonProperty("rate")
    private Double rate;

    @JsonProperty("totalPremium")
    private Long totalPremium;

    @JsonProperty("loanAmount")
    private Long loanAmount;

    public CustomerPolicy() {

    }

    public CustomerPolicy(Policy policy, Customer customer, Long netPremium, Long accessories, Double rate) {

        this.policy = policy;
        this.customer = customer;
        this.netPremium = netPremium;
        this.accessories = accessories;
        this.rate = rate;
    }

    public Policy getPolicy() {

        return policy;
    }

    public void setPolicy(Policy policy) {

        this.policy = policy;
    }

    public Customer getCustomer() {

        return customer;
    }

    public void setCustomer(Customer customer) {

        this.customer = customer;
    }

    public Long getNetPremium() {

        return netPremium;
    }

    public void setNetPremium(Long netPremium) {

        this.netPremium = netPremium;
    }

    public Long getAccessories() {

        return accessories;
    }

    public void setAccessories(Long accessories) {

        this.accessories = accessories;
    }

    public Double getRate() {

        return rate;
    }

    public void setRate(Double rate) {

        this.rate = rate;
    }

    public Long getTotalPremium() {

        return totalPremium;
    }

    public void setTotalPremium(Long totalPremium) {

        this.totalPremium = totalPremium;
    }

    public Long getLoanAmount() {

        return loanAmount;
    }

    public void setLoanAmount(Long loanAmount) {

        this.loanAmount = loanAmount;
    }

    public Long getCustomerPolicyId() {

        return customerPolicyId;
    }

    public void setCustomerPolicyId(Long customerPolicyId) {

        this.customerPolicyId = customerPolicyId;
    }


    @Override
    public String toString() {

        return " | customerPolicyId=" + customerPolicyId;
    }
}
