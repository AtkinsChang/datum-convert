lazy val default = Seq(
  organization := "edu.nccu.plsm.geo",
  scalaVersion := "2.11.5",
  scalacOptions ++= Seq(
    "-unchecked",
    "-deprecation",
    "-language:_",
    "-target:jvm-1.8",
    "-explaintypes",
    "-feature",
    "-optimise",
    "-encoding", "UTF-8",
    "-Yinline-warnings"
  ),
  javacOptions ++= Seq(
    "-source", "1.8",
    "-target", "1.8",
    "-encoding", "UTF-8",
    "-Xlint:-options",
    "-J-Xmx512m"
  ),
  javacOptions in doc := Seq("-source", "1.8"),
  fork in Test := true
)

lazy val root = (project in file("."))
  .settings(default: _*)
  .settings(
    name := "datum-convert",
    libraryDependencies += "org.specs2" %% "specs2-core" % "2.4.16" % "test",
    publishTo <<= version { version: String =>
      val repository = "http://140.119.163.67:28080/repository/"
      val (name, url) = if (version.contains("-SNAPSHOT"))
        ("libs-snapshot-local", repository+"libs-snapshot-local")
      else
        ("libs-release-local", repository+"libs-release-local")
      Some(Resolver.url(name, new URL(url))(Resolver.mavenStylePatterns))
    }
  )















