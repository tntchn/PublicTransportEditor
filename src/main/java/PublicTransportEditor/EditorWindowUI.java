package PublicTransportEditor;

import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class EditorWindowUI extends ScrollPane {
    /** EditorWindowUI is the working part of a PT */
    protected EditorWindowUI() {
        Rectangle rect = new Rectangle(200, 200, Color.RED);
        this.setContent(rect);
        
        prefWidth(350.0);
        prefHeight(250.0);
        maxWidth(400.0);
        maxHeight(300.0);
        setBackground(new Background(new BackgroundFill(Color.BLUE, CornerRadii.EMPTY, new Insets(5.0))));



        Logger.getLogger("a").log(Level.INFO, "SetWindowUIColor");
        // ListView<Box> tagList = new ListView<Box>();
    }
}
