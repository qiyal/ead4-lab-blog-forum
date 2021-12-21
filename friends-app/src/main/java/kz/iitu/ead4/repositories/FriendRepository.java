package kz.iitu.ead4.repositories;

import kz.iitu.ead4.models.Friend;
import kz.iitu.ead4.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FriendRepository extends JpaRepository<Friend,Integer> {

    boolean existsByFirstUserAndSecondUser(User first,User second);

    List<Friend> findByFirstUser(User user);
    List<Friend> findBySecondUser(User user);

}
