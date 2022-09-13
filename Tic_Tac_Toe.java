package XOX_Tic_Tac_Toe;
import java.util.*;
public class Tic_Tac_Toe {
	static int countX=0;
	static int countO=0;
	public static void main(String[] args) {
		Scanner s=new Scanner(System.in);
		char arr[][]=new char[3][3];
		for(int i=0;i<3;i++)
		{
			for(int j=0;j<3;j++)
			{
				arr[i][j]='1';
			}
		}
		display(arr);
		//Players enter only 1 to 9
		while(true)
		{
			int position;
			System.out.println("Player 1 : Enter position ");
			position=s.nextInt();
			if(storeX_or_O(position,arr,"player_1"))
				continue;
			display(arr);
			countX++;
			if(countX>=3 && checkWin(arr))
			{
				System.out.println("Player 1(X) is Win...");	
				break;
			}
			
			if(countX==5 && countO==4)
			{
				System.out.println("Match is draw...Game Ended...");
				break;
			}
			
			boolean b=true;
			while(b)
			{
				System.out.println("Player 2 : Enter position ");
				position=s.nextInt();
				b=storeX_or_O(position,arr,"player_2");
			}
			display(arr);
			countO++;
			if(countO>=3 && checkWin(arr))
			{
				System.out.println("Player 2(O) is Win...");	
				break;
			}
			
		}
		s.close();
	}	
	public static boolean storeX_or_O(int pos,char arr[][],String player)
	{
		
		char symbol='X';
		if(player.equals("player_1"))
			symbol='X';
		else if(player.equals("player_2"))
			symbol='O';		
		
		switch(pos)
		{
		case 1:
			if(arr[0][0]=='1')
				arr[0][0]=symbol;			
			else
			{
				System.out.println("This position already fill...");
				return true;
			}
			break;
		case 2:
			if(arr[0][1]=='1')
				arr[0][1]=symbol;
			else
				{
					System.out.println("This position already fill...");
					return true;
				}			
			break;
		case 3:
			if(arr[0][2]=='1')
				arr[0][2]=symbol;
			else
				{
					System.out.println("This position already fill...");
					return true;
				}
			break;
		case 4:
			if(arr[1][0]=='1')
				arr[1][0]=symbol;
			else
				{
					System.out.println("This position already fill...");
					return true;
				}
			break;
		case 5:
			if(arr[1][1]=='1')
				arr[1][1]=symbol;
			else
				{
					System.out.println("This position already fill...");
					return true;
				}
			break;
		case 6:
			if(arr[1][2]=='1')
				arr[1][2]=symbol;
			else
				{
					System.out.println("This position already fill...");
					return true;
				}
			break;
		case 7:
			if(arr[2][0]=='1')
				arr[2][0]=symbol;
			else
				{
					System.out.println("This position already fill...");
					return true;
				}
			break;
		case 8:
			if(arr[2][1]=='1')
				arr[2][1]=symbol;
			else
				{
					System.out.println("This position already fill...");
					return true;
				}
			break;
		case 9:
			if(arr[2][2]=='1')
				arr[2][2]=symbol;
			else
				{
					System.out.println("This position already fill...");
					return true;
				}
			break;
		default:
			System.out.println("Enter correct position...");
			return true;						
		}
		return false;
	}
	
	
	public static boolean checkWin(char[][] arr)
	{
		int c=0;
		char RightCross=arr[0][0];
		if(arr[0][0]!='1')
		{
			for(int i=0;i<3;i++)
			{
				
				for(int j=0;j<3;j++)
				{
					if(i==j && arr[i][j]==RightCross)
						c++;					
				}
			}
			if(c==3)
				return true;
		}
		
		c=0;
		char LeftCross=arr[0][2];
		if(arr[0][2]!='1')
		{
			for(int i=0;i<3;i++)
			{
				for(int j=0;j<3;j++)
				{
					if(i+j==2 && arr[i][j]==LeftCross)
						c++;					
				}
							
			}
			if(c==3)
				return true;
		}
		
		
		for(int i=0;i<3;i++)
		{
			char StraightSide=arr[i][0];
			if(arr[i][0]!='1')
			{
				c=0;
				for(int j=0;j<3;j++)
				{
					if(arr[i][j]==StraightSide)
						c++;					
				}
				if(c==3)
					return true;
			}
		}
		
		
		for(int i=0;i<3;i++)
		{
			char StraightDown=arr[0][i];
			if(arr[0][i]!='1')
			{
				c=0;
				for(int j=0;j<3;j++)
				{
					if(arr[j][i]==StraightDown)
						c++;					
				}
				if(c==3)
					return true;
			}
		}		
		
		
		return false;
	}
	
	public static void display(char[][] arr)
	{
		for(int i=0;i<3;i++)
		{
			for(int j=0;j<3;j++)
			{
				System.out.print(arr[i][j]+" ");
			}
			System.out.println();
		}
	}
}
