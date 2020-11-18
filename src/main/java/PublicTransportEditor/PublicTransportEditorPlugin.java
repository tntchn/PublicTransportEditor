package PublicTransportEditor;

import org.openstreetmap.josm.data.osm.Relation;
import org.openstreetmap.josm.gui.MainApplication;
import org.openstreetmap.josm.gui.MapFrame;
import org.openstreetmap.josm.gui.layer.OsmDataLayer;
import org.openstreetmap.josm.plugins.Plugin;
import org.openstreetmap.josm.plugins.PluginInformation;

public class PublicTransportEditorPlugin extends Plugin {
    public PublicTransportEditorPlugin(PluginInformation info) {
        super(info);
    }

    @Override
    public void mapFrameInitialized(MapFrame oldFrame, MapFrame newFrame) {
        if (oldFrame == null && newFrame != null) {
            EditorWindow editorWindow = new EditorWindow();
            OsmDataLayer layer = MainApplication.getLayerManager().getActiveDataLayer();
            editorWindow = editorWindow.showEditor(layer, new Relation(11601762));
        }
    }
}
