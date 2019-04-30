// aviv tfilin 313459869
import java.util.ArrayList;
import java.util.InputMismatchException;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AddWindow extends Stage {
	private final int SPACE = 20, HEIGHT = 300 , WIDTH = 400;
	private VBox layout = new VBox(SPACE);
	private VBox layout2 = new VBox(SPACE);
	private ComboBox<String> comboBox;
	private final String types[] = new String[] { "Guitar", "Bass", "Flute", "Saxophone" };
	private StackPane stackPane = new StackPane();
	private Button addButton = new Button("add");
	private AddGuitar addGuitar = new AddGuitar();
	private AddBassGuitar addBassGuitar = new AddBassGuitar();
	private AddSaxophone addSaxophone = new AddSaxophone();
	private AddFlute addFlute = new AddFlute();
	private InstrumentsGrid grid;
	private Insets paddindInserts = new Insets(SPACE, SPACE, SPACE, SPACE);

	public AddWindow(ArrayList<MusicalInstrument> allInstruments , ArrayList<MusicalInstrument> searchInstrument) {
		grid = new InstrumentsGrid(allInstruments);
		setTitle("add instrument");
		
		comboBox = new ComboBox<>();
		comboBox.getItems().addAll(types);
		comboBox.setPromptText("Choose Instrument Type Here");
		stackPane.getChildren().add(comboBox);

		layout.setPadding(paddindInserts);

		layout.getChildren().add(stackPane);
		layout.setAlignment(Pos.CENTER);

		comboBox.setOnAction(e -> instrumentToAdd());

		addButton.setOnAction(ev -> {
			addInstrument(allInstruments , searchInstrument);
			grid.updateView(allInstruments, 0);
			close();
		});

		Scene scene = new Scene(layout, WIDTH, HEIGHT);
		setScene(scene);
		setAlwaysOnTop(true);
		show();
	}

	public Button getAddButton() {
		return addButton;
	}

	public void setAddButton(Button addButton) {
		this.addButton = addButton;
	}

	public void instrumentToAdd() {
		layout.setAlignment(Pos.TOP_CENTER);

		switch (comboBox.getValue()) {
		case "Guitar":
			layout2.getChildren().clear();
			layout2.getChildren().add(addGuitar);
			
			break;

		case "Bass":
			layout2.getChildren().clear();
			layout2.getChildren().add(addBassGuitar);
			break;
			
		case "Saxophone":
			layout2.getChildren().clear();
			layout2.getChildren().add(addSaxophone);
			break;
			
		case "Flute":
			layout2.getChildren().clear();
			layout2.getChildren().add(addFlute);
			break;
			
		}
		layout.getChildren().clear();
		layout.getChildren().addAll(comboBox, layout2 , addButton);	
	}

	public void addInstrument(ArrayList<MusicalInstrument> allInstruments , ArrayList<MusicalInstrument> searchInstruments) {
		Alert alert = new Alert(Alert.AlertType.ERROR);

		try {
			switch (comboBox.getValue()) {
			case "Guitar":

				String brand = addGuitar.getBrandField().getText();
				double price = Double.parseDouble(addGuitar.getPriceField().getText());
				int numOfStrings = Integer.parseInt(addGuitar.getNumOfStrings().getText());
				String type = addGuitar.getComboBox().getValue();
				allInstruments.add(new Guitar(brand, price, numOfStrings, type));
				searchInstruments.addAll(allInstruments);
				break;

			case "Bass":
				String brand1 = addBassGuitar.getBrandField().getText();
				double price1 = Double.parseDouble(addBassGuitar.getPriceField().getText());
				int numOfStrings1 = Integer.parseInt(addBassGuitar.getNumOfStrings().getText());
				boolean fretless = addBassGuitar.getCheckFretless().isSelected();
				allInstruments.add(new Bass(brand1, price1, numOfStrings1, fretless));
				searchInstruments.add(new Bass(brand1, price1, numOfStrings1, fretless));
				
				break;

			case "Saxophone":
				String brand2 = addSaxophone.getBrandField().getText();
				double price2 = Double.parseDouble(addSaxophone.getPriceField().getText());
				allInstruments.add(new Saxophone(brand2, price2));
				searchInstruments.add(new Saxophone(brand2, price2));
				break;
			
			case "Flute":
				String brand3 = addFlute.getBrandField().getText();
				double price3 = Double.parseDouble(addFlute.getPriceField().getText());
				String material = addFlute.getComboBoxMaterial().getValue();
				String type3 = addFlute.getComboBoxType().getValue();		
				allInstruments.add(new Flute(brand3, price3, material, type3));
				searchInstruments.add(new Flute(brand3, price3, material, type3));
				break;

			default:
				break;
						
			}
			grid.updateView(allInstruments, 0);
			
		} catch (InputMismatchException e) {
			alert.setContentText(e.getMessage());
			alert.show();
		}
		catch (NullPointerException e) {
			alert.setContentText("You Must Choose a Type/ Material");
			alert.show();
		}
		
		catch (NumberFormatException e) {
			alert.setContentText("Illegal Input For a Number!");
			alert.show();
		}
		catch (IllegalArgumentException e) {
			alert.setContentText(e.getMessage());
			alert.show();
		}

	}

}
