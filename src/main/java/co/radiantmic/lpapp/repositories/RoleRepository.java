package co.radiantmic.lpapp.repositories;

import co.radiantmic.lpapp.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Long> {
    Optional<Role> find
}
