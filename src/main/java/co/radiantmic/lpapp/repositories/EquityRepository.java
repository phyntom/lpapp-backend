package co.radiantmic.lpapp.repositories;

import co.radiantmic.lpapp.domain.Equity;
import co.radiantmic.lpapp.domain.EquityId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EquityRepository extends JpaRepository<Equity, EquityId> {

}
