package repoz.repository;

import org.springframework.stereotype.Repository;

import repoz.model.User;

@Repository
public class UserRepository extends AbstractRepository<User> {

	public User findByUsername(String username) {
		return readQuery(User.class, "username", username);
	}

}