organization := "org.scalaz"
name         := "scalaz-sbt"
version      := "0.1-SNAPSHOT"
scalaVersion := "2.12.6"
sbtPlugin    := true

scriptedLaunchOpts := { scriptedLaunchOpts.value ++
  Seq("-Xmx1024M", "-Dplugin.version=" + version.value)
}
scriptedBufferLog := false
