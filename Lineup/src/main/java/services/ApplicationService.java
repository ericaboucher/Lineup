package services;

import java.util.List;

import beans.User;

public interface ApplicationService {
	
	public List<User> readUsers();

	public User readUser(String id);
	
	public int createUser(User user);
	
	public int deleteUser(String id);

	int updateUser(String firstName, String lastName, String email, String phoneNum, String password);

	int updateUser(String firstName, String lastName, String newEmail, String phoneNum, String password,
			String oldEmail);	
}