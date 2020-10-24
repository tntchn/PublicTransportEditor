package PublicTransportEditor;

import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.shape.Box;

public class EditorWindowUI extends ScrollPane {
    protected EditorWindowUI() {
        this.prefWidth(500.0);
        this.prefHeight(350.0);

        ListView<Box> tagList = new ListView<Box>();
    }
}
