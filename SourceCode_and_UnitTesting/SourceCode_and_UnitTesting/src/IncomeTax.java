
public class IncomeTax extends Square{



	public IncomeTax(String type, int position) {
		
		
		setType(type);
		setPosition(position);
		
	}

	public void Trigger_incomeTax(Player player){
		int deduction=0;

		deduction = Math.round(player.getMoney()/100)*10;
		if(player.getMoney()<=deduction){
			player.setMoney(0);
			player.setGameOverStatus(true);
			System.out.println("\"You don't have enough money to pay -----> *Game Over!!*\"");
		}else{
			System.out.println("Original balance: $"+ player.getMoney());
			System.out.println("Paying 10% of your money...: $"+deduction);
			player.setMoney(player.getMoney()-deduction);
			System.out.println("Your balance is now : $"+ player.getMoney());
			System.out.println("Taxation is done !");
		}

	}


	
	

}
