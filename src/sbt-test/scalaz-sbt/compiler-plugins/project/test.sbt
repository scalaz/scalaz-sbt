val pluginVersion =
  sys.props
    .get("plugin.version")
    .getOrElse(sys.error("Sys prop plugin.version must be defined!"))

addSbtPlugin("org.scalaz" % "scalaz-sbt" % pluginVersion)
