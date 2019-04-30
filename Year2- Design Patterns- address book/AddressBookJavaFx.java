//name: aviv tfilin
//id: 313459869

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;

public class AddressBookJavaFx extends Application {
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		for (int i = 0; i < AddressBookPane.MAX_PANES; i++) {
			AddressBookPane p = AddressBookPane.getInstance();
			if (p != null) {
				Pane pane = p.GetPane();
				Scene scene = new Scene(pane);
				scene.getStylesheets().add("styles.css");
				primaryStage.setTitle("AddressBook");
				primaryStage.setScene(scene);
				primaryStage.show();
				primaryStage.setAlwaysOnTop(true);
				primaryStage = new Stage();
			} else
				System.out.println("Singelton violation. Only 3 panes were created\r\n");
		}
		
	}
}

class AddressBookPane extends GridPane {
	private RandomAccessFile raf;
	// Text fields
	private TextField jtfName = new TextField();
	private TextField jtfStreet = new TextField();
	private TextField jtfCity = new TextField();
	private TextField jtfState = new TextField();
	private TextField jtfZip = new TextField();
	// Buttons
	private AddButton jbtAdd;
	private FirstButton jbtFirst;
	private NextButton jbtNext;
	private PreviousButton jbtPrevious;
	private LastButton jbtLast;
	private UndoButton jbUndo;
	private RedoButton jbRedo;

	private FlowPane buttonPane = new FlowPane();
	protected Originator originator = new Originator();
	protected CareTaker careTaker = new CareTaker();

	private static int numOfPanes = 0;
	public static final int MAX_PANES = 3;
	private static final int NUM_UPDATE_PANES = 1;

	ArrayList<CommandButton> buttonList = new ArrayList<CommandButton>();
	public EventHandler<ActionEvent> ae = new EventHandler<ActionEvent>() {
		public void handle(ActionEvent arg0) {
			((Command) arg0.getSource()).Execute();
		}
	};

	public static AddressBookPane getInstance() {
		AddressBookPane ap = new AddressBookPane();
		numOfPanes++;
		if (numOfPanes <= NUM_UPDATE_PANES) {
			Decorator.decorator(ap.buttonPane, true, ap.buttonList);
			return ap;

		} else {
			if (numOfPanes <= MAX_PANES) {
				Decorator.decorator(ap.buttonPane, false, ap.buttonList);
				return ap;

			} else
				return null;
		}

	}

	private AddressBookPane() { // Open or create a random access file
		try {
			raf = new RandomAccessFile("address.dat", "rw");
		} catch (IOException ex) {
			System.out.print("Error: " + ex);
			System.exit(0);
		}
		jtfState.setAlignment(Pos.CENTER_LEFT);
		jtfState.setPrefWidth(25);
		jtfZip.setPrefWidth(60);

		// buttons
		jbtAdd = new AddButton(this, raf, "add", true, originator, careTaker);
		jbtFirst = new FirstButton(this, raf, "first", false);
		jbtNext = new NextButton(this, raf, "next", false);
		jbtPrevious = new PreviousButton(this, raf, "previous", false);
		jbtLast = new LastButton(this, raf, "last", false);
		jbUndo = new UndoButton(this, raf, "undo", true, originator, careTaker);
		jbRedo = new RedoButton(this, raf, "redo", true, originator, careTaker);

		// add buttons to array
		buttonList.add(jbtAdd);
		buttonList.add(jbtFirst);
		buttonList.add(jbtNext);
		buttonList.add(jbtPrevious);
		buttonList.add(jbtLast);
		buttonList.add(jbUndo);
		buttonList.add(jbRedo);

		Label state = new Label("State");
		Label zp = new Label("Zip");
		Label name = new Label("Name");
		Label street = new Label("Street");
		Label city = new Label("City");
		// Panel p1 for holding labels Name, Street, and City
		GridPane p1 = new GridPane();
		p1.add(name, 0, 0);
		p1.add(street, 0, 1);
		p1.add(city, 0, 2);
		p1.setAlignment(Pos.CENTER_LEFT);
		p1.setVgap(8);
		p1.setPadding(new Insets(0, 2, 0, 2));
		GridPane.setVgrow(name, Priority.ALWAYS);
		GridPane.setVgrow(street, Priority.ALWAYS);
		GridPane.setVgrow(city, Priority.ALWAYS);
		// City Row
		GridPane adP = new GridPane();
		adP.add(jtfCity, 0, 0);
		adP.add(state, 1, 0);
		adP.add(jtfState, 2, 0);
		adP.add(zp, 3, 0);
		adP.add(jtfZip, 4, 0);
		adP.setAlignment(Pos.CENTER_LEFT);
		GridPane.setHgrow(jtfCity, Priority.ALWAYS);
		GridPane.setVgrow(jtfCity, Priority.ALWAYS);
		GridPane.setVgrow(jtfState, Priority.ALWAYS);
		GridPane.setVgrow(jtfZip, Priority.ALWAYS);
		GridPane.setVgrow(state, Priority.ALWAYS);
		GridPane.setVgrow(zp, Priority.ALWAYS);
		// Panel p4 for holding jtfName, jtfStreet, and p3
		GridPane p4 = new GridPane();
		p4.add(jtfName, 0, 0);
		p4.add(jtfStreet, 0, 1);
		p4.add(adP, 0, 2);
		p4.setVgap(1);
		GridPane.setHgrow(jtfName, Priority.ALWAYS);
		GridPane.setHgrow(jtfStreet, Priority.ALWAYS);
		GridPane.setHgrow(adP, Priority.ALWAYS);
		GridPane.setVgrow(jtfName, Priority.ALWAYS);
		GridPane.setVgrow(jtfStreet, Priority.ALWAYS);
		GridPane.setVgrow(adP, Priority.ALWAYS);
		// Place p1 and p4 into jpAddress
		GridPane jpAddress = new GridPane();
		jpAddress.add(p1, 0, 0);
		jpAddress.add(p4, 1, 0);
		GridPane.setHgrow(p1, Priority.NEVER);
		GridPane.setHgrow(p4, Priority.ALWAYS);
		GridPane.setVgrow(p1, Priority.ALWAYS);
		GridPane.setVgrow(p4, Priority.ALWAYS);
		// Set the panel with line border
		jpAddress.setStyle("-fx-border-color: grey;" + " -fx-border-width: 1;" + " -fx-border-style: solid outside ;");
		// Add buttons to a panel
		// FlowPane jpButton = new FlowPane();
		buttonPane.setHgap(5);
		buttonPane.setAlignment(Pos.CENTER);
		GridPane.setVgrow(buttonPane, Priority.NEVER);
		GridPane.setVgrow(jpAddress, Priority.ALWAYS);
		GridPane.setHgrow(buttonPane, Priority.ALWAYS);
		GridPane.setHgrow(jpAddress, Priority.ALWAYS);
		// Add jpAddress and jpButton to the stage
		this.setVgap(5);
		this.add(jpAddress, 0, 0);
		this.add(buttonPane, 0, 1);
		jbtAdd.setOnAction(ae);
		jbtFirst.setOnAction(ae);
		jbtNext.setOnAction(ae);
		jbtPrevious.setOnAction(ae);
		jbtLast.setOnAction(ae);
		jbUndo.setOnAction(ae);
		jbRedo.setOnAction(ae);
		jbtFirst.Execute();
	}

