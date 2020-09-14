package co.radiantmic.lpapp.domain;

import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Set;

@Entity
public class Policy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long policyId;

    @JsonProperty("entityId")
    private String entityId;

    @JsonProperty("entityName")
    private String entityName;

    @JsonProperty("policyNumber")
    private String policyNumber;

    @JsonProperty("policyType")
    private int policyType;

    @JsonProperty("startDate")
    @JsonFormat(pattern = "MM/dd/yyyy")
    private Date startDate;

    @JsonProperty("endDate")
    @JsonFormat(pattern = "MM/dd/yyyy")
    private Date endDate;

    @JsonProperty("sumInsured")
    private Long sumInsured;

    @JsonProperty("duration")
    private int durationMonth;

    @JsonProperty("createdOn")
    @JsonFormat(pattern = "MM/dd/yyyy")
    private Date createdOn;

    @JsonFormat(pattern = "MM/dd/yyyy")
    private Date updatedOn;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "policy")
    @JsonIgnoreProperties("policy")
    private Collection<CustomerPolicy> customerPolicies;

    @ManyToOne
    @JoinColumn(name = "branch_id")
    private Branch branch;

    @JsonProperty("bankName")
    private String bankName;

    private String createdBy;


    public Policy() {

    }

    public Long getPolicyId() {

        return policyId;
    }

    public void setPolicyId(Long policyId) {

        this.policyId = policyId;
    }

    public int getPolicyType() {

        return policyType;
    }

    public void setPolicyType(int policyType) {

        this.policyType = policyType;
    }

    public Date getStartDate() {

        return startDate;
    }

    public void setStartDate(Date startDate) {

        this.startDate = startDate;
    }

    public Date getEndDate() {

        return endDate;
    }

    public Long getSumInsured() {

        return sumInsured;
    }

    public void setSumInsured(Long sumInsured) {

        this.sumInsured = sumInsured;
    }

    public int getDurationMonth() {

        return durationMonth;
    }

    public void setDurationMonth(int durationMonth) {

        this.durationMonth = durationMonth;
    }

    public void setEndDate(Date endDate) {

        this.endDate = endDate;
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

    public Collection<CustomerPolicy> getCustomerPolicies() {

        return customerPolicies;
    }

    public void setCustomerPolicies(Collection<CustomerPolicy> customerPolicies) {

        this.customerPolicies = customerPolicies;
    }

    public Branch getBranch() {

        return branch;
    }

    public void setBranch(Branch branch) {

        this.branch = branch;
    }

    public String getCreatedBy() {

        return createdBy;
    }

    public void setCreatedBy(String createdBy) {

        this.createdBy = createdBy;
    }

    @PrePersist
    protected void onCreate() {
        this.createdOn = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedOn = new Date();
    }

    @PostPersist
    protected void afterCreate() {

        String policyTypePrefix = "";
        if (policyType == 1) {
            policyTypePrefix = "IND";
        } else if (policyType == 2) {
            policyTypePrefix = "GRP";
        }
        Calendar todayCalendar= Calendar.getInstance();
        this.policyNumber = "RDY001" + policyTypePrefix + todayCalendar.get(Calendar.YEAR) + String.format("%05d", this.getPolicyId());
    }

    public String getEntityId() {

        return entityId;
    }

    public void setEntityId(String entityId) {

        this.entityId = entityId;
    }

    public String getPolicyNumber() {

        return policyNumber;
    }

    public void setPolicyNumber(String policyNumber) {

        this.policyNumber = policyNumber;
    }

    public String getEntityName() {

        return entityName;
    }

    public void setEntityName(String entityName) {

        this.entityName = entityName;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (!(o instanceof Policy)) return false;

        Policy policy = (Policy) o;

        if (getPolicyId() != null ? !getPolicyId().equals(policy.getPolicyId()) : policy.getPolicyId() != null)
            return false;
        return getPolicyNumber() != null ? getPolicyNumber().equals(policy.getPolicyNumber()) : policy.getPolicyNumber() == null;
    }

    @Override
    public int hashCode() {

        int result = getPolicyId() != null ? getPolicyId().hashCode() : 0;
        result = 31 * result + (getPolicyNumber() != null ? getPolicyNumber().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {

        return "Policy{" +
                "entityId='" + entityId + '\'' +
                ", policyType=" + policyType +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", sumInsured=" + sumInsured +
                ", durationMonth=" + durationMonth +
                '}';
    }
}
