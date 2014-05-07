import java.io.File

case class Configuration(inout:Boolean = false, decimals:Int = -1, file:File = null) {}

object Parser {
  val parser = new scopt.OptionParser[Configuration]("vw-input-translator") {
    head("vw-input-translator", "1.0")
    opt[Boolean]("inout") action { (x, c) => c.copy(inout = x) } text("Creates an input file for binary classification")
    opt[Int]('d', "decimals") action { (x, c) => c.copy(decimals = x) } validate { x =>
      if (x > 0) success else failure("Option --decimals must be > 0") } text("Creates an input file for multi-class classification." +
        " Each sample will have the selected number of decimals on latitude and longitude coordinates")
    arg[File]("<file>") unbounded() action { (x, c) => c.copy(file = x) } text("Source file")
    checkConfig { c => if (c.inout && c.decimals > -1) failure("You cannot use inout and decimals at the same time :/") else success }
  }
}
