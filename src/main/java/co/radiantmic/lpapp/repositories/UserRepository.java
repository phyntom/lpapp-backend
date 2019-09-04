package co.radiantmic.lpapp.repositories;

import co.radiantmic.lpapp.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    @Override
    Optional<User> findById(Long aLong);

    User findByUserNameAndPassword(String userName,String password);

    Boolean existsByUserName(String userName);

    Boolean existsByEmail(String email);
}
