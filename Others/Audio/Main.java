package Others.Audio;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import javax.sound.sampled.*;

public class Main {
    public static void main(String[] args) throws UnsupportedAudioFileException, IOException, LineUnavailableException{

        Scanner sc = new Scanner(System.in);

        File file = new File("D:\\Learning-CoreJava\\CoreJava\\Audio\\BlackBall.wav");

        AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);

        Clip clip = AudioSystem.getClip();
        clip.open(audioStream);

        String response = "";

        System.out.println("Blackball - SOS x  30KEY!");
        System.out.println("'P' = Play, 'S' = Stop, 'R' = Reset, 'Q' = Quit");
        System.out.println("Enter your response : ");

        while(!response.equals("Q")){

            response = sc.nextLine();
            response = response.toUpperCase();

            switch (response) {

                case ("P"):
                 clip.start();                    
                break;

                case ("S"):
                 clip.stop();
                break;

                case ("R"):
                 clip.setMicrosecondPosition(0);
                break;

                case ("Q"):
                 clip.close();
                break;

                default:
                    System.out.println("Enter a valid response");
                break;
            }
        }
        System.out.println("Thank you for playing the song!");
        sc.close();
    }
}