package Snake_and_Ladders;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Snake_and_Ladders {
	private static final int SNAKES_COUNT = 8;
	private static final int LADDERS_COUNT = 8; 
	
	private static int snakes[][];
	private static int ladders[][];
	
	static ArrayList<PlayersDetails> players = new ArrayList<>();
	
	static int board[][]=new int[10][10];
		
	public static void main(String[] args) {
		
		Random random = new Random();
								// (players,position,rollDice_Count)	
		players.add(new PlayersDetails("p1",0));
		players.add(new PlayersDetails("p2",0));
		players.add(new PlayersDetails("p3",0));
		players.add(new PlayersDetails("p4",0));
		
		snakes=new int[SNAKES_COUNT][2];
		
		snakes[0][0]=17;
		snakes[0][1]=7;
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
		
		ladders[0][0]=4;
		ladders[0][1]=14;
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

		}
			
		String playerName="";
		int i=0;
		do
		{
			playerName=players.get(i).player;
			System.out.println(playerName+" roll the dice...");
			int rollDice = random.nextInt(1,7);
			System.out.println("Dice value = "+rollDice);
			int positionNow=players.get(i).position + rollDice;
			
			positionNow=checkSnakeAttack(snakes,positionNow,playerName);
			positionNow=checkLadderUp(ladders,positionNow,playerName);
			players.get(i).position=positionNow; 
			
			System.out.println("Position of "+ playerName+" = "+players.get(i).position);
			if(players.get(i).position>=100)
			{
				System.out.println(playerName+" is win...");
				viewBoard(players);
				break;
			}
			if(i==3)
			{
				viewBoard(players);
			}
			i++;
			if(i>players.size()-1)
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
					}
				}
				System.out.println();
			}
	}
	
	public static int checkSnakeAttack(int snakes[][],int positionOfPlayer,String playerName)
	{
		for(int i=0;i<SNAKES_COUNT;i++)
		{
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