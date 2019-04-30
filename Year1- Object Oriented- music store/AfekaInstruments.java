// aviv tfilin 313459869
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.input.KeyCode;

public class AfekaInstruments extends Application {
	public final static int WIDTH = 900;
	public final static int HEIGHT = 400;
	public final static int SPACE = 12;
	private Stage window;
	private Scene mainScene;
	private BorderPane mainLayout;
	private Button leftButton = new Button("<");
	private Button rightButton = new Button(">");
	ArrayList<MusicalInstrument> allInstruments;
	private SearchField searchBar;
	private BottomField bottomFields;
	private InstrumentsGrid grid;
	private Insets paddindInserts = new Insets(SPACE, SPACE, SPACE, SPACE);

	@Override
	public void start(Stage primaryStage) throws Exception {
		allInstruments = new ArrayList<MusicalInstrument>();
		File file = getInstrumentsFileFromUser();
		allInstruments = loadInstrumentsFromFile(file, allInstruments);
		searchBar = new SearchField(allInstruments, grid);

		grid = new InstrumentsGrid(searchBar.getSearchInstruments());

		searchBar.getGoButton().setOnAction(e -> go());

		bottomFields = new BottomField();
		bottomFields.getButton1().setOnAction(e -> new AddWindow(searchBar.getSearchInstruments(), allInstruments));

		window = primaryStage;
		window.setTitle("Afeka Instruments Music Store");
		mainLayout = new BorderPane();
		mainLayout.setTop(searchBar);
		mainLayout.setBottom(bottomFields);

		StackPane leftPane = new StackPane();
		leftPane.getChildren().add(leftButton);
		leftPane.setPadding(paddindInserts);
		StackPane rightPane = new StackPane();
		rightPane.getChildren().add(rightButton);
		rightPane.setPadding(paddindInserts);

		rightButton.setOnAction(e -> right());
		leftButton.setOnAction(e -> left());

		// delete one instruments
		bottomFields.getButton2().setOnAction(e -> deleteOneInstrument());

		// delete all instruments
		bottomFields.getButton3().setOnAction(e -> {
			searchBar.getSearchInstruments().clear();
			allInstruments.clear();
			grid.updateView(searchBar.getSearchInstruments(), 0);
		});

		mainLayout.setLeft(leftPane);
		mainLayout.setRight(rightPane);

		mainLayout.setCenter(grid);

		mainScene = new Scene(mainLayout, WIDTH, HEIGHT);
		window.setScene(mainScene);

		window.show();

		mainScene.setOnKeyPressed(event -> {
			if (event.getCode() == KeyCode.ENTER) {
				go();
			}
			if (event.getText().equalsIgnoreCase("A")) {
				new AddWindow(searchBar.getSearchInstruments(), allInstruments);
			}
			if (event.getCode() == KeyCode.DELETE) {
				deleteOneInstrument();
			}
		});

	}

	public void go() {
		grid.updateView(searchBar.search(allInstruments), 0);
	}

	public void right() {
		if (searchBar.getSearchInstruments().size() == 1) {
			grid.setIndex(0);

		} else if (grid.getIndex() != searchBar.getSearchInstruments().size() - 1) {
			grid.setIndex(grid.getIndex() + 1);

		} else {
			grid.setIndex(0);
		}
		grid.updateView(searchBar.getSearchInstruments(), grid.getIndex());
	}

	public void left() {
		if (grid.getIndex() != 0) {
			grid.setIndex(grid.getIndex() - 1);
		} else {
			grid.setIndex(searchBar.getSearchInstruments().size() - 1);
		}

		grid.updateView(searchBar.getSearchInstruments(), grid.getIndex());
	}

	public void deleteOneInstrument() {
		if (searchBar.getSearchInstruments().size() != 0) {
			allInstruments.remove(searchBar.getSearchInstruments().get(grid.getIndex()));
			grid.delete(searchBar.getSearchInstruments());
			grid.updateView(searchBar.getSearchInstruments(), grid.getIndex());

		}
	}

	public static void main(String[] args) {
		launch(args);
	}

	public static File getInstrumentsFileFromUser() {
		boolean stopLoop = true;
		File file;

		do {
			TextInputDialog fileNameFromUser = new TextInputDialog();
			Alert alert = new Alert(Alert.AlertType.ERROR);
			fileNameFromUser.setContentText("Please enter file:");
			Optional<String> result = fileNameFromUser.showAndWait();

			if (!result.isPresent()) { // cancel
				System.exit(0);
			}

			file = new File(result.get());
			stopLoop = file.exists() && file.canRead();

			if (!stopLoop) {
				alert.setContentText("Cannot read from file, please try again");
				alert.showAndWait();
			}

		} while (!stopLoop);

		return file;
	}

	public static ArrayList<MusicalInstrument> loadInstrumentsFromFile(File file,
			ArrayList<MusicalInstrument> allInstruments) {
		Scanner scanner = null;

		try {

			scanner = new Scanner(file);

			addAllInstruments(allInstruments, loadGuitars(scanner));

			addAllInstruments(allInstruments, loadBassGuitars(scanner));

			addAllInstruments(allInstruments, loadFlutes(scanner));

			addAllInstruments(allInstruments, loadSaxophones(scanner));

		} catch (InputMismatchException | IllegalArgumentException ex) {
			System.err.println("\n" + ex.getMessage());
			System.exit(1);
		} catch (FileNotFoundException ex) {
			System.err.println("\nFile Error! File was not found");
			System.exit(2);
		}

		return allInstruments;

	}

	public static ArrayList<MusicalInstrument> loadGuitars(Scanner scanner) {
		int numOfInstruments = scanner.nextInt();
		ArrayList<MusicalInstrument> guitars = new ArrayList<MusicalInstrument>(numOfInstruments);
		for (int i = 0; i < numOfInstruments; i++)
			guitars.add(new Guitar(scanner));
		return guitars;
	}

	public static ArrayList<MusicalInstrument> loadBassGuitars(Scanner scanner) {
		int numOfInstruments = scanner.nextInt();
		ArrayList<MusicalInstrument> bassGuitars = new ArrayList<MusicalInstrument>(numOfInstruments);

		for (int i = 0; i < numOfInstruments; i++)
			bassGuitars.add(new Bass(scanner));

		return bassGuitars;
	}

	public static ArrayList<MusicalInstrument> loadFlutes(Scanner scanner) {
		int numOfInstruments = scanner.nextInt();
		ArrayList<MusicalInstrument> flutes = new ArrayList<MusicalInstrument>(numOfInstruments);

		for (int i = 0; i < numOfInstruments; i++)
			flutes.add(new Flute(scanner));

		return flutes;
	}

	public static ArrayList<MusicalInstrument> loadSaxophones(Scanner scanner) {
		int numOfInstruments = scanner.nextInt();
		ArrayList<MusicalInstrument> saxophones = new ArrayList<MusicalInstrument>(numOfInstruments);

		for (int i = 0; i < numOfInstruments; i++)
			saxophones.add(new Saxophone(scanner));

		return saxophones;
	}

	public static void addAllInstruments(ArrayList<MusicalInstrument> instruments,
			ArrayList<MusicalInstrument> moreInstruments) {
		for (int i = 0; i < moreInstruments.size(); i++) {
			instruments.add(moreInstruments.get(i));
		}
	}

	public static void printInstruments(ArrayList<MusicalInstrument> instruments) {
		for (int i = 0; i < instruments.size(); i++)
			System.out.println(instruments.get(i));
	}

}
