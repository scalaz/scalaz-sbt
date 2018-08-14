package scalaz.build

import sbt._, Keys._

object Publication {

  lazy val sonataCredentials = for {
    username <- sys.env.get("SONATYPE_USERNAME")
    password <- sys.env.get("SONATYPE_PASSWORD")
  } yield Credentials("Sonatype Nexus Repository Manager", "oss.sonatype.org", username, password)

  val buildSettings = Seq(
    credentials ++= sonataCredentials.toSeq,
    publishTo := {
      val nexus = "https://oss.sonatype.org/"
      if (isSnapshot.value)
        Some("snapshots".at(nexus + "content/repositories/snapshots"))
      else
        Some("releases".at(nexus + "service/local/staging/deploy/maven2"))
    }
  )

}
