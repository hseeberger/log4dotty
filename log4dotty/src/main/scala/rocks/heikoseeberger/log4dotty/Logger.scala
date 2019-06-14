/*
 * Copyright 2019 Heiko Seeberger
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package rocks.heikoseeberger.log4dotty

import org.apache.logging.log4j.{ LogManager, Logger => Underlying }
import scala.quoted.Expr

object Logger {
  def apply(name: String): Logger = 
    new Logger(LogManager.getLogger(name))
}

final class Logger private (underlying: Underlying) {
  inline def debug(message: => String): Unit =
    ${ LoggerMacro.debug('underlying, 'message) }
}

object LoggerMacro {
  def debug(underlying: Expr[Underlying], message: Expr[String]): Expr[Unit] = '{
    if ($underlying.isDebugEnabled) $underlying.debug($message)
  }
}
