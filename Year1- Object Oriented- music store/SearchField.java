// aviv tfilin 313459869

import javafx.scene.control.TextField;
import java.util.ArrayList;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

public class SearchField extends HBox {
	private TextField search = new TextField();
	private Button goButton = new Button("Go!");
	private ArrayList<MusicalInstrument> searchInstruments;

	
	public SearchField(ArrayList<MusicalInstrument> allInstruments, InstrumentsGrid grid) {
		search.setPromptText("search....");
		getChildren().addAll(search, goButton);
		setPadding(new Insets(AfekaInstruments.SPACE));
		setHgrow(search, Priority.ALWAYS);
		setSpacing(AfekaInstruments.SPACE);
		
		searchInstruments = new ArrayList<MusicalInstrument>();
		
		// create 2 arrays with instruments
		searchInstruments.addAll(allInstruments);
		
	}

	public ArrayList<MusicalInstrument> search(ArrayList<MusicalInstrument> allInstruments) {	
		searchInstruments.clear();
		for (int i = 0; i < allInstruments.size(); i++) {
			if (allInstruments.get(i).toString().contains(search.getText())) {
				searchInstruments.add(allInstruments.get(i));

			}
		}
		return searchInstruments;
	}

	public ArrayList<MusicalInstrument> getSearchInstruments() {
		return searchInstruments;
	}

	public void setSearchInstruments(ArrayList<MusicalInstrument> searchInstruments) {
		this.searchInstruments = searchInstruments;
	}

	public Button getGoButton() {
		return goButton;
	}

	public void setGoButton(Button goButton) {
		this.goButton = goButton;
	}

}
