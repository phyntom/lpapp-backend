package co.radiantmic.lpapp.domain;
import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="district")
public class District {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer districtId;

    private String districtName;

    private Date createdOn;

    @ManyToOne
    @JoinColumn(name="provinceId")
    private Province province;

    public District() {

    }

    public Integer getDistrictId() {

        return districtId;
    }

    public void setDistrictId(Integer districtId) {

        this.districtId = districtId;
    }

    public String getDistrictName() {

        return districtName;
    }

    public void setDistrictName(String districtName) {

        this.districtName = districtName;
    }

    public Date getCreatedOn() {

        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {

        this.createdOn = createdOn;
    }

    public Province getProvince() {

        return province;
    }

    public void setProvince(Province province) {

        this.province = province;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (!(o instanceof District)) return false;

        District district = (District) o;

        return getDistrictId() != null ? getDistrictId().equals(district.getDistrictId()) : district.getDistrictId() == null;
    }

    @Override
    public int hashCode() {

        return getDistrictId() != null ? getDistrictId().hashCode() : 0;
    }

    @Override
    public String toString() {

        return "District{" +
                "districtId=" + districtId +
                ", districtName='" + districtName + '\'' +
                '}';
    }
}
