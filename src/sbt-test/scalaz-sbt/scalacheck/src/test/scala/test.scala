package test

import org.scalacheck._
import org.scalacheck.Prop._

class Test extends Properties("test") {
  property("tautology") = forAll { (b: Boolean) =>
    b || true
  }
}
