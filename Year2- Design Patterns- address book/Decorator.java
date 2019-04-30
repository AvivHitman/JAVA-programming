//name: aviv tfilin
//id: 313459869
import java.util.ArrayList;
import java.util.List;
import javafx.scene.layout.FlowPane;

public class Decorator {

	public static void decorator(FlowPane button, boolean update, ArrayList<CommandButton> buttonList) {
		if (update) {
			for (int i = 0; i < buttonList.size(); i++) {
				button.getChildren().add(buttonList.get(i));
			}
		} else {
			for (int i = 0; i < buttonList.size(); i++) {
				if (!buttonList.get(i).update)
					button.getChildren().add(buttonList.get(i));
			}
		}

	}
}

class Memento {
	private String s;

	public Memento(String s) {
		this.s = s;

	}

	public String getS() {
		return s;
	}
}

// ***** CareTaker Class *****///
class CareTaker {
	private List<Memento> mementoList = new ArrayList<Memento>();
	private int index;

	public CareTaker() {
		index = mementoList.size();
	}

	public void add(Memento state) {
		if (state != null) {
			mementoList.add(state);
			index = mementoList.size() - 1;
		}
	}

	public Memento getPrev() {
		if (mementoList.isEmpty() || index <= 0) {
			return null;
		}
		return mementoList.get(--index);
	}

	public Memento getNext() {
		if (mementoList.isEmpty() || index >= mementoList.size() - 1) {
			return null;
		}
		return mementoList.get(++index);
	}
	
	public Memento getCurrent() {
		if (mementoList.isEmpty() || index >= mementoList.size() - 1) {
			return null;
		}
		return mementoList.get(index);
	}
	
	public boolean isEmpty2() {
		return mementoList.isEmpty();
	}
	
	
}

// ***** Originator Class *****///
class Originator {
	private String s;

	public String getS() {
		return s;
	}

	public void setState(String s) {
		this.s = s;

	}

	public Memento saveStateToMemento() {
		return new Memento(s);
	}

	public void getStateFromMemento(Memento memento) {
		if (memento != null) {
			this.s = memento.getS();

		}
	}
}
