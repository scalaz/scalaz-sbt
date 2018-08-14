package scalaz.build

import sbt._, Keys._
import org.scalafmt.sbt.ScalafmtPlugin.autoImport.scalafmtOnCompile

object Compilation {

  val buildSettings =
    Seq(
      scalaVersion := "2.12.6",
      crossScalaVersions := Seq("2.11.12", "2.12.6", "2.13.0-M4"),
      scalacOptions := stdScalacOptions ++ crossScalacOptions(
        scalaVersion.value
      ),
      incOptions := incOptions.value.withLogRecompileOnMacro(false)
    )

  val projectSettings = Seq(
    scalacOptions in (Compile, console) --= Seq(
      "-Ywarn-unused:imports",
      "-Xfatal-warnings"
    ),
    libraryDependencies ++= compilerPlugins ++ Seq(
      "com.github.ghik" %% "silencer-lib" % silencerVersion % Provided,
      "org.scalacheck"  %% "scalacheck"   % "1.14.0"        % Test
    )
  )

  val silencerVersion = "1.0"

  // borrowed from https://tpolecat.github.io/2017/04/25/scalac-flags.html
  val stdScalacOptions = Seq(
    "-deprecation", // Emit warning and location for usages of deprecated APIs.
    "-encoding",
    "utf-8", // Specify character encoding used by source files.
    "-explaintypes", // Explain type errors in more detail.
    "-feature", // Emit warning and location for usages of features that should be imported explicitly.
    "-language:existentials", // Existential types (besides wildcard types) can be written and inferred
    "-language:experimental.macros", // Allow macro definition (besides implementation and application)
    "-language:higherKinds", // Allow higher-kinded types
    "-language:implicitConversions", // Allow definition of implicit functions called views
    "-unchecked", // Enable additional warnings where generated code depends on assumptions.
    "-Xcheckinit", // Wrap field accessors to throw  an exception on uninitialized access.
    "-Xfatal-warnings", // Fail the compilation if there are any warnings.
    "-Xfuture", // Turn on future language features.
    "-Xlint:adapted-args", // Warn if an argument list is modified to match the receiver.
    "-Xlint:constant", // Evaluation of a constant arithmetic expression results in an error.
    "-Xlint:delayedinit-select", // Selecting member of DelayedInit.
    "-Xlint:doc-detached", // A Scaladoc comment appears to be detached from its element.
    "-Xlint:inaccessible", // Warn about inaccessible types in method signatures.
    "-Xlint:infer-any", // Warn when a type argument is inferred to be `Any`.
    "-Xlint:missing-interpolator", // A string literal appears to be missing an interpolator id.
    "-Xlint:nullary-override", // Warn when non-nullary `def f()' overrides nullary `def f'.
    "-Xlint:nullary-unit", // Warn when nullary methods return Unit.
    "-Xlint:option-implicit", // Option.apply used implicit view.
    "-Xlint:package-object-classes", // Class or object defined in package object.
    "-Xlint:poly-implicit-overload", // Parameterized overloaded implicit methods are not visible as view bounds.
    "-Xlint:private-shadow", // A private field (or class parameter) shadows a superclass field.
    "-Xlint:stars-align", // Pattern sequence wildcard must align with sequence component.
    "-Xlint:type-parameter-shadow", // A local type parameter shadows a type already in scope.
    "-Yrangepos",
    "-Ywarn-dead-code", // Warn when dead code is identified.
    "-Ywarn-extra-implicit", // Warn when more than one implicit parameter section is defined.
    "-Ywarn-numeric-widen", // Warn when numerics are widened.
    "-Ywarn-unused:implicits", // Warn if an implicit parameter is unused.
    "-Ywarn-unused:imports", // Warn if an import selector is not referenced.
    "-Ywarn-unused:locals", // Warn if a local definition is unused.
    "-Ywarn-unused:params", // Warn if a value parameter is unused.
    "-Ywarn-unused:patvars", // Warn if a variable bound in a pattern is unused.
    "-Ywarn-unused:privates", // Warn if a private member is unused.
    "-Ywarn-value-discard" // Warn when non-Unit expression results are unused.
  )

  def crossScalacOptions(version: String): Seq[String] =
    CrossVersion.partialVersion(version) match {
      case Some((2L, 12L)) =>
        Seq(
          "-Ypartial-unification",
          "-Yno-adapted-args", // Do not adapt an argument list (either by inserting () or creating a tuple) to match the receiver.
          "-opt-warnings",
          "-opt:l:inline",
          "-opt-inline-from:<source>"
        )
      case Some((2L, 11L)) =>
        Seq(
          "-Ypartial-unification",
          "-Xexperimental",
          "-Yno-adapted-args"
        )
      case _ => Seq()

    }

  val compilerPlugins = Seq(
    compilerPlugin("org.spire-math"         %% "kind-projector"  % "0.9.7"),
    compilerPlugin("com.github.tomasmikula" %% "pascal"          % "0.2.1"),
    compilerPlugin("com.github.ghik"        %% "silencer-plugin" % silencerVersion)
  )
}
