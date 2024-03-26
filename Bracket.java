public class Bracket {
	public Round sixtyFour;
	public Round thirtyTwo;
	public Round sixteen;
	public Round eight;
	public Round four;
	public Round championship;
	public Round champion;

	public Bracket(Round sixtyFour, Round thirtyTwo, Round sixteen, Round eight, Round four, Round championship,
			Round champion) {
		this.sixtyFour = sixtyFour;
		this.thirtyTwo = thirtyTwo;
		this.sixteen = sixteen;
		this.eight = eight;
		this.four = four;
		this.championship = championship;
		this.champion = champion;
	}
	
	public String toString() {
		Round[] rounds = {sixtyFour,thirtyTwo,sixteen,eight,four,championship,champion};
		String result = "";
		for (Round r : rounds) {
			result+=r.toString()+"\n";
		}
		return result;
	}

}
