// aviv tfilin 313459869

import javafx.animation.Timeline;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.util.Duration;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.PathTransition;

public class BottomField extends VBox{
	private HBox hbox1 = new HBox(10);
	private Button button1 = new Button("add");
	private Button button2 = new Button("delete");
	private Button button3 = new Button("clear");
	
	private Line line = new Line();
	private PathTransition pt;	
	private Timeline clock = new Timeline();
	private DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	private Date date = new Date();
	private Text text = new Text();

	
	
	public BottomField() {
		getChildren().addAll(TopHBox(button1, button2, button3), Low() );
		setAlignment(Pos.CENTER);
		pt.play();
		text.setOnMouseEntered(e-> pt.pause());
		text.setOnMouseExited(e-> pt.play());
		
	}


	public HBox TopHBox (Button button1, Button button2, Button button3){
		hbox1.getChildren().addAll(button1, button2, button3);
		setPadding(new Insets(AfekaInstruments.SPACE));
		setSpacing(AfekaInstruments.SPACE);
		hbox1.setAlignment(Pos.CENTER);
		return hbox1;
		
	}
	
	public Text Low () {
		clock.getKeyFrames().add(new KeyFrame(Duration.seconds(1), e-> {
			text.setText(dateFormat.format(date) + " " + LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"))
					+ " afeka instrument music  store $$$ ON SALE!!!$$$ Guitars, Basses, Flutes , Saxophones and more!");
		}));
		clock.setCycleCount(Animation.INDEFINITE);
		clock.play();
		text.setFill(Color.RED);
		text.setFont(Font.font("Times New Roman" , FontWeight.BOLD , FontPosture.REGULAR , 16));
		text.setTextAlignment(TextAlignment.CENTER);
		line.setStartX(- AfekaInstruments.HEIGHT);
		line.setEndX(AfekaInstruments.WIDTH);
		pt = new PathTransition(Duration.seconds(10), line);	
		pt.setCycleCount(Animation.INDEFINITE);
		pt.setAutoReverse(true);
		pt.setNode(text);
			
		return text;
		
	}
	
	
	public Button getButton1() {
		return button1;
	}

	public void setButton1(Button button1) {
		this.button1 = button1;
	}

	public Button getButton2() {
		return button2;
	}

	public void setButton2(Button button2) {
		this.button2 = button2;
	}

	public Button getButton3() {
		return button3;
	}

	public void setButton3(Button button3) {
		this.button3 = button3;
	}

	
	
}
