package services;

public interface Observed {
	public void watch(Observer o);
	public void unWatch(Observer o);
	public void sendUpdate();
}