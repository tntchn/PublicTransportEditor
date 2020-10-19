package PublicTransportEditor;

import org.openstreetmap.josm.gui.MainApplication;
import org.openstreetmap.josm.gui.layer.OsmDataLayer;
import org.openstreetmap.josm.plugins.Plugin;
import org.openstreetmap.josm.plugins.PluginInformation;

public class PublicTransportEditorPlugin extends Plugin {
    public PublicTransportEditorPlugin(PluginInformation info) {
        super(info);

        EditorWindow editorWindow = new EditorWindow(null, null);

        // test
        OsmDataLayer layer = MainApplication.getLayerManager().getActiveDataLayer();
        editorWindow = editorWindow.showEditor(layer, null);
    }
}
