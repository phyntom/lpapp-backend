package co.radiantmic.lpapp.repositories;

import co.radiantmic.lpapp.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Override
    Optional<User> findById(Long aLong);

    User findByUsernameAndPassword(String username, String password);

    User findByUsername(String username);

}
