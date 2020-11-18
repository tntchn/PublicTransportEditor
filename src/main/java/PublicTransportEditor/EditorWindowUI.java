package PublicTransportEditor;

import PublicTransportEditor.controls.MembersList;

import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.control.Button;
import javafx.scene.control.SplitPane;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import org.openstreetmap.josm.data.osm.Relation;
import org.openstreetmap.josm.tools.Logging;

public class EditorWindowUI extends SplitPane {
    private Relation relation;

    private VBox tagsPane;
    
    private VBox membersPane;
    private transient ToolBar membersToolBar;
    private transient ToolBar membersFilterBar;
    private transient MembersList membersList;

    /** EditorWindowUI is the working part of a PT */
    protected EditorWindowUI() {
        super();
        setOrientation(Orientation.VERTICAL);

        tagsPane = new VBox();
        membersPane = new VBox();

        Button deleteMembersButton = new Button("-");
        Button addMembersButton = new Button("+");
        membersToolBar = new ToolBar(deleteMembersButton, addMembersButton);
        membersToolBar.setPadding(new Insets(0));
        membersToolBar.setOrientation(Orientation.VERTICAL);
        membersToolBar.setPrefWidth(deleteMembersButton.getWidth());

        Button allButton = new Button("ALL");
        Button moreButton = new Button("...");
        membersFilterBar = new ToolBar(allButton, moreButton);

        getItems().addAll(tagsPane, membersPane);
        prefWidth(500.0);
        prefHeight(350.0);
        maxWidth(500.0);
        maxHeight(350.0);

        Logging.info("Set WindowUI");
    }

    public void setUIRelation(Relation r) {
        relation = r;
        membersList = new MembersList(relation);
        membersList.setBackground(new Background(new BackgroundFill(Color.AQUA, new CornerRadii(10), Insets.EMPTY)));
        HBox membersListPane = new HBox(membersList, membersToolBar);
        membersPane.getChildren().setAll(membersFilterBar, membersListPane);
        VBox.setMargin(membersListPane, new Insets(10));
    }
}
