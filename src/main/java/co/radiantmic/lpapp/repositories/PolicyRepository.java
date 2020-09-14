package co.radiantmic.lpapp.repositories;

import co.radiantmic.lpapp.domain.Policy;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PolicyRepository extends JpaRepository<Policy,Long> {
    List<Policy> getPoliciesByPolicyTypeOrderByCreatedOnDesc(Integer policyType);
    List<Policy> getPoliciesByCreatedByAndPolicyTypeOrderByCreatedOnDesc(String createdBy,Integer policyType);
}
