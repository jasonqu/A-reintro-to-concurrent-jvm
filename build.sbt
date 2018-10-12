lazy val concurrent_jvm = (project in file("."))
  .settings(
    name := "concurrent-jvm",
    organization := "org.bitbucket.qiuguo0205",
    version := "0.0.1-SNAPSHOT",
    scalaVersion := "2.12.2",
    libraryDependencies ++= {
      val akkaVersion = "2.5.3"
      Seq(
        // akka
        "com.typesafe.akka" %% "akka-actor" % akkaVersion withSources(),
        "com.typesafe.akka" %% "akka-slf4j" % akkaVersion withSources(),
        "com.typesafe.akka" %% "akka-stream" % akkaVersion withSources(),

        // test
        "com.typesafe.akka" %% "akka-testkit" % akkaVersion % Test,
        "com.typesafe.akka" %% "akka-stream-testkit" % akkaVersion % Test,
        "org.scalatest" %% "scalatest" % "3.0.1" % Test
      )
    }
  )
