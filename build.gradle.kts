plugins {
    id("org.openstreetmap.josm") version "0.7.1"
}

version = "v0.0.1"
archivesBaseName = "PublicTransportEditor"

josm {
  // debugport = ‹insertDebugPortNumber›
  josmCompileVersion = "17084"
  manifest {
    description = "The description of my awesome plugin"
    mainClass = "PublicTransportEditor.PublicTransportEditorPlugin"
    minJosmVersion = "17084"
    author = "tntchn"
    canLoadAtRuntime = true
    // iconPath = 'path/to/my/icon.svg'
    // loadEarly = false
    // loadPriority = 50
    // pluginDependencies << 'apache-commons' << 'apache-http'
    // website = new URL('https://example.org')
    // oldVersionDownloadLink 123, 'v1.2.0', new URL('https://example.org/download/v1.2.0/MyAwesomePlugin.jar')
    // oldVersionDownloadLink  42, 'v1.0.0', new URL('https://example.org/download/v1.0.0/MyAwesomePlugin.jar')
  }
  // i18n {
  //   bugReportEmail = 'me@example.com'
  //   copyrightHolder = 'John Doe'
  // }
}
