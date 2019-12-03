// shadow sbt-scalajs' crossProject and CrossType from Scala.js 0.6.x
import sbtcrossproject.CrossPlugin.autoImport.{ crossProject, CrossType }

lazy val commonSettings = Seq(
  organization := "com.github.fdietze",
  version := "master-SNAPSHOT",

  scalaVersion := crossScalaVersions.value.last,
  crossScalaVersions := Seq("2.11.12", "2.12.10", "2.13.1"),

  resolvers ++= (
    ("jitpack" at "https://jitpack.io") ::
    Nil
  ),

  scalacOptions ++=
    "-encoding" :: "UTF-8" ::
    "-unchecked" ::
    "-deprecation" ::
    "-explaintypes" ::
    "-feature" ::
    "-language:_" ::
    "-Xlint" ::
    Nil,

  scalacOptions ++= {
    CrossVersion.partialVersion(scalaVersion.value) match {
      case Some((2, 12)) =>
        "-Ywarn-extra-implicit" ::
          "-Ypartial-unification" ::
          "-Yno-adapted-args" ::
          "-Ywarn-infer-any" ::
          "-Ywarn-value-discard" ::
          "-Ywarn-nullary-override" ::
          "-Ywarn-nullary-unit" ::
          Nil
      case _ =>
        Nil
    }
  },

  initialCommands in console := """
  import chopchop._
  """,
)


lazy val chopchop = crossProject(JSPlatform, JVMPlatform).crossType(CrossType.Full)
  .enablePlugins(ScalaJSPlugin, ScalaJSBundlerPlugin)
  .settings(commonSettings)
  .settings(
    name := "chopchop",

    libraryDependencies ++=
      Deps.scalaTest.value % Test ::
      Nil
  )
  .jsSettings(
    useYarn := true,
    npmDependencies in Compile ++= (
      Deps.Npm.pako.value ::
      Nil
    ),
    scalacOptions ++= git.gitHeadCommit.value.map { headCommit =>
      val local = baseDirectory.value.toURI
      val remote = s"https://raw.githubusercontent.com/fdietze/chopchop/${headCommit}/"
      s"-P:scalajs:mapSourceURI:$local->$remote"
    }
  )
