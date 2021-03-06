pomIncludeRepository := { _ => false }

releaseCrossBuild := true
releasePublishArtifactsAction := PgpKeys.publishSigned.value
publishMavenStyle := true
publishArtifact in Test := false
publishTo := Some(if (isSnapshot.value) Opts.resolver.sonatypeSnapshots else Opts.resolver.sonatypeStaging)

PgpKeys.useGpg in Global := true      // workaround with pgp and sbt 1.2.x
pgpSecretRing := pgpPublicRing.value  // workaround with pgp and sbt 1.2.x

pomExtra in Global := {
  <developers>
    <developer>
      <id>dacr</id>
      <name>David Crosson</name>
      <url>https://github.com/dacr</url>
    </developer>
  </developers>
}

releaseTagComment        := s"Releasing ${(version in ThisBuild).value}"
releaseCommitMessage     := s"[ci skip] Setting version to ${(version in ThisBuild).value}"
releaseNextCommitMessage := s"[ci skip] Setting version to ${(version in ThisBuild).value}"

import ReleaseTransformations._
releaseProcess := Seq[ReleaseStep](
    checkSnapshotDependencies,
    inquireVersions,
    //runClean,
    runTest,
    setReleaseVersion,
    commitReleaseVersion,
    tagRelease,
    publishArtifacts,
    setNextVersion,
    commitNextVersion,
    releaseStepCommand("sonatypeReleaseAll"),
    pushChanges
  )
