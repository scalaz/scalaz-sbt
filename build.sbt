import sbt._

enablePlugins(ScriptedPlugin)

organization := "org.scalaz"
name         := "scalaz-sbt"
version      := "0.1-SNAPSHOT"
scalaVersion := "2.12.8"
sbtPlugin    := true

scriptedLaunchOpts ++= {
  Seq("-Xmx1024M", "-Dplugin.version=" + version.value)
}

scriptedBufferLog := false


addSbtPlugin("de.heikoseeberger"  % "sbt-header"   % "5.2.0")
addSbtPlugin("com.dwijnand"       % "sbt-dynver"   % "4.0.0")
addSbtPlugin("com.dwijnand"       % "sbt-travisci" % "1.2.0")
addSbtPlugin("org.scalameta"      % "sbt-scalafmt" % "2.0.4")
addSbtPlugin("pl.project13.scala" % "sbt-jmh"      % "0.3.7")


scalafmtOnCompile := true
scalafmtConfig := baseDirectory.value / "src" / "main" / "resources" / "scalafmt.conf"

