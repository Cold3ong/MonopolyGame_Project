
import java.util.ArrayList;

public class Player {
	
	//money player has
	private int money;
	
	//Property own by player
	private ArrayList<String> property = new ArrayList();		

	//current position of player
	private String currentPos;
	private int currentPosNo;
	private int playerNum;
	private int jailStop=0;
	private String name;
	private boolean gameOver=false;
	
	//constructor for new game
	public Player(int playerNum, String name, String firstPos) {
		
		setMoney(1500);
		setCurrentPos(firstPos);
		setCurrentPosNo(1);
		setPlayerNum(playerNum);
		setName(name);
	}
	
	
	//constructor for load game
	public Player(int money, int currentPosNo, int playerNum, ArrayList<String> property,String name) {
		

		setMoney(money);
		setCurrentPosNo(currentPosNo);
		setPlayerNum(playerNum);
		setProperty(property);
		setName(name);
		
	}
	
	
	


	public String getName() {
        return name;
    }

	public void setName(String name) {
        this.name = name;
    }

	public int getMoney() {
		return money;
	}


	public void setMoney(int money) {
		this.money = money;
	}


	public String getCurrentPos() {
		return currentPos;
	}


	public void setCurrentPos(String currentPos) {
		this.currentPos = currentPos;
	}

	public void setCurrentPosNo(int currentPosNo) {
		this.currentPosNo = currentPosNo;
	}

	public int getCurrentPosNo(){return currentPosNo;}


	public int getPlayerNum() {
		return playerNum;
	}


	public void setPlayerNum(int playerNum) {
		this.playerNum = playerNum;
	}


	public String getProperty() {
		String listToString ="";
		for(String s:property){
			listToString+= s+"\t";
		}
		return listToString;
	}


	public void setProperty(ArrayList<String> property) {
		this.property = property;

	}
	public void addProperty(String property){
		this.property.add(property);
		System.out.println(getName() + " now owns property: " + property);
	}

	public int getJailStop(){
		return this.jailStop;
	}

	public void setJailStop(int time){
		this.jailStop = time;
	}

	public void setGameOverStatus(boolean b){
		this.gameOver = b;
	}

	public boolean getGameOverStatus(){
		return	gameOver;
	}



	public ArrayList<String> getPropertyArrayList() {

		return property;
	}

	
}
