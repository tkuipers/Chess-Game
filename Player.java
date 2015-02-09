abstract class Player{
	private char team;
	public Player(char myTeam){
		// System.out.println(myTeam);
		this.team = myTeam;
	}
	public char getTeam(){
		return team;
	}
	public abstract Board promptUser(Board board);
	


}