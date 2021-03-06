package repoz.repository;

import org.springframework.stereotype.Repository;

import repoz.model.User;

@Repository
public class UserRepository extends AbstractRepository<User> {

	public User findUserByUsername(String username) {
		return readQueryFromValue(User.class, "username", username);
	}

}
