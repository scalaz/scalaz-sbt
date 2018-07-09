package test

import scala.deprecated
import com.github.ghik.silencer.silent

trait Forall[F[_]] {
  def apply[A]: F[A]
}

abstract class Test {

  // this should compile iff kind-projector is activated
  def foo[F[_]]: Unit

  val l = foo[Map[String, ?]]

  // this should compile iff pascal is activated
  type ~>[F[_], G[_]] = Forall[λ[α => F[α] => G[α]]]
  val headOption = ν[List ~> Option](_.headOption)
  @deprecated("don't use that", "0.0")
  def depr(): Unit = ()

  // this should compile iff silencer is activated
  @silent
  def useDeprecatedMethod(): Unit = depr

}
