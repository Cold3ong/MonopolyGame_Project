
public class Go extends Square{



	public Go(String type, int position) {
		
	
		setType(type);
		setPosition(position);
		
	}

	public void Trigger_Go(Player player){
		player.setMoney(player.getMoney()+1500);
	}




}