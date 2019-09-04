package co.radiantmic.lpapp.domain;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Policy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long policyId;

    private String policyType;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private LocalDateTime createdOn;

    private LocalDateTime updatedOn;


    public Policy() {

    }

    public Long getPolicyId() {

        return policyId;
    }

    public void setPolicyId(Long policyId) {

        this.policyId = policyId;
    }

    public String getPolicyType() {

        return policyType;
    }

    public void setPolicyType(String policyType) {

        this.policyType = policyType;
    }

    public LocalDateTime getStartDate() {

        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {

        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {

        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {

        this.endDate = endDate;
    }

    public LocalDateTime getCreatedOn() {

        return createdOn;
    }

    public void setCreatedOn(LocalDateTime createdOn) {

        this.createdOn = createdOn;
    }

    public LocalDateTime getUpdatedOn() {

        return updatedOn;
    }

    public void setUpdatedOn(LocalDateTime updatedOn) {

        this.updatedOn = updatedOn;
    }

    @PrePersist
    protected void onCreate() {

        this.createdOn = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {

        this.updatedOn = LocalDateTime.now();
    }
}
