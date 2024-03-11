import java.util.Scanner;
public class Game{
	public Game(){}

	//the move method, it makes sure a correct option is chosen
	public int move(String message, String options, int numOptions){
		int playerInput = 0;
		Scanner input = new Scanner(System.in);
		System.out.println(message+", what do you do?\n"+options);
		boolean exists = false;
		while(!exists){
			playerInput = input.nextInt();
			if(playerInput <= numOptions && playerInput > 0){
				exists = true;
				break;
			}
			if(!exists){
				System.out.println("Sorry enter again!\n\n");
			}
		}
		System.out.println();
		input.close();
		return playerInput;
	}

	//returns 0 if a retreat (enemy or player), -1 if player dies, and the enemy.getExp() method if the player wins
	public int combat(Player player, Enemy enemy){
		System.out.println("You enter combat with "+enemy.getName());

		//Hp is put as a variable so it can be modified without mdifying hp of player or enemy, and atk is here for ease of access
		int enemyHp = enemy.getHealth();
		int enemyAtk = enemy.getAttack();
		int playerHp = player.getHealth();
		int playerAtk = player.getAttack();

		//inpu is for the 'move' method tracking
		int inpu = 0;

		//does a random number to determine what the enemy does (attack, or retreat)
		int enemyMove = 0;

		//tracking player chance to do something
		int r = 0;

		//turns true if you defend
		boolean defend = false;
		
		
		


		
		//normal fightstyle
		if(enemy.getStyle()==0){
			while(true){
				System.out.println("\n\nYour Hp : "+playerHp+"/"+player.getHealth()+"\nEnemy Hp: "+enemyHp+"/"+enemy.getHealth()+"\n");
				inpu = move("It is your turn","1. Attack\n2. Heal/Defend\n3. Retreat",3);
				if(inpu == 1){
					if(attack()){
						enemyHp-=playerAtk;
						System.out.println("You deal "+playerAtk+" damage to the enemy("+(enemyHp+playerAtk)+" --> "+enemyHp+")");
						if(enemyHp < 1){
							System.out.println("You win!\n\n");
							return enemy.getExp();
						}
					}
				} else if(inpu==2){
					defend = true;
				} else {
					if (retreat(80)){
						System.out.println("You sucessfully get away.\n\n");
						return 0;
					}
					System.out.println("You fail to get away!");
				}

				//enemy time
				System.out.println("Enemy turn!");
				enemyMove = (int)(Math.random()*100)+1;
				if(enemyMove > 20){
					if(attack()&&!defend){
						playerHp -= enemyAtk;
						System.out.println("You take "+enemyAtk+" damage("+(playerHp+enemyAtk)+" --> "+playerHp+")");
						if(playerHp < 1){
							System.out.println("You lose!\n\n");
							return -1;
						}
					}
					//if player defended
					else if(defend) {
						r = (int)(Math.random()*100);
						if(r<70){
							System.out.println("You block the attack!");
							if(playerHp+(playerAtk/2) < player.getHealth()){
								playerHp+=(playerAtk/2+1);
								System.out.println("You heal "+(playerAtk/2+1)+" hp: "+playerHp);
							}else{
								System.out.println("You heal "+(player.getHealth()-playerHp)+" hp: "+player.getHealth());
								playerHp=player.getHealth();
							}
						} else {
							System.out.println("You fail to block the attack!");
							playerHp -= enemyAtk;
							System.out.println("You take "+enemyAtk+" damage("+(playerHp+enemyAtk)+" --> "+playerHp+")");
							if(playerHp < 1){
								System.out.println("You lose!\n\n");
								return -1;
							}
						}
						defend = false;
					}
				} else {
					if(playerHp+(playerAtk/2) < player.getHealth()&&defend){
						playerHp+=(playerAtk/2+1);
						System.out.println("You heal "+(playerAtk/2+1)+" hp: "+playerHp);
					}else if(defend){
						System.out.println("You heal "+(player.getHealth()-playerHp)+" hp: "+player.getHealth());
						playerHp=player.getHealth();
					}
					System.out.println("The enemy tries to get away!\n");
					if(retreat(20)){
						System.out.println("The enemy gets away!\n\n");
						return 0;
					}
				}
			}
		}
		
		


		
		//boss fightstyle
		else if(enemy.getStyle()==1){
			while(true){
				System.out.println("\n\nYour Hp : "+playerHp+"/"+player.getHealth()+"\nEnemy Hp: "+enemyHp+"/"+enemy.getHealth()+"\n");
				inpu = move("It is your turn","1. Attack\n2. Heal/Defend",2);
				if(inpu == 1){
					if(attack()){
						enemyHp-=playerAtk;
						System.out.println("You deal "+playerAtk+" damage to the enemy("+(enemyHp+playerAtk)+" --> "+enemyHp+")");
						if(enemyHp < 1){
							System.out.println("You win!\n\n");
							return enemy.getExp();
						}
					}
				} else if(inpu==2){
					defend = true;
					
				}

				//enemy time
				System.out.println("Enemy turn!");
				if(attack()&&!defend){
					playerHp -= enemyAtk;
					System.out.println("You take "+enemyAtk+" damage("+(playerHp+enemyAtk)+" --> "+playerHp+")");
					if(playerHp < 1){
						System.out.println("You lose!\n\n");
						return -1;
					}
				}
				//if player defended
				else if(defend) {
					r = (int)(Math.random()*100);
					if(r<70){
						System.out.println("You block the attack!");
						if(playerHp+(playerAtk/2) < player.getHealth()){
							playerHp+=(playerAtk/2+1);
							System.out.println("You heal "+(playerAtk/2+1)+" hp: "+playerHp);
						}else{
								System.out.println("You heal "+(player.getHealth()-playerHp)+" hp: "+player.getHealth());
								playerHp=player.getHealth();
							}
					} else {
						System.out.println("You fail to block the attack!");
						playerHp -= enemyAtk;
						System.out.println("You take "+enemyAtk+" damage("+(playerHp+enemyAtk)+" --> "+playerHp+")");
						if(playerHp < 1){
							System.out.println("You lose!\n\n");
							return -1;
						}
					}
					defend = false;
				}
			}
		}
		
		
		


		
		//defenceless fightstyle
		else if(enemy.getStyle()==2){
			while(true){
				System.out.println("\n\nYour Hp : "+playerHp+"/"+player.getHealth()+"\nEnemy Hp: "+enemyHp+"/"+enemy.getHealth()+"\n");
				inpu = move("It is your turn","1. Attack\n2. Retreat",2);
				if(inpu == 1){
					if(attack()){
						enemyHp-=playerAtk;
						System.out.println("You deal "+playerAtk+" damage to the enemy("+(enemyHp+playerAtk)+" --> "+enemyHp+")");
						if(enemyHp < 1){
							System.out.println("You win!\n\n");
							return enemy.getExp();
						}
					}
				} else {
					if (retreat(80)){
						System.out.println("You sucessfully get away.\n\n");
						return 0;
					}
					System.out.println("You fail to run!");
				}

				//enemy time
				System.out.println("Enemy turn!");
				System.out.println("The enemy tries to get away!");
				if(retreat(20)){
					System.out.println("The enemy gets away!\n\n");
					return 0;
				}
			}
		}
		return -1;
	}




	//returns true if the attack hits, and false if it misses along with a "Miss!"
	public boolean attack(){
		int r = (int)(Math.random()*100);
		if(r<10){
			System.out.println("Miss!");
			return false;
		} else {
			return true;
		}
	}

	//basically the same as attack but without the printing of miss on a miss, so no need for an if
	public boolean retreat(int chance){
		int r  = (int)(Math.random()*100);
		return (r < chance);
	}
}

