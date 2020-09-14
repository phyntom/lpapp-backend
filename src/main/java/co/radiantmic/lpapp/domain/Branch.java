package co.radiantmic.lpapp.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="branch")
public class Branch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer branchId;

    @JsonProperty("branchName")
    private String branchName;

    @ManyToOne
    @JoinColumn(name = "bank_id")
    @JsonProperty("bank")
    private Bank bank;

    @ManyToOne
    @JoinColumn(name="provinceId")
    @JsonProperty("province")
    private Province province;

    @ManyToOne
    @JoinColumn(name="district_id")
    @JsonProperty("district")
    private District district;

    private Date createdOn;

    private Date updatedOn;

    @JsonProperty("createdBy")
    private String createdBy;

    public Branch() {

    }

    public Integer getBranchId() {

        return branchId;
    }

    public void setBranchId(Integer branchId) {

        this.branchId = branchId;
    }

    public String getBranchName() {

        return branchName;
    }

    public void setBranchName(String branchName) {

        this.branchName = branchName;
    }

    public Bank getBank() {

        return bank;
    }

    public void setBank(Bank bank) {

        this.bank = bank;
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

    public Province getProvince() {

        return province;
    }

    public void setProvince(Province province) {

        this.province = province;
    }

    public District getDistrict() {

        return district;
    }

    public void setDistrict(District district) {

        this.district = district;
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

    @Override
    public String toString() {

        return "Branch{" +
                "branchName='" + branchName + '\'' +
                ", bank=" + bank +
                ", province=" + province +
                ", district=" + district +
                '}';
    }
}
