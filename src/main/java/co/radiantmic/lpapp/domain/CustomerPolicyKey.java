package co.radiantmic.lpapp.domain;
import javax.persistence.Column;
import java.io.Serializable;

public class CustomerPolicyKey implements Serializable {

    @Column(name="customer_id")
    private Long customer;

    @Column(name="customer_id")
    private Long policy;

    public CustomerPolicyKey() {

    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (!(o instanceof CustomerPolicyKey)) return false;

        CustomerPolicyKey that = (CustomerPolicyKey) o;

        if (customer != null ? !customer.equals(that.customer) : that.customer != null) return false;
        return policy != null ? policy.equals(that.policy) : that.policy == null;
    }

    @Override
    public int hashCode() {

        int result = customer != null ? customer.hashCode() : 0;
        result = 31 * result + (policy != null ? policy.hashCode() : 0);
        return result;
    }
}
