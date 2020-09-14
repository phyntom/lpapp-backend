package co.radiantmic.lpapp.domain;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class PolicyType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long projectTypeId;

    private String projectTypeDesc;

    private LocalDateTime createdOn;

    private LocalDateTime updatedOn;

    public PolicyType() {

    }

    public Long getProjectTypeId() {

        return projectTypeId;
    }

    public void setProjectTypeId(Long projectTypeId) {

        this.projectTypeId = projectTypeId;
    }

    public String getProjectTypeDesc() {

        return projectTypeDesc;
    }

    public void setProjectTypeDesc(String projectTypeDesc) {

        this.projectTypeDesc = projectTypeDesc;
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


}
