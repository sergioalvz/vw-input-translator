name := "vw-input-translator"

version := "1.0"

scalaVersion := "2.11.0"

mainClass := Some("Main")

libraryDependencies += "org.scalatest" % "scalatest_2.11" % "2.1.5" % "test"

libraryDependencies += "com.github.scopt" %% "scopt" % "3.2.0"

resolvers += Resolver.sonatypeRepo("public")
