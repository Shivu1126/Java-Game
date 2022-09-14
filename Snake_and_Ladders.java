package Snake_and_Ladders;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Snake_and_Ladders {
	private static final int SNAKES_COUNT = 8;		//8 Snakes
	private static final int LADDERS_COUNT = 8; 	//8 Ladders
	
	private static int snakes[][];	//snakes position array
	private static int ladders[][];	//ladders position array
	
	static ArrayList<PlayersDetails> players = new ArrayList<>();	//players detail
		
	static int board[][]=new int[10][10];		//Board
		
	public static void main(String[] args) {
		
		Random random = new Random(); //For roll dice
				//players are Object(players,position)	
		players.add(new PlayersDetails("p1",0));
		players.add(new PlayersDetails("p2",0));
		players.add(new PlayersDetails("p3",0));
		players.add(new PlayersDetails("p4",0));
		
		snakes=new int[SNAKES_COUNT][2];	
		
		snakes[0][0]=17;	//Snakes head position at Board
		snakes[0][1]=7;		//Snakes tail position at Board
		snakes[1][0]=54;
		snakes[1][1]=34;
		snakes[2][0]=62;
		snakes[2][1]=19;
		snakes[3][0]=64;
		snakes[3][1]=60;
		snakes[4][0]=87;
		snakes[4][1]=24;
		snakes[5][0]=93;
		snakes[5][1]=73;
		snakes[6][0]=95;
		snakes[6][1]=75;
		snakes[7][0]=99;
		snakes[7][1]=78;
		
		ladders=new int[LADDERS_COUNT][2];
		
		ladders[0][0]=4;	//Ladder start position at Board
		ladders[0][1]=14;	//Ladder end position at Board
		ladders[1][0]=9;
		ladders[1][1]=31;
		ladders[2][0]=20;
		ladders[2][1]=38;
		ladders[3][0]=28;
		ladders[3][1]=84;
		ladders[4][0]=40;
		ladders[4][1]=69;
		ladders[5][0]=51;
		ladders[5][1]=67;
		ladders[6][0]=63;
		ladders[6][1]=81;
		ladders[7][0]=71;
		ladders[7][1]=91;
		
		
		//Board
		int end=100;
		for(int i=0;i<10;i++)
		{
			int j;
			for(j=0;j<10;j++)
			{
				board[i][j]=end;			
				if(i%2==0)		
					end--;		
				else
					end++;	
			}		
			if(i%2==0)
				end-=9;
			else
				end-=11;
			//'Even' (1-10, 21-30, ..., 81-90)  are printed left to right.
			//'Odd'  (11-20, 31-40, ..., 91-100)  are printed right to left.
			
		}
			
		String playerName="";
		int i=0;
		do
		{
			playerName=players.get(i).player;		//Get a player name using list get method
			System.out.println(playerName+" roll the dice...");	
			int rollDice = random.nextInt(1,7);		//Dice is give the 1-6 random value.So used random class.
			System.out.println("Dice value = "+rollDice);
			int positionNow=players.get(i).position + rollDice;	
			//Get a playing player position.
			
			positionNow=checkSnakeAttack(snakes,positionNow,playerName);
			//check position of the player same as snakes head position.
			
			positionNow=checkLadderUp(ladders,positionNow,playerName);
			//check position of the player same as ladders start position.
			
			players.get(i).position=positionNow; //replace the position by new position
			
			System.out.println("Position of "+ playerName+" = "+players.get(i).position);
			
			if(players.get(i).position>=100)	//which player cross the 100 first..that player is win
			{
				System.out.println(playerName+" is win...");
				viewBoard(players);			//display board and player position
				break;
			}
			if(i==3)	//if all players playing one shift then board is printing
			{
				viewBoard(players);		
			}
			i++;
			if(i>players.size()-1)	//if last player finish the one shift then start from 1st player.
				i=0;
				
		}while(true);
					
	}

	private static void viewBoard(List<PlayersDetails> players)
	{

			for(int i=0;i<10;i++)
			{
				for(int j=0;j<10;j++)
				{

					String p="";
					for(int loop=0;loop<players.size();loop++)
					{
						if(players.get(loop).position==board[i][j])		
						{
							//if position of player and board value position are same 
							//then add the player name in the temporary string.							
							p+=players.get(loop).player;
						}
					}
					if(p.equals(""))
					{
						try {
							Thread.sleep(10);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						System.out.print(board[i][j]+"\t");
					}
					else	 
					{
						try {
							Thread.sleep(10);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						System.err.print(board[i][j]+""+p+"\t");
						//if player name added..print the player name with board position
					}
				}
				System.out.println();
			}
	}
	
	public static int checkSnakeAttack(int snakes[][],int positionOfPlayer,String playerName)
	{
		for(int i=0;i<SNAKES_COUNT;i++)
		{
				//if snake head and position of the player are equal...
				//return the new position(snake tail) of the player and update this.
				if(snakes[i][0]==positionOfPlayer)
				{
					System.out.println(playerName+" attacked by position "+snakes[i][0]+" snake");
					System.out.println("So "+playerName+" DOWN to "+snakes[i][1]);
					return snakes[i][1];
				}
		}
		return positionOfPlayer;
	}
	
	public static int checkLadderUp(int ladders[][],int positionOfPlayer,String playerName)
	{
		for(int i=0;i<LADDERS_COUNT;i++)
		{
				//if ladder start and position of the player are equal...
				//return the new position(ladder end) of the player and update this.
				if(ladders[i][0]==positionOfPlayer)
				{
					System.out.println(playerName+" claimed the ladder by position "+ladders[i][0]+" ladder");
					System.out.println("So "+playerName+" UP to "+ladders[i][1]);
					return ladders[i][1];
				}
		}
		return positionOfPlayer;
	}
	
}
class PlayersDetails
{
	String player;		
	int position;
	public PlayersDetails(String player,int position) 
	{
		this.player=player;			
		this.position=position;
	}
}