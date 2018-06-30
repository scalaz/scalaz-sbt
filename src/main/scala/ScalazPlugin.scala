import sbt._, Keys._

import de.heikoseeberger.sbtheader.HeaderPlugin

object ScalazPlugin extends AutoPlugin {

  override def trigger = allRequirements

  override def requires = HeaderPlugin

  override def buildSettings = Seq(
    organization     := "org.scalaz",
    organizationName := "Scalaz",
    startYear        := Some(2018),
    licenses         += ("Apache-2.0", new URL("https://www.apache.org/licenses/LICENSE-2.0.txt"))
  )

}
