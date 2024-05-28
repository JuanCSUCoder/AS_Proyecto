package co.edu.javeriana.repositories;

import org.springframework.data.repository.CrudRepository;

import co.edu.javeriana.model.User;

public interface UserRepository extends CrudRepository<User, String> {
    User findByUserPod(String userPod);

}
