package services;

public interface ApplicationService {

	public User readUser(String id);
	
	public void createUser(User user);
	
	public void updateUser(User user);
	
	public void deleteUser(String id);
	
	public void createOrUpdateUser(User user);
	
}
