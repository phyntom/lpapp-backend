package co.radiantmic.lpapp.domain;

import javax.persistence.*;
import java.math.BigInteger;
import java.time.LocalDateTime;

@Entity
@Table(name = "bank")
public class Bank {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer bankId;

    private String bankName;

    private String bankType;

    private BigInteger maxSumInsured;

    private double discount;

    private LocalDateTime createdOn;

    private LocalDateTime updatedOn;

    public Bank() {

    }

    public Bank(String bankName, String bankType, BigInteger maxSumInsured, double discount) {

        this.bankName = bankName;
        this.bankType = bankType;
        this.maxSumInsured = maxSumInsured;
        this.discount = discount;
    }

    public Integer getBankId() {

        return bankId;
    }

    public double getDiscount() {

        return discount;
    }

    public void setDiscount(double discount) {

        this.discount = discount;
    }

    public void setBankId(Integer bankId) {

        this.bankId = bankId;
    }

    public String getBankName() {

        return bankName;
    }

    public void setBankName(String bankName) {

        this.bankName = bankName.toUpperCase();
    }

    public String getBankType() {

        return bankType;
    }

    public void setBankType(String bankType) {

        this.bankType = bankType.toUpperCase();
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
