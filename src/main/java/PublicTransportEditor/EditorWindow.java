package PublicTransportEditor;

import static org.openstreetmap.josm.tools.I18n.tr;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import org.openstreetmap.josm.data.osm.Relation;
import org.openstreetmap.josm.gui.ExtendedDialog;
import org.openstreetmap.josm.gui.MainApplication;
import org.openstreetmap.josm.gui.layer.OsmDataLayer;
import org.openstreetmap.josm.tools.CheckParameterUtil;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;

public class EditorWindow extends ExtendedDialog {
    private static final long serialVersionUID = 1L;
    private final transient OsmDataLayer layer;
    private transient Relation relation;
    private transient Relation relationSnapshot;

    private EditorWindowUI windowUI;
    private static VBox pane;

    // The window only initialize at mapFrameInitialized
    protected EditorWindow() {
        super(MainApplication.getMainFrame(), "", new String[] { "Apply Changes", "Cancel" }, false, false);

        this.layer = null;
        this.relation = null;

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new JFrame();
                final JFXPanel fxPanel = new JFXPanel();

                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        windowUI = new EditorWindowUI();
                        pane = new VBox();
                        fxPanel.setScene(new Scene(pane));

                    }
                });
                frame.add(fxPanel);
                frame.setVisible(true);
            }
        });
    }

    public EditorWindow showEditor(OsmDataLayer layer, Relation relation) {
        // CheckParameterUtil.ensureParameterNotNull(layer, "layer");
        setRelation(relation);
        if (isVisible())
            showDialog();
        return this;
    }

    protected void updateTitle() {
        if (getRelation() == null) {
            setTitle(tr("Create new public transport relation in layer ''{0}''", layer.getName()));
        } else if (getRelation().isNew()) {
            setTitle(tr("Edit new public transport relation in layer ''{0}''", layer.getName()));
        } else {
            setTitle(tr("Edit public transport relation #{0} in layer ''{1}''", relation.getId(), layer.getName()));
        }
    }

    protected final OsmDataLayer getLayer() {
        return layer;
    }

    protected final Relation getRelation() {
        return relation;
    }

    protected final void setRelation(Relation relation) {
        setRelationSnapshot((relation == null) ? null : new Relation(relation));
        this.relation = relation;
        // updateTitle();
    }

    protected final Relation getRelationSnapshot() {
        return relationSnapshot;
    }

    protected final void setRelationSnapshot(Relation snapshot) {
        if (relationSnapshot != null && relationSnapshot.getDataSet() == null)
            relationSnapshot.setMembers(null);
        relationSnapshot = snapshot;
    }

    private static void initFX(JFXPanel fxPanel) {
        // This method is invoked on JavaFX thread
        fxPanel.setScene(new Scene(pane));
    }
}
