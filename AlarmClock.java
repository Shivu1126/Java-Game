package audio;

import java.io.File;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class AlarmClock {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Alarm alarm = new Alarm();
		alarm.init();

	}
}
class Alarm
{
	private Scanner scanner = new Scanner(System.in);
	protected void init()
	{
//		playAudio();
		System.out.println("Enter set time...");
		String setTime = scanner.nextLine();
		Pattern pattern = Pattern.compile("([01]?[0-9]|2[0-3]):[0-5][0-9]:([0-5]?[0-9])$");
		Matcher matcher = pattern.matcher(setTime);
		if(! matcher.matches())
		{
			System.out.println("Invalid time");
			return;
		}
		long millis = timeToMillis(setTime);
//		System.out.println("millis"+millis);
		DateFormat formatter = new SimpleDateFormat("HH:mm:ss");
		String time = formatter.format(new Date());
//		System.out.println(time);
		long currTime = timeToMillis(time);
//		System.out.println("currtime"+currTime);
		if(currTime>millis)
			millis += 24*60*60*1000L;
//		System.out.println(millis);
		while(true)
		{
			if(currTime>=millis)
			{
				System.out.println("Play....");
				playAudio();
				break;
			}
			time = formatter.format(new Date());
			currTime = timeToMillis(time);
		}	
	}
	private long timeToMillis(String time)
	{
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss"); 
		Date date = null;
		try {
			date = sdf.parse(time);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		long millis = date.getTime();
		return millis;
	}
	boolean playCompleted;
	private void playAudio()
	{
		try {
			File file = new File("C:\\Users\\saran\\Music\\Hope-Xxxtentacion.wav");
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(file.getAbsoluteFile());
			Clip clip = AudioSystem.getClip();
			clip.open(audioInputStream);
			clip.start();
			clip.loop(0);
			System.out.println("If you want to stop..press '1'");
			if(scanner.next().charAt(0)=='1')
				clip.stop();
			
			clip.close();
		} catch(Exception ex) {
			System.out.println("Error with playing sound.");
			ex.printStackTrace();
		}

	}
}