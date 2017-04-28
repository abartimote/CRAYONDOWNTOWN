package crayondowntown.navalassault;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

 
public class grid extends Application {
	
	
    public static void main(String[] args) {
        launch(args);
    }
    
    //Creates an 2D array of Buttons to use in the grid
    private static Button[][] playerBoard = new Button[10][10];
    private static Button[][] computerBoard = new Button[10][10];
    Boolean soundOn = false;
    
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Grid Demo");
        
        //Creates a new root node
        Group root = new Group();
                 
        /*Creates an event handler that will play the "Miss" sound 
         * (I have put this on all buttons on the computerGrid)*/
        EventHandler miss = new EventHandler<ActionEvent>() { 
            @Override
            public void handle(ActionEvent event) {
            	Media miss = new Media("file:///Users/Andrew/git/CRAYONDOWNTOWN/crayondowntown/src/Miss.mp3");
                MediaPlayer playMiss = new MediaPlayer(miss);
                playMiss.play();
            }
        };
        
        /*Creates an event handler that will play the "Hit" sound 
        (I have put this on all buttons on the playerGrid)*/
        EventHandler hit = new EventHandler<ActionEvent>() { 
            @Override
            public void handle(ActionEvent event) {
            	Media hit = new Media("file:///Users/Andrew/git/CRAYONDOWNTOWN/crayondowntown/src/Hit.mp3");
                MediaPlayer playHit = new MediaPlayer(hit);
                playHit.play();
            }
        };
        
        /* Creates array of buttons for player board and sets them out in a 10 x 10 grid
         on the scene*/
        for (int y=0; y<playerBoard.length; y++) {
        	for (int x=0; x<playerBoard.length; x++) {
        		playerBoard[x][y] = new Button();
        		playerBoard[x][y].setPrefSize(40,40);
        		playerBoard[x][y].setLayoutX(x * 40 + 150);
        		playerBoard[x][y].setLayoutY(y * 40 + 150);
        		playerBoard[x][y].setOnAction(hit);
        		root.getChildren().add(playerBoard[x][y]);
        	}
        }  
        
        /* Creates array of buttons for computer board and sets them out in a 10 x 10 grid
        on the scene*/
        for (int y=0; y<computerBoard.length; y++) {
        	for (int x=0; x<computerBoard.length; x++) {
        		computerBoard[x][y] = new Button();
        		computerBoard[x][y].setPrefSize(40,40);
        		computerBoard[x][y].setLayoutX(x * 40 + 650);
        		computerBoard[x][y].setLayoutY(y * 40 + 150);
        		computerBoard[x][y].setOnAction(miss);
        		root.getChildren().add(computerBoard[x][y]);
        	}
        } 
         
        //Creates a music on/off button
        Button musicOn = new Button();
        musicOn.setPrefSize(200, 50);
        musicOn.setLayoutX(500);
        musicOn.setLayoutY(650);
        musicOn.setText("Turn Music On");
        root.getChildren().add(musicOn);
        
        //Creates a new media player to store background music
        Media music = new Media("file:///Users/Andrew/git/CRAYONDOWNTOWN/crayondowntown/src/Background_Music.mp3");
    	MediaPlayer playMusic = new MediaPlayer(music);
        
        //Creates event handler for music on/off button
        EventHandler sound = new EventHandler<ActionEvent>() { 
        	@Override
            public void handle(ActionEvent event) {
        	if (soundOn == false) {
        		System.out.println(soundOn);
        		System.out.println("Turn Music On");
        		playMusic.play();
        		musicOn.setText("Music Off");
        		soundOn = true;
        		System.out.println(soundOn);
        	}
        	else {
        		System.out.println(soundOn);
        		System.out.println("Turn Music Off");
        		playMusic.stop();
        		musicOn.setText("Music On");
        		soundOn = false;
        		System.out.println(soundOn);
        	}}     
        };
        
        //Links the on/off event handler to on/off button
        musicOn.setOnAction(sound);
        
        primaryStage.setScene(new Scene(root, 1200, 800));
        primaryStage.show();
    }
}