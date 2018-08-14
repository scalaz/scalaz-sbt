import sbt._

organization := "org.scalaz"
name         := "scalaz-sbt"
version      := "0.1-SNAPSHOT"
scalaVersion := "2.12.6"
sbtPlugin    := true

scriptedLaunchOpts := { scriptedLaunchOpts.value ++
  Seq("-Xmx1024M", "-Dplugin.version=" + version.value)
}

scriptedBufferLog := false


addSbtPlugin("de.heikoseeberger"  % "sbt-header"   % "5.0.0")
addSbtPlugin("com.dwijnand"       % "sbt-dynver"   % "3.0.0")
addSbtPlugin("com.dwijnand"       % "sbt-travisci" % "1.1.1")
addSbtPlugin("com.geirsson"       % "sbt-scalafmt" % "1.6.0-RC3")
addSbtPlugin("pl.project13.scala" % "sbt-jmh"      % "0.3.3")


scalafmtOnCompile := true
scalafmtConfig := Some(baseDirectory.value / "src" / "main" / "resources" / "scalafmt.conf")

