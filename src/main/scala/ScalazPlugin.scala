import sbt._, Keys._

object ScalazPlugin extends AutoPlugin {

  override def buildSettings = Seq(
    organization := "org.scalaz"
  )

}
