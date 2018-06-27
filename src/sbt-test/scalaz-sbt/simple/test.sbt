enablePlugins(ScalazPlugin)

name := "simple"

TaskKey[Unit]("check-organization") := {
  assert(organization.value == "org.scalaz", s"Common setting not applied (organization = ${organization.value})")
}
