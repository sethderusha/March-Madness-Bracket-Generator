import java.util.ArrayList;

public class Round extends ArrayList<Team> {
	
	public Round() {
	}
	
	public Round randomRound() {
	    Round next = new Round();
	    for (int t = 0; t < this.size(); t += 2) {
	        Team t1 = this.get(t);
	        Team t2 = this.get(t + 1);
	        Team winner = random(t1, t2);
	        next.add(winner);
	    }
	    return next;
	}
	
	public Team random(Team t1, Team t2) {
		double probability = .5;
		double decision = Math.random();
		if (decision < probability) {
			return t1;
		}
		else {
			return t2;
		}
	}
	
	public Round weightedRound() {
		Round next = new Round();
	    for (int t = 0; t < this.size(); t += 2) {
	        Team t1 = this.get(t);
	        Team t2 = this.get(t + 1);
	        Team winner = weighted(t1, t2);
	        next.add(winner);
	    }
	    return next;
	}
	
	public Team weighted(Team t1, Team t2) {
		double probability = (double)t2.seed/((double)t1.seed+(double)t2.seed);
		double decision = Math.random();
		if (decision < probability) {
			return t1;
		}
		else {
			return t2;
		}
	}
	
	public Round historicalRound() {
		Round next = new Round();
	    for (int t = 0; t < this.size(); t += 2) {
	        Team t1 = this.get(t);
	        Team t2 = this.get(t + 1);
	        Team winner = historical(t1, t2);
	        next.add(winner);
	    }
	    return next;
	}
	
	public Team historical(Team t1, Team t2) {
		double[][] winRates = {
				{.5,.545,.634,.705,.797,.706,.857,.785,.906,.875,.556,1,1,0,0,.987},
				{.455,.5,.606,.5,.25,.722,.696,.4,.667,.641,.842,1,0,0,.928,0},
				{.366,.394,.5,.556,.5,.583,.632,1,.75,.692,.667,0,0,.855,.667,0},
				{.295,.5,.444,.5,.573,.333,.333,.385,.4,1,0,.705,.789,0,0,0},
				{.203,.75,.5,.427,.5,1,0,.25,.4,1,0,.674,.85,0,0,0},
				{.294,.278,.417,.667,0,0,.667,.25,0,.6,.628,0,0,.875,1,0},
				{.143,.304,.368,.667,0,.333,0,.5,0,.606,0,0,0,1,.333,0},
				{.215,.6,0,.615,.75,.75,.5,0,.511,0,1,0,1,0,1,0},
				{.094,.333,.25,.6,.6,0,0,.489,0,1,0,0,1,0,0,1},
				{.125,.359,.308,0,0,.4,.394,0,0,0,.5,0,0,1,1,0},
				{.444,.158,.333,0,0,.372,1,0,1,.5,.5,0,0,1,0,0},
				{0,0,0,.295,.326,0,0,1,0,0,0,.5,.75,0,0,0},
				{0,0,0,.211,.15,0,0,0,0,0,0,.25,.5,0,0,0},
				{0,0,.145,0,0,.125,0,0,0,0,0,0,0,.5,0,0},
				{0,.072,.333,0,0,0,.667,0,0,0,0,0,0,0,0,0},
				{.013,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}};
		double probability = winRates[t1.seed-1][t2.seed-1];
		double decision = Math.random();
		if (decision < probability) {
			return t1;
		}
		else {
			return t2;
		}
	}
	
	public String toString() {
		String result = this.size()+"-\n";
		for (int t = 0; t < this.size(); t++) {
			result += this.get(t)+"\n";
		}
		
		return result;
	}
}
