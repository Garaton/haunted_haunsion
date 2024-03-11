import java.util.Scanner;
class Main {
  public static void main(String[] args) {
		//variables for preforming game actions
		int playerInput=0;
    	Game game = new Game();
		Scanner inputTracker = new Scanner(System.in);

		//the player, self evident
		Player player = new Player(10);
		
		//enemies
		//tutorial enemy
		Enemy trainingDummy = new Enemy(5,1,0,"Training Dummy",combatStyle.NORMAL);
		
		//random encounters
		Enemy monster = new Enemy(5,1,50,"Monster",combatStyle.NORMAL);
		Enemy rat = new Enemy(2,9999,50,"Rat",combatStyle.PEACEFUL);
		Enemy ghostMinor = new Enemy(12,2,50,"Minor Ghost",combatStyle.NORMAL);
		Enemy ghostMajor = new Enemy(20,3,50,"Major Ghost",combatStyle.NORMAL);
		Enemy powerfulGhost = new Enemy(40,4,50,"Powerful Ghost",combatStyle.NORMAL);

		//bosses
		Enemy boss1 = new Enemy(10,1,500,"Spirit of the Shower",combatStyle.BOSS);
		Enemy boss2 = new Enemy(30,2,500,"Spirit of the Closet",combatStyle.BOSS);
		Enemy boss3 = new Enemy(50,4,500,"Spirit of the Bedroom",combatStyle.BOSS);
		Enemy boss4 = new Enemy(80,6,1,"Spirit of the House",combatStyle.BOSS);
		Enemy theGame = new Enemy(214,7,483,"Kr99KXwcJPgOIDRXg8qP",combatStyle.BOSS);

		//for the infinate hallway, made to mirror the player
		Enemy hallGhost;
		int numHalls=0;

		//used for combat
		int temp=0;
		int monsterChance = 0;
		
		//if you die
		boolean dead = false;
		//if you win the game
		boolean done = false;

		//for toilet ending
		boolean fart = false;
		int farted = 0;

		//for main story progress, and secret progress
		int questProg = 0;
		int secretProg = 0;

		//for player location on house 2d array
		int x = 2;
		int y = 3;

		//for rand num generation
		int r = 0;

		//place is where player is for switch case
		//house is the array with the house setup there is a comment bellow for better viewing
		String place;
		String[][] house = {{"error","bath","bathHall","leftRight","bedHall"},{"error","error","upDown","error","bed"},{"closHall","leftRight","allWay","leftRight","endHall"},{"clos","error","door","error","end"}};
		/* d is bed
		 |0  1  2  3  4  (x)
		0|e |b |bh|lr|dh
		1|e |e |ud|e |d
		2|ch|lr|aw|lr|eh
		3|c |e |d |e |en
		(y)
		*/

		/*
		//example of a combat setup

		temp = game.combat(player,ghostMinor);
		if(temp > 0){
			player.addExp(temp);
		} else if(temp == 0){
		} else if(temp == -1){
			dead = true;
			break;
		}
		*/

		
		//pregame screens
		while(true){
			playerInput=game.move("Welcome to the Haunted Haunsion!\nThe Game at which you walk around a house and kill ghost to free the house from sed ghosts!\n","1. Start the Game\n2. Tutorial",2);
			if(playerInput==3209){
				System.out.println("Going to infinate hallway.\n");
				done=true;
				break;
			}else if(playerInput==1){
				break;
			}else if(playerInput==2){
				while(true){
					playerInput=game.move("Welcome to the tutorial!\nWhat do you wish to learn about!\n","1. Combat\n2. House Layout\n3. Back to Menu",3);
					if(playerInput==1){
						playerInput=game.move("Combat you have three options (unless if it's a boss where you can't retreat), attack, defend/heal, retreat.\n\nAttacking uses your current attack against the enemy, and has a 10% chance to fail.\nDefending nullifies all enemy attack and allows you to heal one half of your current attack plus one, and has a 30% chance to fail.\nRetreating gives you a 80% chance to get out of combat (not available for bosses).\n\nThe Enemy always does its turn after the player, and can attack or retreat, everything else is the same, except that an enemy has an 80% chance to fail a retreat.\n\nThe Best strategy is to heal whenever you can.\n","1. Try combat against a training dummy\n2. Back to Tutorial",2);
						if(playerInput==1){
							temp = game.combat(player,trainingDummy);
						}
					}else if(playerInput==2){
						System.out.println(" |0  1  2  3  4  (x)\n0|e |b |bh|lr|dh\n1|e |e |ud|e |d\n2|ch|lr|aw|lr|eh\n3|c |e |do|e |en\n(y)\n\ne is for error or for non existant room,\nb is for bathroom,\nbh is for bathroom hallway,\nlr is for hall that goes left or right,\ndh is for bedroom hall,\nud is for hall that goes up or down,\nd is for bedroom,\nch is for closet hall,\naw is for a hall that goes all ways,\neh is for ending room hall,\nc is for closet,\ndo is for the door,\nen is for the ending room.");
					}else{
						break;
					}
				}
			}else{
				System.out.println("Sorry enter again!\n\n");
				playerInput=inputTracker.nextInt();
			}
		}


		
		while(!done&&!dead){//start of game-loop

			//rand encounter thing
			/*
			*/
			r = ((int)(Math.random()*100)+1);
			if(r<=33){//start rand encounter
				System.out.println("Random Encounter!\n");
				if(r<=5){
					temp = game.combat(player,rat);
					if(temp > 0){
						player.addExp(temp);
					} else if(temp == 0){
					} else if(temp == -1){
						dead = true;
						break;
					}
				}else{
					//start level scale section
					r=(int)(Math.random()*3);
					monsterChance = player.getLevel()-1+r;
					if(monsterChance<=2){
						temp = game.combat(player,monster);
						if(temp > 0){
							player.addExp(temp);
						} else if(temp == 0){
						} else if(temp == -1){
							dead = true;
							break;
						}
					}else if(monsterChance<=6){
						temp = game.combat(player,ghostMinor);
						if(temp > 0){
							player.addExp(temp);
						} else if(temp == 0){
						} else if(temp == -1){
							dead = true;
							break;
						}
					}else if(monsterChance<=10){
						temp = game.combat(player,ghostMajor);
						if(temp > 0){
							player.addExp(temp);
						} else if(temp == 0){
						} else if(temp == -1){
							dead = true;
							break;
						}
					}else if(monsterChance>=20){
						temp = game.combat(player,powerfulGhost);
						if(temp > 0){
							player.addExp(temp);
						} else if(temp == 0){
						} else if(temp == -1){
							dead = true;
							break;
						}
					}
					//end level scale section
				}
			} //end rand encounter
			/* d is bed
		 |0  1  2  3  4
		0|e |b |bh|lr|dh
		1|e |e |ud|e |d
		2|ch|lr|aw|lr|eh
		3|c |e |d |e |en
			*/

			//to seperate combat and/or locations to look better
			System.out.println("\n\n\n");

			//game map after each move
			System.out.println("---------------");
			for(int yy=0; yy<4; yy++){
				for(int xx=0; xx<5; xx++){
					if(!(house[yy][xx].equals("error"))&&(xx!=x||yy!=y)){
						System.out.print("| |");
					}else if(x==xx&&y==yy){
						System.out.print("|#|");
					}else{
						System.out.print("   ");
					}
				}
				System.out.println("\n---------------");
			}

			System.out.println("\n\n");
			
			//house movement mechanism
			place=house[y][x];
			switch(place){
				case("door"):
					playerInput = game.move("You arrive at the door, you know you cannot leave this place before you are done","1. Move Up",6);
					while(true){
						if(playerInput==6&&secretProg==2){
							secretProg++;
							System.out.println("You go to the door at which you punch it and your hurt your hand owie owie, a paper falls out of door and you look at it and you see, \".. --. -. --- .-. . / .... .. -- --..-- / . -. - . .-. / --... / --- -. -.-. . / -.-- --- ..- / . -. - . .-. / - .... . / .-. --- --- -- / --- ..-. / -.. .- .-. -.- / . -. . .-. --. -.-- / .- -. -.. / .-- . / -- .- -.-- / -- . . - .-.-.-\"\n(Wow I don't know what this says, and you don't too!!! This is a fairly useless kind of to rabbit hole to go down, don't you agree?)");
						}else if(playerInput==1){
							y--;
							break;
						}else{
							System.out.println("Sorry enter again!\n\n");
							playerInput=inputTracker.nextInt();
						}
					}
					break;




					
				//closet
				case("clos"):
					while(!dead){
						//if not defeated boss yet
						if(questProg==2){
							playerInput = game.move("Entering the closet you instantly see a ghost of immense stature, and if you want to go through the closet you must defeat it","1. Fight it\n2. Zoinks Scoop let's run!",2);
							if(playerInput==1){
								temp = game.combat(player,boss2);
								if(temp > 0){
									player.addExp(temp);
									questProg++;
								} else if(temp == 0){
								} else if(temp == -1){
									dead = true;
									break;
								}
							}else{
								break;
							}
						}
						
						//main closet code
						else{
							playerInput = game.move("The closet after you defeated it's ghost is fairly empty, however the ghost's body seems to be something to look at, there's also a bucket","1. Look at the ghost body\n2. Pick up the bucket\n3. Leave",3);
							if(playerInput==1){
								System.out.println("Examining the ghostly remains when you move your hand through them it has no resistence yet you feel resistence, a strange feeling indeed.");
							}else if(playerInput==2){
								playerInput = game.move("Picking up the bucket you feel a warmth coming from it","1. Hug the bucket\n2. Look inside the bucket\n3. Leave",4);
								if(playerInput==1){
									System.out.println("Hugging the bucket you feel very happy.\n+1 relationship with Bucket\n");
								}else if(playerInput==2){
									System.out.println("When you look inside the bucket, you see nothing, the bucket is just a bucket which was not used for anything.");
								}else if(playerInput==4&&secretProg==1){
									secretProg++;
									System.out.println("Somehow you open the bucket, in such a way that it defies all laws of reality, that a paper is formed. On the paper you see, \n\"\n VI\n door\n     \"\n(honestly this is kind of strange, I'd probably stop)");
								}
							}else{
								break;
							}
						}
					}
					y--;
					break;



					

				//bathroom
				case("bath"):
					while(!dead){
						playerInput = game.move("The Bathroom has a toilet, large shower, and medicine cabinent. The floor is tiled in pure marble with in between a black separator of ambiguous material","1. Examine the toilet\n2. Examine the shower\n3. Examine the medicine cabinent\n4. Leave",4);


						//examine toilet option
						if(playerInput==1){
							if(fart){
								farted+=1;
								if(farted >= 5){
									System.out.println("Fine you shove your face into the toilet, it drags you in further, and you drown, congrats you've, \"Examine the toilet\"...");
									dead = true;
									break;
								}
								System.out.println("You know that you already got what you want from the toilet, and you most obviously don't want to do it again.");
							}else{
								playerInput = game.move("You look into the toilet, you see something within it","1. Don't go for it\n2. TALLY HO LADS!",2);
								if(playerInput==1){
									System.out.println("You know better than to shove your hand in a 40 year old maybe more toilet.");
								}else{
									playerInput = game.move("With your mind set, you shove your hand into the toilet, you wiggle your hand around which is hard due to you encountering some form of resistence. You finally grab onto something and pull it out. You glance at what you just pulled out, a bottle","1. Put it back in\n2. Pop it open!",2);
									if(playerInput==1){
										System.out.println("You reason that a bottle in a haunted mansion inside a toilet is most probably not a good thing to open.");
									}else{
										fart = true;
										System.out.println("Opening the bottle you get a stench of years old human waste, and then you look into it seeing there is liquid gold. FORTUNE FAVORS THE BOLD! (Don't do that again...)");
										player.addGold(50);
									}
								}
							}
						}
						
						
						//examine shower option
						else if(playerInput==2){
							if(questProg==0){
								playerInput=game.move("As you open the shower curtain you see a ghost eating some pizza","1. Attack the Ghost!\n2. Leave the ghost alone...",2);
								if(playerInput==1){
									temp = game.combat(player,boss1);
									if(temp > 0){
										player.addExp(temp);
										questProg++;
									} else if(temp == 0){
									} else if(temp == -1){
										dead = true;
										break;
									}
								}else{
									System.out.println("You slowly close the shower curtain hoping the ghost won't notice you.");
								}
							}else{
								System.out.println("When you open the shower you look at the remnants of the ghost, you most likely don't want to touch it due to it smelling terrible.");
							}
						}
						
						
						//examine medicine cabinent option
						else if(playerInput==3){
							if(!player.hasItem("Pills")){
								playerInput=game.move("The outside of the medicine cabinet has one side with a broken mirror, and the other side with a cracked mirror. Opening the cabinet you see some old pill bottles one with the pills still inside it, there is also a small picture inside","1. Look at the picture\n2. Take the pills\n3. Leave",3);
							}else{
								playerInput=game.move("The outside of the medicine cabinet has one side with a broken mirror, and the other side with a cracked mirror. Opening the cabinet you see some old pill bottles one with the pills still inside it, there is also a small picture inside","1. Look at the picture\n2. Leave",2);
							}
							if(playerInput==1){
								System.out.println("Looking at the picture closer, it for some reason has no dust on it like the other items inside this bathroom. The picture seems to be of a advertisement selling glass beads. Turning it around you see the number 3 under a crude drawning of a matress.");
							}else if(playerInput==2&&!player.hasItem("Pills")){
								player.addItem("Pills",25);
							}
						}

							
						//leave option
						else{
							x++;
							break;
						}
					}
					break;
				//end bathroom




					
					
				//bed
				case("bed"):
					while(!dead){
						playerInput=game.move("Entering the bedroom you see a dresser, bed, and a bedside table","1. Examine the bed\n2. Examine the dresser\n3. Examine the bedside table\n4. Leave",4);

						
						//examine bed option
						if(playerInput==1){
							playerInput=game.move("Looking at the bed you see it's most probably king sized, and has fancy wood engravings on it","1. Take off the sheets\n2. Look under the bed\n3. Leave",3);
							if(playerInput==1){
								System.out.println("Taking off the sheets you see a note, at which you read without picking it up, \"I apologize for being so sudden but your car warranty has expired, and you are not going to be able to get your money back from that fatal accident.\", you place the note back down.");
							}else if(playerInput==2){
								playerInput=game.move("Looking under the bed you see little, there's a small knife and that's about it","1. Look at the knife\n2. Leave",3);
								if(playerInput==1){
									System.out.println("Looking at the knife there is little to say, it seems like a normal and average butter knife.");
								}else if(playerInput==3&&secretProg==0){
									secretProg=1;
									System.out.println("You somehow find a pressable tile under the matress at which you somehow get a scroll. Unwrapping it you see a crude drawning of a 4 inside a bucket.");
								}
							}
						}

							
						//examine dresser option
						else if(playerInput==2){
							playerInput=game.move("The dresser has little on top of it except a broken vase and very dead flowers","1. Open the drawers\n2. Look behind the dresser\n3. Leave",3);
							if(playerInput==1){
								//check if fought boss or not
								if(questProg==4){
									System.out.println("Opening the drawers you are attacked by a powerful dark force!");
									temp = game.combat(player,boss3);
									if(temp > 0){
										player.addExp(temp);
										questProg++;
									} else if(temp == 0){
									} else if(temp == -1){
										dead = true;
										break;
									}
								}
								//normal open drawers
								else{
									playerInput=game.move("Unlike the other ghosts the Spirit of the Bedroom has disapated, allowing you to inspect the drawers","1. See what's inside them\n2. Leave",2);
									if(playerInput==1){
										if(!player.hasItem("Sock")){
											playerInput=game.move("Looking inside the dressers you see lots of teared up clothing, and the only drawer that seems untouch is the one with socks","1. Take a sock\n2. Leave",2);
											if(playerInput==1){
												player.addItem("Sock",50);
											}
										}else{
											System.out.println("Looking inside the dressers you see lots of teared up clothing, and the only drawer that seems untouch is the one with socks, you also see the missing spot from where you took a sock.");
										}
									}
								}
							}else if(playerInput==2){
								System.out.println("Looking behind the dresser you see lots of dust but nothing else.");
							}
						}

							
						//examine table option
						else if(playerInput==3){
							playerInput=game.move("Looking at the bedside table you see a picture and lamp","1. Observe the lamp\n2. Observe the picture\n3. Leave",3);
							if(playerInput==1){
								System.out.println("The lamp is a old oil based lamp, and the mechanism to light it seems to have failed, on top of the lighter fluid seeming to have leaked all over the table.");
							}else if(playerInput==2){
								playerInput=game.move("The picture seems to be of a couple holding hands, it seems to be partially burned which most probably due to the nearby lamp","1. Look at the frame\n2. Look at the picture further\n3. Leave",3);
								if(playerInput==1){
									System.out.println("Looking at the frame it seems to have expert craftmenship, which is overshadowed by the cheap gold paint.");
								}else if(playerInput==2){
									System.out.println("Looking at the picture closer you see nothing, so you turn it around and see the writing, \"5 days before we bought this house\".");
								}
							}
						}

						
						//leave option
						else{
							y--;
							break;
						}
					}
					break;




					


					
				case("end"):
					playerInput = game.move("You enter the room, almost instantly","1. Defeat the foul beast",7);
					while(!done){
						if(playerInput==1){
							//end main story game boss
							System.out.println("You charge at the spirit in order to end the reign of terror.");
							temp = game.combat(player,boss4);
							if(temp > 0){
								player.addExp(temp);
								done = true;
								System.out.println("As you stab the evil spectre for the last time you feel happy, as you know you have saved the home from the evil spirits!\n\nYOU WIN!!!\n");
								break;
							} else if(temp == 0){
							} else if(temp == -1){
								dead = true;
								break;
							}
						}else if(playerInput==7&&secretProg==3){
							playerInput=game.move("Hello again, all you know of me is my orders upon you, but it is the time for you to complete the series of events, you must defeat the game.\nNow answer the question","Press the 1 key.",1);
							temp = game.combat(player,theGame);
							if(temp > 0){
								player.addExp(temp);
								done = true;
								System.out.println("Thank you very much for following this path until completion, with the game dead there won't have to be a bad plot with no beginning or end, there can be peace, and that infinate hallway.");
								break;
							} else if(temp == 0){
							} else if(temp == -1){
								dead = true;
								break;
							}
						}else{
							System.out.println("Sorry enter again!\n\n");
							playerInput=inputTracker.nextInt();
						}
					}
					break;


					




					
				case("closHall"):
					playerInput = game.move("You are at the doorway that goes to the closet","1. Move right\n2. Enter the closet",2);
					if(playerInput==1){
						x++;
					}else if(questProg>=2){
						y++;
					}else if(questProg==1){
						System.out.println("When opening the door you feel like defeating the Spirit of the Shower has made the dark forces of this house weaken, as you open the door.");
						questProg++;
						y++;
					}else{
						System.out.println("Trying to open the closet it feels as if a unatural force is preventing you from entering, so you decide to leave.");
						x++;
					}
					break;




					


					
				case("bathHall"):
					playerInput = game.move("You are at the doorway that goes to the bathroom","1. Enter the bathroom\n2. Move right\n3. Move down",3);
					if(playerInput==1){
						x--;
					}else if(playerInput==2){
						x++;
					}else{
						y++;
					}
					break;





					

					
				case("bedHall"):
					playerInput = game.move("You are at the doorway that goes to the bedroom","1. Move left\n2. Enter the bedroom",2);
					if(playerInput==1){
						x--;
					}else if(questProg>=4){
						y++;
					}else if(questProg==3){
						System.out.println("When opening the door you feel like defeating the ghost of the closet has made the dark forces of this house weaken, as you open the door.");
						questProg++;
						y++;
					}else{
						System.out.println("Trying to open the bedroom door it feels as if a unatural force is preventing you from entering, so you decide to leave.");
						x--;
					}
					break;






					
					
				case("endHall"):
					playerInput = game.move("You are at a doorway that has a dark energy to it","1. Move left\n2. Try to enter it",2);
					if(playerInput==1){
						x--;
					}else{
						if(questProg==5){
							y++;
						}else{
							System.out.println("Before you even go to the door you're overfilled with a feeling of dark energy, which you sense to be there due to the dead spirits within this house, so you know you must defeat the stong spirits of this home befoe you can go through this door.");
						}
					}
					break;



					


					
					
				case("allWay"):
					playerInput = game.move("You are in a hallway, you can move Left, Right, Up or Down","1. Move left\n2. Move right\n3. Move up\n4. Move down",4);
					if(playerInput==1){
						x--;
					}else if(playerInput==2){
						x++;
					}else if(playerInput==3){
						y--;
					}else{
						y++;
					}
					break;



					




					
				case("leftRight"):
					playerInput = game.move("You are in a hallway, you can move Left or Right","1. Move left\n2. Move right",2);
					if(playerInput==1){
						x--;
					} else {
						x++;
					}
					break;






					
					
				case("upDown"):
					playerInput = game.move("You are in a hallway, you can move Up or Down","1. Move up\n2. Move down",2);
					if(playerInput==1){
						y--;
					} else {
						y++;
					}
					break;




					

					
					
				case("error"):
					System.out.println("You got into a oob position : x"+x+" y"+y+"\nReseting to door");
					x = 2;
					y = 3;
					break;




					


					
				default:
					System.out.println("Switch case broke");
					done = true;
					break;
			}
		}//end of game loop





		
		//inf hallway code
		if(done){
			System.out.println("Due to you finishing the game you get to go into the infinate hallway which makes you battle infinate clones of your player character.\n\nEnter anything to conitnue.\n(Enter 3209 inside the title location to get back here)");
			inputTracker.next();
		}
		while(!dead){
			//combat part inf hallway
			hallGhost=new Enemy(player.getHealth(),player.getAttack(),100,"Yourself",combatStyle.BOSS);
			temp = game.combat(player,hallGhost);
			if(temp > 0){
				player.addExp(temp);
			} else if(temp == 0){
			} else if(temp == -1){
				dead = true;
				System.out.println("You have defeated "+numHalls+" of player clones.");
				break;
			}
			numHalls++;

			//hall part
			playerInput=game.move("You are in a hallway that seems to extend into infinity, and have defeated "+numHalls+" of ghosts that seem to take your form","1. Continue on.",1);
		}
		if(dead||done){
			inputTracker.close();
		}
    }

	
}

