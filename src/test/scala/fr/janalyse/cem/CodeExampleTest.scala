package fr.janalyse.cem

import org.scalatest._,matchers._, OptionValues._, flatspec._
import better.files._

class CodeExampleTest extends AnyFlatSpec with should.Matchers {
  "CodeExample" can "be constructed from a local file" in {
    val ex1 = CodeExample(file"test-data/sample1/fake-testing-pi.sc", file"test-data/sample1")
    ex1.fileExt shouldBe "sc"
    ex1.filename shouldBe "fake-testing-pi.sc"
    ex1.content should include regex "id [:] 8f2e14ba-9856-4500-80ab-3b9ba2234ce2"
  }

  it can "automatically recognize categories from sub-directory name" in {
    CodeExample(file"test-data/sample1/fake-testing-pi.sc", file"test-data/sample1").category shouldBe None
    CodeExample(file"test-data/sample1/fake-testing-pi.sc", file"test-data").category shouldBe Some("sample1")
    CodeExample(file"test-data/sample1/fake-testing-pi.sc", file"").category shouldBe Some("test-data/sample1")
  }
}
