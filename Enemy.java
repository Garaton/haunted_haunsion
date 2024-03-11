//the enemy class is basically only for combat and stores variables to use, done this way for it being easier to edit the enemies

public class Enemy{
	private int hp, atk, expValue, style;
	private String name;
	public Enemy (int health, int attack, int exp, String nam, int fighting){
		hp = health;
		atk = attack;
		name = nam;
		expValue = exp;
		style = fighting;
	}

	//the getters are all used within the Game's combat method, but could be used in other methods
	public String getName(){
		return name;
	}
	public int getAttack(){
		return atk;
	}
	public int getHealth(){
		return hp;
	}
	public int getExp(){
		return expValue;
	}
	//style determines how it fights, 0 is normal fighting, 1 is boss fighitng (boss can't retreat), 2 is defenseless fighting style (for things that can't fight back, they only retreat)
	public int getStyle(){
		return style;
	}

	public String toString(){
		return "Name: "+name+"\nHP: "+hp+"\nAttack: "+atk;
	}
}

