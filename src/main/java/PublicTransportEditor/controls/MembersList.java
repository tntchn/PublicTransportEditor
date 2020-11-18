package PublicTransportEditor.controls;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;

import org.openstreetmap.josm.data.osm.Relation;
import org.openstreetmap.josm.data.osm.RelationMember;
import org.openstreetmap.josm.tools.Logging;

public class MembersList extends ListView<RelationMember> {

    public MembersList(Relation relation) {
        super();
        createDefaultSkin();
        this.setPrefWidth(500);

        // Fix test data delay problem
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ObservableList<RelationMember> members = FXCollections.observableArrayList(relation.getMembers());

        // Set ObservableList into ListView and call setCellFactory()
        Logging.info("GO TO SET MEMBERS");
        this.setItems(members);
        if (members.isEmpty()) Logging.info("IS EMPTY");
        this.setCellFactory(memberListView -> new RelationMemberCell());
        Logging.info("FINISH SETCELLFACTORY");
    }

    /** 
     * RelationMemberCell is the style definition of a member cell,
     * constructed by setCellFactory()
     */
    static class RelationMemberCell extends ListCell<RelationMember> {
        @Override
        public void updateItem(RelationMember rm, boolean empty) {
            super.updateItem(rm, empty);
            
            if (empty || rm == null) {
                setText(null);
                setGraphic(null);
            } else {
                Label name = new Label(rm.getMember().getName());
                VBox relationMemberBox= new VBox(8, name);
                setGraphic(relationMemberBox);
            }
        }
    }
}
