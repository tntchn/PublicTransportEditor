package PublicTransportEditor;

import static org.openstreetmap.josm.tools.I18n.tr;

import org.openstreetmap.josm.data.osm.Relation;
import org.openstreetmap.josm.gui.ExtendedDialog;
import org.openstreetmap.josm.gui.MainApplication;
import org.openstreetmap.josm.gui.layer.OsmDataLayer;
import org.openstreetmap.josm.tools.CheckParameterUtil;

public class EditorWindow extends ExtendedDialog {
    private static final long serialVersionUID = 1L;
    private final transient OsmDataLayer layer;
    private transient Relation relation;

    private transient Relation relationSnapshot;

    protected EditorWindow(OsmDataLayer layer, Relation relation) {
        super(MainApplication.getMainFrame(),
                "",
                new String[] {"Apply Changes", "Cancel"},
                false,
                false
        );

        // CheckParameterUtil.ensureParameterNotNull(layer, "layer");
        this.layer = layer;
    }

    public EditorWindow showEditor(OsmDataLayer layer, Relation relation) {
        setRelation(relation);
        if (isVisible()) showDialog();
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

    protected final Relation getRelation() {
        return relation;
    }

    protected final void setRelation(Relation relation) {
        setRelationSnapshot((relation == null) ? null : new Relation(relation));
        this.relation = relation;
        updateTitle();
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