	public void actionHandled(ActionEvent e) {
		((Command) e.getSource()).Execute();
	}

	public void SetName(String text) {
		jtfName.setText(text);
	}

	public void SetStreet(String text) {
		jtfStreet.setText(text);
	}

	public void SetCity(String text) {
		jtfCity.setText(text);
	}

	public void SetState(String text) {
		jtfState.setText(text);
	}

	public void SetZip(String text) {
		jtfZip.setText(text);
	}

	public String GetName() {
		return jtfName.getText();
	}

	public String GetStreet() {
		return jtfStreet.getText();
	}

	public String GetCity() {
		return jtfCity.getText();
	}

	public String GetState() {
		return jtfState.getText();
	}

	public String GetZip() {
		return jtfZip.getText();
	}

	public AddressBookPane GetPane() {
		return this;
	}
}

interface Command {
	public void Execute();
}

///////////
class CommandButton extends Button implements Command {
	public final static int NAME_SIZE = 32;
	public final static int STREET_SIZE = 32;
	public final static int CITY_SIZE = 20;
	public final static int STATE_SIZE = 2;
	public final static int ZIP_SIZE = 5;
	public final static int RECORD_SIZE = (NAME_SIZE + STREET_SIZE + CITY_SIZE + STATE_SIZE + ZIP_SIZE);
	protected boolean update;
	protected AddressBookPane p;
	protected RandomAccessFile raf;

	public CommandButton(AddressBookPane p, RandomAccessFile raf, String text, boolean update) {
		super(text);
		this.p = p;
		this.raf = raf;
		this.update = update;
	}

	public void Execute() {
	}
	
