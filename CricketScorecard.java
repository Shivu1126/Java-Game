package Cricket_Scorecard;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class CricketScorecard {
	static Scanner s=new Scanner(System.in);
	static Random random = new Random();
	static int totalFirstBatting=0;
	static final int OVER=5;
	static ArrayList<playerScores> scoreDetailsByplayerBatFirst = new ArrayList<>();
	static ArrayList<playerScores> scoreDetailsByplayerBat2nd = new ArrayList<>();

	public static void main(String[] args) {

		System.out.println("Enter team1 name ");
		String team1=s.next();
		System.out.println("Enter team2 name ");
		String team2=s.next();
		System.out.println("Lets start the match...");
		System.out.println("-----"+team1+" vs "+team2+"-----");
		System.out.println(team1+" captain toss the coin..");
		System.out.println(team2+" captain...1.head or 2.tail..");
		String toss=s.next();
		String tossWin[]= {"head","tail"};
		int index = random.nextInt(tossWin.length);
		String tossWinningTeam="";
		String tossLoseTeam="";

		if(tossWin[index].equals(toss))
		{
			System.out.println(team2+" captain win the toss..");
			tossWinningTeam=team2;
			tossLoseTeam=team1;
		}
		else
		{
			System.out.println(team1+" captain win the toss..");
			tossWinningTeam=team1;
			tossLoseTeam=team2;

		}
		System.out.println("Choose 1.bat first or 2.bowl first");
		int chooseBatFirst=s.nextInt();
		String batFirst="";
		String bat2nd="";
		if(chooseBatFirst==1)
		{
			System.out.println(tossWinningTeam+" goes to bat first..");
			System.out.println(tossLoseTeam+" goes to bowl first...");
			batFirst=tossWinningTeam;
			bat2nd=tossLoseTeam;
		}
		else
		{
			System.out.println(tossWinningTeam+" goes to bowl first..");
			System.out.println(tossLoseTeam+" goes to bat first...");
			batFirst=tossLoseTeam;
			bat2nd=tossWinningTeam;
		}
		addPlayersName(batFirst,1);
		addPlayersName(bat2nd,2);
		displayTeamDetails(batFirst, bat2nd);

		totalFirstBatting = Batting(batFirst, scoreDetailsByplayerBatFirst,1);
		System.out.println("Target is.."+(totalFirstBatting+1));
		int totalSecondBatting=Batting(bat2nd, scoreDetailsByplayerBat2nd,2);
		if(totalFirstBatting>totalSecondBatting)
		{
			System.out.println(batFirst+" team win the match by "+(totalFirstBatting-totalSecondBatting)+" runs.");
		}
		if(totalSecondBatting==totalFirstBatting)
		{
			System.out.println("...MATCH IS DRAW...");
		}

	}

	static void addPlayersName(String teamName,int who)
	{
		System.out.println("Enter "+teamName+" Team XI....");
		for(int i=0;i<11;i++)
		{
			System.out.print((i+1)+". ");
			String name = s.next();
			if(who==1)
				scoreDetailsByplayerBatFirst.add(new playerScores(name, 0, 0));
			else
				scoreDetailsByplayerBat2nd.add(new playerScores(name, 0, 0));
		}
	}

	static void displayTeamDetails(String Bat1st,String Bat2nd)
	{
		System.out.println(Bat1st+"\tvs\t"+Bat2nd);

		for(int i=0;i<11;i++)
		{
			System.out.println(scoreDetailsByplayerBatFirst.get(i).playerName+"\t\t\t"+scoreDetailsByplayerBat2nd.get(i).playerName);
		}
	}

	static int Batting(String battingTeam,ArrayList<playerScores> teamPlayers,int who)
	{
		int i=1;
		int wickets=0;
		int teamScore=0;
		System.out.println(battingTeam+" Start the batting...");
		System.out.println(teamPlayers.get(0).playerName+" is Striker");
		System.out.println(teamPlayers.get(i).playerName+" is Non Striker");
		int totalBalls=0;
		int extras=0;

		playerScores striker=teamPlayers.get(0);
		playerScores Nonstriker=teamPlayers.get(i);
		while(OVER*6>totalBalls && wickets<10)
		{
			int runsDetail[]= {-2,-1,0,1,2,3,4,6};
			int index = random.nextInt(runsDetail.length);
			int run=runsDetail[index];
			if(run==-1)
			{
				System.out.println(striker.playerName+" is out...");
				striker.balls++;
				wickets++;
				i++;
				striker=teamPlayers.get(i);
				System.out.println("New Striker is "+striker.playerName);
			}
			else if(run==-2)
			{
				System.out.println("Wide ball(EXTRA)");
				teamScore+=1;
				extras++;
				totalBalls--;
				if(who==2 && checkWinOrLose(teamScore))
				{
					System.out.println(battingTeam+" is Winning the match...before "+((OVER*6)-totalBalls)+" Balls");
					displayScoreBoard(teamPlayers, battingTeam, run, wickets, extras);
					break;
				}
			}
			else
			{
				System.out.println(striker.playerName+" take "+run+" run ");
				teamScore+=run;
				if(run%2==1)
				{
					striker.runs+=run;
					striker.balls++;
					playerScores newStriker=striker;
					striker=Nonstriker;
					Nonstriker=newStriker;				
				}
				else
				{
					striker.balls++;
					striker.runs+=run;
				}
				if(who==2 && checkWinOrLose(teamScore))
				{
					System.out.println(battingTeam+" is Winning the match...before "+((OVER*6)-totalBalls+1)+" Balls");					
					displayScoreBoard(teamPlayers, battingTeam, teamScore, wickets, extras);
					break;
				}
			}
			totalBalls++;
			if(totalBalls%6==0)
			{

				playerScores changeStriker=striker;
				striker=Nonstriker;
				Nonstriker=changeStriker;
				System.out.println("__- "+(totalBalls/6)+" OVER COMPLETE -__");
				System.out.println("Wickets"+wickets);
				displayScoreBoard(teamPlayers, battingTeam, teamScore, wickets, extras);
			}

		}

		striker.playerName=striker.playerName+"*";
		Nonstriker.playerName=Nonstriker.playerName+"*";
		if(who==1)
			System.out.println("First Innings Over...");
		else
			System.out.println("Second Innings Over...");
		displayScoreBoard(teamPlayers, battingTeam, teamScore, wickets, extras);

		return teamScore;
	}
	private static boolean checkWinOrLose(int teamScore)
	{
		return teamScore>totalFirstBatting;
	}

	private	static void displayScoreBoard(List<playerScores> scoreOfPlayers,String teamName,int totalScore,int Wickets,int Extras)
	{
		System.out.println("------------------------------------");
		System.out.println("Team Name : "+teamName);
		System.out.println("------------------------------------");
		System.out.println("playerName\t Runs \t Balls");
		System.out.println("------------------------------------");
		for(int i=0;i<11;i++)
		{
			playerScores player = scoreOfPlayers.get(i);
			if(player.balls!=0 || player.playerName.charAt(player.playerName.length()-1)=='*')
			{
				System.out.println(player.playerName+"\t\t"+player.runs+"\t"+player.balls);
			}
		}
		System.out.println("------------------------------------");
		System.out.println("Total score : "+totalScore+" Extras : "+Extras+" WICKETS : "+Wickets);
		System.out.println("------------------------------------");
	}

}
class playerScores
{
	String playerName;
	int runs;
	int balls;
	public playerScores(String playerName,int runs,int balls) 
	{
		this.playerName=playerName;
		this.runs=runs;
		this.balls=balls;
	}
}