package co.radiantmic.lpapp.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigInteger;
import java.time.LocalDateTime;

public class GroupSale {

    private String policyNumber;

    private String entityName;

    private int age;

    private String nationalId;

    private String policyType;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private String startDate;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private String endDate;

    private String bankName;

    private String createdBy;

    private BigInteger sumInsured;

    private Integer duration;

    private BigInteger totalPremium;

    private BigInteger accessories;

    private BigInteger totalNetPremium;

    private BigInteger memberSumInsured;

    @JsonFormat(pattern = "dd/MM/yyyy hh:mm:ss")
    private LocalDateTime createdOn;

    private String memberNames;

    public GroupSale() {

    }

    public String getNationalId() {

        return nationalId;
    }

    public void setNationalId(String nationalId) {

        this.nationalId = nationalId;
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

    public int getAge() {

        return age;
    }

    public void setAge(int age) {

        this.age = age;
    }

    public String getPolicyType() {

        return policyType;
    }

    public void setPolicyType(String policyType) {

        this.policyType = policyType;
    }

    public String getStartDate() {

        return startDate;
    }

    public void setStartDate(String startDate) {

        this.startDate = startDate;
    }

    public String getEndDate() {

        return endDate;
    }

    public void setEndDate(String endDate) {

        this.endDate = endDate;
    }

    public String getBankName() {

        return bankName;
    }

    public void setBankName(String bankName) {

        this.bankName = bankName;
    }

    public String getCreatedBy() {

        return createdBy;
    }

    public void setCreatedBy(String createdBy) {

        this.createdBy = createdBy;
    }

    public BigInteger getSumInsured() {

        return sumInsured;
    }

    public void setSumInsured(BigInteger sumInsured) {

        this.sumInsured = sumInsured;
    }

    public Integer getDuration() {

        return duration;
    }

    public void setDuration(Integer duration) {

        this.duration = duration;
    }

    public BigInteger getTotalPremium() {

        return totalPremium;
    }

    public void setTotalPremium(BigInteger totalPremium) {

        this.totalPremium = totalPremium;
    }

    public BigInteger getAccessories() {

        return accessories;
    }

    public void setAccessories(BigInteger accessories) {

        this.accessories = accessories;
    }

    public BigInteger getTotalNetPremium() {

        return totalNetPremium;
    }

    public void setTotalNetPremium(BigInteger totalNetPremium) {

        this.totalNetPremium = totalNetPremium;
    }

    public LocalDateTime getCreatedOn() {

        return createdOn;
    }

    public void setCreatedOn(LocalDateTime createdOn) {

        this.createdOn = createdOn;
    }

    public BigInteger getMemberSumInsured() {

        return memberSumInsured;
    }

    public void setMemberSumInsured(BigInteger memberSumInsured) {

        this.memberSumInsured = memberSumInsured;
    }

    public String getMemberNames() {

        return memberNames;
    }

    public void setMemberNames(String memberNames) {

        this.memberNames = memberNames;
    }
}
