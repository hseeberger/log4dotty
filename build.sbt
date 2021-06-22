// *****************************************************************************
// Projects
// *****************************************************************************

lazy val `log4dotty-root` =
  project
    .in(file("."))
    .settings(commonSettings)
    .settings(
      Compile / unmanagedSourceDirectories := Seq.empty,
      Test / unmanagedSourceDirectories := Seq.empty,
      publishArtifact := false
    )
    .aggregate(log4dotty, `log4dotty-demo`)

lazy val log4dotty =
  project
    .enablePlugins(AutomateHeaderPlugin)
    .settings(commonSettings)
    .settings(
      libraryDependencies ++= Seq(
          library.log4jApi
        )
    )

lazy val `log4dotty-demo` =
  project
    .enablePlugins(AutomateHeaderPlugin)
    .settings(commonSettings)
    .dependsOn(log4dotty)
    .settings(
      libraryDependencies ++= Seq(
          library.log4jCore
        )
    )

// *****************************************************************************
// Library dependencies
// *****************************************************************************

lazy val library =
  new {
    object Version {
      val log4j = "2.13.3"
      val mUnit = "0.7.16"
    }
    val log4jApi        = "org.apache.logging.log4j" % "log4j-api"        % Version.log4j
    val log4jCore       = "org.apache.logging.log4j" % "log4j-core"       % Version.log4j
    val mUnit           = "org.scalameta"           %% "munit"            % Version.mUnit
    val mUnitScalaCheck = "org.scalameta"           %% "munit-scalacheck" % Version.mUnit
  }

// *****************************************************************************
// Settings
// *****************************************************************************

lazy val commonSettings =
  Seq(
    scalaVersion := "3.0.0",
    organization := "rocks.heikoseeberger",
    organizationName := "Heiko Seeberger",
    startYear := Some(2019),
    licenses += ("Apache-2.0", url("http://www.apache.org/licenses/LICENSE-2.0")),
    scalacOptions ++= Seq(
        "-unchecked",
        "-deprecation",
        "-encoding",
        "UTF-8"
      ),
    testFrameworks += new TestFramework("munit.Framework")
    // scalafmtOnCompile := true,
  )
