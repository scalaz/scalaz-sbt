import argonaut._, Argonaut._

disablePlugins(ScalazPlugin)

scalacOptions ++= scalaz.build.Compilation.stdScalacOptions

val url = "https://api.github.com/repos/scala/scala/commits/2.13.x"

val sha = {
  val json = scala.io.Source.fromURL(url).mkString.parse.fold(sys.error(_), identity)
  case class Commit(sha: String)
  val decodeResult = jdecode1L(Commit.apply)("sha").decodeJson(json).result
  val commit = decodeResult.fold(e => sys.error(e.toString + " " + json.spaces2), identity)
  val result = commit.sha.take(7)
  println("latest scala = " + commit)
  result
}

resolvers += "scala-pr" at "https://scala-ci.typesafe.com/artifactory/scala-integration/"

scalaVersion := s"2.13.0-pre-$sha"
