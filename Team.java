public class Team {
	public String name;
	public int seed;
	
	public Team(String name, int seed) {
		this.name = name;
		this.seed = seed;
	}
	
	public String toString() {
		return seed+" "+name;
	}
}