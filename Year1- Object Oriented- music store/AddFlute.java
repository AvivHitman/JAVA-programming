// aviv tfilin 313459869

import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

public class AddFlute extends AddSaxophone{
	private Label label1 = new Label("Material");
	private Label label2 = new Label("Flute Type:");
	private final String types[] = new String[] { "Flute" , "Recorder", "Bass"};
	private final String material[] = new String[] { "Wood" , "Metal", "Plastic"};
	private ComboBox<String> comboBoxMaterial;
	private ComboBox<String> comboBoxType;
	
	public AddFlute() {
		super();	
		
		comboBoxMaterial = new ComboBox<>();
		comboBoxMaterial.getItems().addAll(material);
		comboBoxMaterial.setPromptText(label1.getText());
			
		comboBoxType = new ComboBox<>();
		comboBoxType.getItems().addAll(types);
		comboBoxType.setPromptText("Type");
	    	    
	    add(label1, 0, 2);
	    add(comboBoxMaterial, 1, 2);
	    add(label2, 0, 3); 
	    add(comboBoxType, 1, 3);
	    	
	}

	public ComboBox<String> getComboBoxMaterial() {
		return comboBoxMaterial;
	}

	public void setComboBoxMaterial(ComboBox<String> comboBoxMaterial) {
		this.comboBoxMaterial = comboBoxMaterial;
	}

	public ComboBox<String> getComboBoxType() {
		return comboBoxType;
	}

	public void setComboBoxType(ComboBox<String> comboBoxType) {
		this.comboBoxType = comboBoxType;
	}
		
}
