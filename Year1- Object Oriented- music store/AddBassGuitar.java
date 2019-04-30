// aviv tfilin 313459869

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class AddBassGuitar extends AddSaxophone {
	private Label label1 = new Label("Number of Strings");
	private Label label2 = new Label("Fretless");
	private TextField numOfStrings = new TextField();
	private CheckBox checkFretless;
	
	
	public AddBassGuitar() {
		super();
		checkFretless = new CheckBox();
		setAlignment(Pos.CENTER);
		setPadding(new Insets(10, 10, 10, 10));
	    setHgap(10);
	    setVgap(10);
	        
	    numOfStrings.setPromptText("EX: 6");
	    
	    add(label1, 0, 2);
	    add(numOfStrings, 1, 2);
	    add(label2, 0, 3); 
	    add(checkFretless, 1, 3);
	}


	public TextField getNumOfStrings() {
		return numOfStrings;
	}


	public void setNumOfStrings(TextField numOfStrings) {
		this.numOfStrings = numOfStrings;
	}


	public CheckBox getCheckFretless() {
		return checkFretless;
	}


	public void setCheckFretless(CheckBox checkFretless) {
		this.checkFretless = checkFretless;
	}
}
