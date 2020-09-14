package co.radiantmic.lpapp.domain;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class EquityId implements Serializable {

    private Integer age;

    private double periodMonth;

    public EquityId() {

    }

    public Integer getAge() {

        return age;
    }

    public void setAge(Integer age) {

        this.age = age;
    }

    public double getPeriodMonth() {

        return periodMonth;
    }

    public void setPeriodMonth(double periodMonth) {

        this.periodMonth = periodMonth;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (!(o instanceof EquityId)) return false;

        EquityId equityId = (EquityId) o;

        if (Double.compare(equityId.getPeriodMonth(), getPeriodMonth()) != 0) return false;
        return getAge().equals(equityId.getAge());
    }

    @Override
    public int hashCode() {

        int result;
        long temp;
        result = getAge().hashCode();
        temp = Double.doubleToLongBits(getPeriodMonth());
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
