name := """Repaso"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.11.7"

libraryDependencies ++= Seq(
  javaJdbc,
  cache,
  javaWs
)
libraryDependencies += "postgresql" % "postgresql" % "9.1-901.jdbc4"
lazy val myProject = (project in file(".")).enablePlugins(PlayJava, PlayEbean)
