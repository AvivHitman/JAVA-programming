// aviv tfilin 313459869

import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class AddGuitar extends AddSaxophone {
	private Label label1 = new Label("Number of Strings");
	private Label label2 = new Label("Guitar Type:");
	private TextField numOfStrings = new TextField();
	private final String types[] = new String[] { "Classic" , "Acoustic", "Electric"};
	private ComboBox<String> comboBox;
	
	
	public AddGuitar() {
		super();	    
	    comboBox = new ComboBox<>();
	    comboBox.getItems().addAll(types);
	    comboBox.setPromptText("Type");
	    
	    numOfStrings.setPromptText("EX: 6");
	    add(label1, 0, 2);
	    add(numOfStrings, 1, 2);
	    add(label2, 0, 3); 
	    add(comboBox, 1, 3);
	    	
	}


	public ComboBox<String> getComboBox() {
		return comboBox;
	}


	public void setComboBox(ComboBox<String> comboBox) {
		this.comboBox = comboBox;
	}


	public TextField getNumOfStrings() {
		return numOfStrings;
	}


	public void setNumOfStrings(TextField numOfStrings) {
		this.numOfStrings = numOfStrings;
	}


	public String[] getTypes() {
		return types;
	}
	
	
}
