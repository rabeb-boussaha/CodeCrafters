package CodeCrafters.CodeCrafters.Repository;

import CodeCrafters.CodeCrafters.model.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Long> {

    @Modifying
    @Transactional
    @Query("UPDATE User u SET u.firstname = :firstname, u.lastname = :lastname WHERE u.id = :id")
    void updateUser(Long id, String firstname, String lastname);
}
