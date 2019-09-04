package co.radiantmic.lpapp.domain;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class PolicyType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long projectTypeId;

    private String projectTypeDesc;

    private LocalDateTime createdOn;

    private LocalDateTime updatedOn;
}
