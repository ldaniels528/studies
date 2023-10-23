package com.github.ldaniels828

package object studies {

  /**
   * Automatically closes a resource after the completion of a code block
   */
  final implicit class AutoClose[T <: AutoCloseable](val resource: T) extends AnyVal {

    @inline
    def use[S](block: T => S): S = try block(resource) finally {
      try resource.close() catch {
        case _: Exception =>
      }
    }

  }

}
