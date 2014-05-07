import java.io._
import scala.io.Source
import java.security.MessageDigest

object Main {
  def main(args:Array[String]):Unit = {
    Parser.parser.parse(args, Configuration()) map { config =>
      //do things
    } getOrElse {
      println("Some error :(")
    }

    // val tsv = args(0)
    // val decimals = args(1).toInt
    // val outputFileName = s"${tsv}_vw_${decimals}_decimals"
    // Source.fromFile(tsv).getLines.foreach(line => {
    //   val formatted = formatLine(line, decimals)
    //   writeLine(formatted, outputFileName)
    // })
  }

  def writeLine(line:String, file:String):Unit = {
    val out = new File(file)
    if(!out.exists) out.createNewFile
    val writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(out, true)))
    try {
      writer.write(line)
    } finally {
      writer.flush()
      writer.close()
    }
  }

  def getClass(score:Double):Int = {
    if(score < 0.0) 0 else 1
  }

  def trunkCoordinate(coordinate:String, decimals:Int):String = {
    val parts = coordinate.split("\\.")
    parts(0) + "." + parts(1).slice(0, decimals)
  }

  def formatLine(line:String, decimals:Int):String = {
    val columns = line.split("\t")
    val lat = trunkCoordinate(columns(1), decimals)
    val lng = trunkCoordinate(columns(2), decimals)

    val key = lat + ", " + lng
    val md5 = getMD5(key)

    md5 + " |Tweet " + columns(3) + "\n"
  }

  def getMD5(key:String):String = MessageDigest.getInstance("MD5").digest(key.getBytes).map("%02x".format(_)).mkString
}
