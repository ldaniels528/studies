val appVersion = "0.0.1"
val log4jVersion = "1.2.17"
val scalaVersion_2_13 = "2.13.10"
val scalaAppVersion = scalaVersion_2_13
val scalaTestVersion = "3.3.0-SNAP3"
val slf4jVersion = "2.0.5"

lazy val testDependencies = Seq(
    libraryDependencies ++= Seq(
        "log4j" % "log4j" % log4jVersion,
        "org.slf4j" % "slf4j-api" % slf4jVersion,
        "org.slf4j" % "slf4j-log4j12" % slf4jVersion,
        "org.scalatest" %% "scalatest" % scalaTestVersion % Test
    ))

/////////////////////////////////////////////////////////////////////////////////
//      Root Project - builds all artifacts
/////////////////////////////////////////////////////////////////////////////////

lazy val root = (project in file(".")).
  settings(testDependencies: _*).
  settings(
    name := "studies",
    organization := "com.github.ldaniels828.studies",
    description := "Studies in Computer Science",
    version := appVersion,
    scalaVersion := scalaAppVersion,
    Compile / console / scalacOptions ++= Seq("-deprecation", "-unchecked", "-feature", "-language:implicitConversions", "-Xlint"),
    Compile / doc / scalacOptions += "-no-link-warnings",
    autoCompilerPlugins := true
  )