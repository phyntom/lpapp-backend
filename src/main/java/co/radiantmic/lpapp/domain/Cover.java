package co.radiantmic.lpapp.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Cover {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long coverId;

    private String coverDescription;

    public Cover() {

    }

    public Long getCoverId() {

        return coverId;
    }

    public void setCoverId(Long coverId) {

        this.coverId = coverId;
    }
}
