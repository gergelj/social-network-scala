name := """project-social-network"""
organization := "com.example"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.13.3"

libraryDependencies += guice
libraryDependencies += "org.scalatestplus.play" %% "scalatestplus-play" % "5.0.0" % Test

libraryDependencies ++= Seq(
    "com.typesafe.play" %% "play-slick" % "5.0.0",
    "com.typesafe.slick" %% "slick-codegen" % "3.3.2",
    "com.typesafe.play" %% "play-json" % "2.8.1",
    //"com.typesafe.slick" %% "slick-hikaricp" % "3.3.2",
    //"com.typesafe.play" %% "play-slick-evolutions" % "4.0.0",
    "org.mindrot" % "jbcrypt" % "0.4",
    "mysql" % "mysql-connector-java" % "8.0.15"
)

// Adds additional packages into Twirl
//TwirlKeys.templateImports += "com.example.controllers._"

// Adds additional packages into conf/routes
// play.sbt.routes.RoutesKeys.routesImport += "com.example.binders._"
