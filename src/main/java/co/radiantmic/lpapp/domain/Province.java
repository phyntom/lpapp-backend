package co.radiantmic.lpapp.domain;

import javax.persistence.*;

@Entity
@Table(name = "province")
public class Province {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer provinceId;

    private String provinceName;

    public Province() {

    }

    public Integer getProvinceId() {

        return provinceId;
    }

    public void setProvinceId(Integer provinceId) {

        this.provinceId = provinceId;
    }

    public String getProvinceName() {

        return provinceName;
    }

    public void setProvinceName(String provinceName) {

        this.provinceName = provinceName;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (!(o instanceof Province)) return false;

        Province province = (Province) o;

        return getProvinceId() != null ? getProvinceId().equals(province.getProvinceId()) : province.getProvinceId() == null;
    }

    @Override
    public int hashCode() {

        return getProvinceId() != null ? getProvinceId().hashCode() : 0;
    }

    @Override
    public String toString() {

        return "Province{" +
                "provinceId=" + provinceId +
                ", provinceName='" + provinceName + '\'' +
                '}';
    }
}
