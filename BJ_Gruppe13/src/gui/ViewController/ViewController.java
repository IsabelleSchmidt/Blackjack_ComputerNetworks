package gui.ViewController;
import javafx.scene.layout.Pane;

public abstract class ViewController<T> {
	protected Pane rootView;
	protected T application;

	public ViewController() {
	}

	public ViewController(T application) {
		this.application = application;
	}

	public Pane getRootView() {
		return rootView;
	}

	abstract public void initialize();

	public void setApplication(T application) {
		this.application = application;
	}

}
