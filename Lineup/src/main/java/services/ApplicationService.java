package services;

import java.util.List;

import beans.User;

public interface ApplicationService {
	
	public List<User> readUsers();

	public User readUser(String id);
	
	public int createUser(User user);
	
	public int updateUser(User user);
	
	public int deleteUser(String id);
	
	public void createOrUpdateUser(User user);

	int deleteUser(User currentUser);
	
}
