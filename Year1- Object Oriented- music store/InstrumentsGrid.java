// aviv tfilin 313459869
import java.util.ArrayList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class InstrumentsGrid extends GridPane {
	private Label label1 = new Label("Type:");
	private Label label2 = new Label("Brand:");
	private Label label3 = new Label("Price:");
	private TextField typeField = new TextField();
	private TextField brandField = new TextField();
	private TextField priceField = new TextField();
	private int index = 0;

	public InstrumentsGrid(ArrayList<MusicalInstrument> allInstruments) {
		setAlignment(Pos.CENTER);
		setPadding(new Insets(11.5, 12.5, 13.5, 14.5));
		setHgap(5.5);
		setVgap(5.5);

		setPromptText();

		add(label1, 0, 0);
		add(typeField, 1, 0);
		add(label2, 0, 1);
		add(brandField, 1, 1);
		add(label3, 0, 2);
		add(priceField, 1, 2);

		updateView(allInstruments, index);

	}

	public void setPromptText() {
		typeField.clear();
		brandField.clear();
		priceField.clear();

		typeField.setPromptText("No items");
		brandField.setPromptText("No items");
		priceField.setPromptText("No items");
	}

	public void updateView(ArrayList<MusicalInstrument> allInstruments, int index) {
		if (allInstruments.size() == 0) {
			setPromptText();
		}

		else {
			setTypeField(allInstruments.get(index).getClass().getName());
			setBrandField(allInstruments.get(index).getBrand());
			setPriceField(allInstruments.get(index).getPrice());
		}
	}

	public void delete(ArrayList<MusicalInstrument> allInstruments) {
		allInstruments.remove(getIndex());
		if (getIndex() == allInstruments.size()) {
			setIndex(0);

		}
	}

	public TextField getTypeField() {
		return typeField;
	}

	public void setTypeField(String s) {
		this.typeField.setText(s);
	}

	public TextField getBrandField() {
		return brandField;
	}

	public void setBrandField(String s) {
		this.brandField.setText(s);
		;
	}

	public TextField getPriceField() {
		return priceField;
	}

	public void setPriceField(Number price) {
		String s = price.toString();
		this.priceField.setText(s);
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

}
