package PublicTransportEditor;

import static org.openstreetmap.josm.tools.I18n.tr;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import org.openstreetmap.josm.data.osm.Relation;
import org.openstreetmap.josm.gui.ExtendedDialog;
import org.openstreetmap.josm.gui.MainApplication;
import org.openstreetmap.josm.gui.layer.OsmDataLayer;
import org.openstreetmap.josm.tools.CheckParameterUtil;
import org.openstreetmap.josm.tools.ImageProvider;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;

import java.awt.event.ActionEvent;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EditorWindow extends ExtendedDialog {
    private static final long serialVersionUID = 1L;
    private final transient OsmDataLayer layer;
    private transient Relation relation;
    private transient Relation relationSnapshot;

    private JFXPanel fxPanel;

    /**
     * Generate a EditorWindow only at mapFrameInitialized().
     */
    protected EditorWindow() {
        super(MainApplication.getMainFrame(), "", new String[] { "Apply Changes", "Cancel" }, false, false);

        layer = null;
        relation = null;

        fxPanel = new JFXPanel();
        SwingUtilities.invokeLater(() -> {
            this.add(fxPanel);
            Platform.runLater(() -> fxPanel.setScene(createScene()));
        });

        getContentPane().add(fxPanel, BorderLayout.CENTER);

        JPanel bp = new JPanel(new FlowLayout());
        bp.add(new JButton(new ApplyAction()));
        bp.add(new JButton(new CancelAction()));
        getContentPane().add(bp, BorderLayout.SOUTH);
    }

    private Scene createScene() {
        EditorWindowUI windowUI = new EditorWindowUI();
        windowUI.setVisible(true);
        return new Scene(windowUI, 500, 350);
    }

    public EditorWindow showEditor(OsmDataLayer layer, Relation relation) {
        // CheckParameterUtil.ensureParameterNotNull(layer, "layer");
        Logger.getLogger("a").log(Level.INFO, "a2");
        setRelation(relation);
        setSize(500, 350);
        setResizable(false);
        if (!isVisible())
            setVisible(true);
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
}

class ApplyAction extends AbstractAction {
    private static final long serialVersionUID = 1L;

    protected ApplyAction() {
        putValue(SHORT_DESCRIPTION, tr("Apply the updates and close the dialog"));
        new ImageProvider("ok").getResource().attachImageIcon(this);
        putValue(NAME, tr("OK"));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub

    }

}

class CancelAction extends AbstractAction {
    private static final long serialVersionUID = 1L;

    protected CancelAction() {
        putValue(SHORT_DESCRIPTION, tr("Cancel the updates and close the dialog"));
        new ImageProvider("cancel").getResource().attachImageIcon(this);
        putValue(NAME, tr("Cancel"));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub

    }

}