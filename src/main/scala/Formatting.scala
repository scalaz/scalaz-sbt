package scalaz.build

import java.nio.file.{ Files, Paths }

import org.scalafmt.sbt.ScalafmtPlugin.autoImport.scalafmtOnCompile
import sbt._

import scala.io.Source

object Formatting {

  private val pluginConfigFileName    = "scalafmt.conf"
  private val generatedConfigFileName = ".scalafmt.conf"
  val scalafmtConfigFileCreated       = TaskKey[Boolean]("scalafmtConfigFileCreated")
  val scalafmtGenerateConfigOnLoad    = SettingKey[Unit]("scalafmtGenerateConfigOnLoad")

  val buildSettings = Seq(
    scalafmtOnCompile := true,
    scalafmtGenerateConfigOnLoad := generateConfigFileFormSource(
      pluginConfigFileName,
      generatedConfigFileName
    ),
    scalafmtConfigFileCreated := checkIfFileIsCreated(generatedConfigFileName)
  )

  private def generateConfigFileFormSource(sourceName: String, fileName: String): Unit =
    IO.write(
      file(fileName),
      Source.fromInputStream(getClass.getResourceAsStream(s"/$sourceName")).mkString
    )

  private def checkIfFileIsCreated(fileName: String): Boolean =
    if (Files.exists(Paths.get(fileName)))
      true
    else
      throw new MessageOnlyException(
        s"$fileName file doesn't exist."
      )
}
