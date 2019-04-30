// aviv tfilin 313459869
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class AddSaxophone extends GridPane{
	private Label label1 = new Label("Brand:");
	private Label label2 = new Label("Price:");
	private TextField brandField = new TextField();
	private TextField priceField = new TextField();
	
	public AddSaxophone() {
		setAlignment(Pos.CENTER);
		setPadding(new Insets(10, 10, 10, 10));
	    setHgap(10);
	    setVgap(10);
	    
	    brandField.setPromptText("EX: Gisbon");
	    priceField.setPromptText("EX: 7500");
		 add(label1, 0, 0);
		 add(brandField, 1, 0);
		 add(label2, 0, 1); 
		 add(priceField, 1, 1);
	}

	public TextField getBrandField() {
		return brandField;
	}

	public void setBrandField(TextField brandField) {
		this.brandField = brandField;
	}

	public TextField getPriceField() {
		return priceField;
	}

	public void setPriceField(TextField priceField) {
		this.priceField = priceField;
	}
}
