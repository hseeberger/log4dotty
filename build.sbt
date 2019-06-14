// *****************************************************************************
// Projects
// *****************************************************************************

lazy val `log4dotty-root` =
  project
    .in(file("."))
    .settings(settings)
    .settings(
      Compile / unmanagedSourceDirectories := Seq.empty,
      Test / unmanagedSourceDirectories    := Seq.empty,
      publishArtifact := false
    )
    .aggregate(log4dotty, `log4dotty-demo`)

lazy val log4dotty =
  project
    .enablePlugins(AutomateHeaderPlugin)
    .settings(settings)
    .settings(
      libraryDependencies ++= Seq(
        library.log4jApi,
      )
    )

lazy val `log4dotty-demo` =
  project
    .enablePlugins(AutomateHeaderPlugin)
    .settings(settings)
    .dependsOn(log4dotty)
    .settings(
      libraryDependencies ++= Seq(
        library.log4jCore,
      )
    )

// *****************************************************************************
// Library dependencies
// *****************************************************************************

lazy val library =
  new {
    object Version {
      val log4j      = "2.11.2"
      val scalaCheck = "1.14.0"
      val scalaTest  = "3.0.8"
    }
    val log4jApi   = "org.apache.logging.log4j" %  "log4j-api"  % Version.log4j
    val log4jCore  = "org.apache.logging.log4j" %  "log4j-core" % Version.log4j
    val scalaCheck = "org.scalacheck"           %% "scalacheck" % Version.scalaCheck
    val scalaTest  = "org.scalatest"            %% "scalatest"  % Version.scalaTest
  }

// *****************************************************************************
// Settings
// *****************************************************************************

lazy val settings =
  commonSettings ++
  scalafmtSettings

lazy val commonSettings =
  Seq(
    scalaVersion := "0.16.0-RC3",
    organization := "rocks.heikoseeberger",
    organizationName := "Heiko Seeberger",
    startYear := Some(2019),
    licenses += ("Apache-2.0", url("http://www.apache.org/licenses/LICENSE-2.0")),
    scalacOptions ++= Seq(
      "-unchecked",
      "-deprecation",
      "-language:_",
      "-target:jvm-1.8",
      "-encoding", "UTF-8",
      // "-Ypartial-unification",
      // "-Ywarn-unused-import",
    ),
    Compile / unmanagedSourceDirectories := Seq((Compile / scalaSource).value),
    Test / unmanagedSourceDirectories := Seq((Test / scalaSource).value),
)

lazy val scalafmtSettings =
  Seq(
    scalafmtOnCompile := false,
  )
