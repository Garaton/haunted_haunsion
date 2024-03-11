import java.util.Scanner;
public class Player{
	private String[] inventory;
	private int[] invVal;
	private String[] keyItems = {"","","","","","","","",""};
	private String name;
	private int invSize, gold, invMax, exp;
	private int atk,hp;
	private int level;
	public Player(int inv){
		Scanner namething = new Scanner(System.in);
		System.out.println("Enter the name of your Character!");
		name = namething.next();
		namething.close();
		inventory = new String[999];
		invVal = new int[999];
		invMax = inv;
		invSize = 0;
		gold = 10;
		exp = 0;
		atk = 1;
		hp = 6;
		level = 1;
	}

	//returns the player's current inventory array
	public String[] getInvArray(){
		String[] arr = new String[invSize];
		for(int i = 0; i < invSize; i++){
			arr[i]=inventory[i];
		}
		return arr;
	}

	//returns the player's current price inventory (which correlates to the item inventory)
	public int[] getPriceArray(){
		int[] arr = new int[invSize];
		for(int i = 0; i < invSize; i++){
			arr[i]=invVal[i];
		}
		return arr;
	}

	//this returns the player's inventory as a string, with the currently held gold.
	public String getInventory(){
		String inv = "";
		if(invSize == 0){
			return "-no inv\nGold : "+gold+"\n";
		}
		for(int i = 0; i < invSize; i++){
			inv+=inventory[i]+",";
		}
		inv+="\nGold : "+gold+"\n";
		return inv;
	}

	//this gets the correlating price value in the price array, returns -1 if it fails
	public int getItemValBasedOnItem(String item){
		int index = -1;
		for(int i = 0; i < invSize; i++){
			if(inventory[i].equalsIgnoreCase(item)){
				index = i;
				break;
			}
		}
		if(index != -1){
			return invVal[index];
		}
		return -1;
	}

	//returns a item, for use outside of player
	public String getItem(int index){
		return inventory[index];
	}

	//returns the level
	public int getLevel(){
		return level;
	}

	//returns a key item, for use outside of player
	public String getKeyItem(int index){
		return keyItems[index];
	} 

	//returns a price, for use outside of player
	public int getItemVal(int index){
		return invVal[index];
	}

	//makes the inventory allowence for size larger
	public void addInvMax(int max){
		invMax += max;
	}

	//a basic search method that goes through the inventoy array and returns true if it finds the item
	public boolean hasItem(String item){
		for(int i = 0; i < invSize; i++){
			if(inventory[i].equals(item)){
				return true;
			}
		}
		return false;
	}

	//a basic search method that goes through the inventoy array and returns true if it finds the item
	public boolean hasKeyItem(String item){
		for(int i = 0; i < 5; i++){
			if(keyItems[i].equalsIgnoreCase(item)){
				return true;
			}
		}
		return false;
	}

	//adds a key item to the keyItems array, for use outside of function
	public void addKeyItem(String add){
		keyItems[keyItems.length]=add;
	}

	//removes a item from the inventory and makes invSize go down so if a new item is add it wont die
	public void removeItem(int index){
		for(int i = index+1; i < invSize; i++){
			inventory[i-1]=inventory[i];
			invVal[i-1]=invVal[i];
		}
		invSize--;
	}

	//adds a item to the inventory, the 'add' string goes into the inventory array, the 'value' int goes into the price array.
	//the most of this method is the thing that makes sure a player can't overstore items
	public void addItem(String add,int value){
		if(invSize==invMax){
			System.out.println("Inventory Max please choose what to remove!");
			Scanner playerIn = new Scanner(System.in);
			boolean done = false;
			int itemRemove;
			while(!done){
				System.out.println(getInventory()+"\nWhat item do you want to remove (start with 0 then count up, or enter -1 to remove the item you wanted to add)");
				itemRemove=playerIn.nextInt();
				int playeinp;
				if(itemRemove>invSize){}
				else if(itemRemove == -1){
					done = true;
				} else {
					System.out.println("Are you sure you want to remove "+getItem(itemRemove)+" worth "+getItemVal(itemRemove)+"? (1 for yes, anything else for no)");
					playeinp=playerIn.nextInt();
					if(playeinp==1){
						inventory[itemRemove]=add;
						invVal[itemRemove]=value;
						done = true;
					}
				}
			}
			playerIn.close();
		} else {
			inventory[invSize] = add;
			invVal[invSize] = value;
			invSize++;
			System.out.println("You obtain: "+add);
		}
	}

	//returns the inventory size, or how many items are currently inside inventory
	public int getInvSize(){
		return invSize;
	}

	//returns the gold
	public int getGold(){
		return gold;
	}

	//returns the health of the player
	public int getHealth(){
		return hp;
	}

	//returns the attack of the player
	public int getAttack(){
		return atk;
	}

	//returns the name
	public String getName(){
		return name;
	}

	//adds gold, and prints out a line that says stuff
	public void addGold(int g){
		gold+=g;
		System.out.println("You gain "+g+" G!\nYou now have : "+gold+" G");
	}

	//adds exp and levels you up if you have enough exp, it's inside a while loop so it doesnt cheat the player out of levels
	public void addExp(int amoung){
		exp += amoung;
		System.out.println("You have gained "+amoung+" exp\n");
		while (exp >= 100){
			Scanner which = new Scanner(System.in);
			int choice = 2;
			level+=1;
			System.out.println("You leveled up!\nDo you have 2 more hp or 1 more atk!(0 for hp, 1 for atk)\n");
			choice = which.nextInt();
			while(true){
				if(choice == 0){
					hp+=2;
					break;
				} else if (choice == 1){
					atk++;
					break;
				}
				System.out.println("enter \"0\" for 1 more hp, or enter \"1\" for 1 more atk");
				choice = which.nextInt();
			}
			which.close();
			exp-=100;
		}
	}

	//returns a string that consists of the inventory, the amount of gold, and the name
	public String toString(){
		return getInventory()+"\nGold: "+gold+"\nName: "+name;
	}
}

