package co.radiantmic.lpapp.domain;

import javax.persistence.*;

@Entity
@Table(name = "equity")
public class Equity {

    @EmbeddedId
    private EquityId equityId;

    private double rate;

    private double periodYear;

    public Equity() {

    }

    public EquityId getEquityId() {

        return equityId;
    }

    public void setEquityId(EquityId equityId) {

        this.equityId = equityId;
    }

    public double getRate() {

        return rate;
    }

    public void setRate(double rate) {

        this.rate = rate;
    }

    public double getPeriodYear() {

        return periodYear;
    }

    public void setPeriodYear(double periodYear) {

        this.periodYear = periodYear;
    }
}