	public void saveToMemento(Originator originator, CareTaker careTaker) {
		try {
			for (int i = 0; i < raf.length(); i += RECORD_SIZE * 2) {
				raf.seek(i);
				String s = FixedLengthStringIO.readFixedLengthString(RECORD_SIZE, raf);
				originator.setState(s);
				careTaker.add(originator.saveStateToMemento());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/** Write a record at the end of the file */
	public void writeAddress() {
		try {
			raf.seek(raf.length());
			FixedLengthStringIO.writeFixedLengthString(p.GetName(), NAME_SIZE, raf);
			FixedLengthStringIO.writeFixedLengthString(p.GetStreet(), STREET_SIZE, raf);
			FixedLengthStringIO.writeFixedLengthString(p.GetCity(), CITY_SIZE, raf);
			FixedLengthStringIO.writeFixedLengthString(p.GetState(), STATE_SIZE, raf);
			FixedLengthStringIO.writeFixedLengthString(p.GetZip(), ZIP_SIZE, raf);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	/** Read a record at the specified position */
	public void readAddress(long position) throws IOException {
		raf.seek(position);
		String name = FixedLengthStringIO.readFixedLengthString(NAME_SIZE, raf);
		String street = FixedLengthStringIO.readFixedLengthString(STREET_SIZE, raf);
		String city = FixedLengthStringIO.readFixedLengthString(CITY_SIZE, raf);
		String state = FixedLengthStringIO.readFixedLengthString(STATE_SIZE, raf);
		String zip = FixedLengthStringIO.readFixedLengthString(ZIP_SIZE, raf);
		p.SetName(name);
		p.SetStreet(street);
		p.SetCity(city);
		p.SetState(state);
		p.SetZip(zip);
	}

	public void clearPane() {
		p.SetName("");
		p.SetStreet("");
		p.SetCity("");
		p.SetState("");
		p.SetZip("");

	}
}

class AddButton extends CommandButton {
	Originator originator;
	CareTaker careTaker;

	public AddButton(AddressBookPane pane, RandomAccessFile r, String text, boolean update, Originator originator,
			CareTaker careTaker) {
		super(pane, r, text, update);
		this.careTaker = careTaker;
		this.originator = originator;

	}

	@Override
	public void Execute() {
		try {
			if(raf.length() > 0 && careTaker.isEmpty2()) {
				saveToMemento(originator, careTaker);
			}
			writeAddress();
			raf.seek(raf.getFilePointer() - 2 * RECORD_SIZE);
			String s = FixedLengthStringIO.readFixedLengthString(RECORD_SIZE, raf);
			originator.setState(s);
			careTaker.add(originator.saveStateToMemento());

		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}

class NextButton extends CommandButton {
	public NextButton(AddressBookPane pane, RandomAccessFile r, String text, boolean update) {
		super(pane, r, text, update);

	}

	@Override
	public void Execute() {
		try {
			long currentPosition = raf.getFilePointer();
			if (currentPosition < raf.length())
				readAddress(currentPosition);
			if (raf.length() == 0) {
				clearPane();
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
}

class PreviousButton extends CommandButton {
	public PreviousButton(AddressBookPane pane, RandomAccessFile r, String text, boolean update) {
		super(pane, r, text, update);
	}

	@Override
	public void Execute() {
		try {
			if (raf.length() == 0) {
				clearPane();

			} else {
				long currentPosition = raf.getFilePointer();
				if (currentPosition - 2 * 2 * RECORD_SIZE >= 0)
					readAddress(currentPosition - 2 * 2 * RECORD_SIZE);
				else
					;
			}

		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
}

class LastButton extends CommandButton {
	public LastButton(AddressBookPane pane, RandomAccessFile r, String text, boolean update) {
		super(pane, r, text, update);
	}

	@Override
	public void Execute() {
		try {
			if (raf.length() == 0) {
				clearPane();
			}
			long lastPosition = raf.length();
			if (lastPosition > 0)
				readAddress(lastPosition - 2 * RECORD_SIZE);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
}

class FirstButton extends CommandButton {
	public FirstButton(AddressBookPane pane, RandomAccessFile r, String text, boolean update) {
		super(pane, r, text, update);
	}

	@Override
	public void Execute() {
		try {
			if (raf.length() == 0) {
				clearPane();

			}
			if (raf.length() > 0)
				readAddress(0);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
}

class UndoButton extends CommandButton {
	Originator originator;
	CareTaker careTaker;

	public UndoButton(AddressBookPane pane, RandomAccessFile r, String text, boolean update, Originator originator,
			CareTaker careTaker) {
		super(pane, r, text, update);
		this.careTaker = careTaker;
		this.originator = originator;

	}

	@Override
	public void Execute() {
		try {
			long lastPosition = raf.length();
			if (lastPosition > 0) {
				if(careTaker.isEmpty2()) {
					saveToMemento(originator, careTaker);
				}
				originator.getStateFromMemento(careTaker.getPrev());
				raf.setLength(raf.length() - 2 * RECORD_SIZE);

				if (raf.length() > 0) {
					readAddress(0);

				} else {
					clearPane();
				}
			}

		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
}

class RedoButton extends CommandButton {
	Originator originator;
	CareTaker careTaker;

	public RedoButton(AddressBookPane pane, RandomAccessFile r, String text, boolean update, Originator originator,
			CareTaker careTaker) {
		super(pane, r, text, update);
		this.careTaker = careTaker;
		this.originator = originator;
	}

	@Override
	public void Execute() {
		try {
			long lastPosition = raf.length();
			if (lastPosition > 0) {
				originator.getStateFromMemento(careTaker.getNext());
				if (originator.getS() != null) {
					raf.seek(lastPosition);
					FixedLengthStringIO.writeFixedLengthString(originator.getS(), RECORD_SIZE, raf);
					readAddress(lastPosition - 2 * RECORD_SIZE);
				}
			}

			else {
				if (originator.getS() != null) {
					raf.seek(0);
					FixedLengthStringIO.writeFixedLengthString(originator.getS(), RECORD_SIZE, raf);
					readAddress(0);

				}
			}

		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}



}
