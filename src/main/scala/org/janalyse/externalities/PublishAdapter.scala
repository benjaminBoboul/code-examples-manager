package org.janalyse.externalities

import org.janalyse.{Change, CodeExample}

trait PublishAdapter {
  def migrateGists(examples: List[CodeExample], authToken: AuthToken): List[Change]
  def synchronize(examples:List[CodeExample], authToken: AuthToken):List[Change]
}
