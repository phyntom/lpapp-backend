package co.radiantmic.lpapp.domain;

import javax.persistence.*;
import java.math.BigInteger;
import java.time.LocalDateTime;

@Entity
public class Bank {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bankId;

    private String bankName;

    private BigInteger maxSumInsured;

    private LocalDateTime createdOn;

    private LocalDateTime updatedOn;

    public Bank() {

    }

    public Long getBankId() {

        return bankId;
    }

    public void setBankId(Long bankId) {

        this.bankId = bankId;
    }

    public String getBankName() {

        return bankName;
    }

    public void setBankName(String bankName) {

        this.bankName = bankName;
    }

    public BigInteger getMaxSumInsured() {

        return maxSumInsured;
    }

    public void setMaxSumInsured(BigInteger maxSumInsured) {

        this.maxSumInsured = maxSumInsured;
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
    protected void onCreate(){
        this.createdOn = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate(){
        this.updatedOn = LocalDateTime.now();
    }

}
