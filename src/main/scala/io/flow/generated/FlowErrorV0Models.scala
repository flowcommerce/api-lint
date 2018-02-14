/**
 * Generated by API Builder - https://www.apibuilder.io
 * Service version: 0.4.84
 * apibuilder 0.14.3 app.apibuilder.io/flow/error/0.4.84/play_2_x_standalone_json
 */
package io.flow.error.v0.models {

  /**
   * An error of some type has occured. The most common error will be validation on
   * input. See messages for details.
   * 
   * @param code Generic errors will always have a code set to 'generic_error'
   */
  case class GenericError(
    code: io.flow.error.v0.models.GenericErrorCode = io.flow.error.v0.models.GenericErrorCode.GenericError,
    messages: Seq[String]
  )

  sealed trait GenericErrorCode extends _root_.scala.Product with _root_.scala.Serializable

  object GenericErrorCode {

    /**
     * Generic errors are the default type. The accompanying message will provide
     * details on the failure.
     */
    case object GenericError extends GenericErrorCode { override def toString = "generic_error" }
    /**
     * A client error has occurred. This represents a misconfiguration of the client
     */
    case object ClientError extends GenericErrorCode { override def toString = "client_error" }
    /**
     * A server error has occurred. The Flow tech team is automatically notified of all
     * server errors
     */
    case object ServerError extends GenericErrorCode { override def toString = "server_error" }

    /**
     * UNDEFINED captures values that are sent either in error or
     * that were added by the server after this library was
     * generated. We want to make it easy and obvious for users of
     * this library to handle this case gracefully.
     *
     * We use all CAPS for the variable name to avoid collisions
     * with the camel cased values above.
     */
    case class UNDEFINED(override val toString: String) extends GenericErrorCode

    /**
     * all returns a list of all the valid, known values. We use
     * lower case to avoid collisions with the camel cased values
     * above.
     */
    val all: scala.List[GenericErrorCode] = scala.List(GenericError, ClientError, ServerError)

    private[this]
    val byName: Map[String, GenericErrorCode] = all.map(x => x.toString.toLowerCase -> x).toMap

    def apply(value: String): GenericErrorCode = fromString(value).getOrElse(UNDEFINED(value))

    def fromString(value: String): _root_.scala.Option[GenericErrorCode] = byName.get(value.toLowerCase)

  }

}

package io.flow.error.v0.models {

  package object json {
    import play.api.libs.json.__
    import play.api.libs.json.JsString
    import play.api.libs.json.Writes
    import play.api.libs.functional.syntax._
    import io.flow.error.v0.models.json._

    private[v0] implicit val jsonReadsUUID = __.read[String].map(java.util.UUID.fromString)

    private[v0] implicit val jsonWritesUUID = new Writes[java.util.UUID] {
      def writes(x: java.util.UUID) = JsString(x.toString)
    }

    private[v0] implicit val jsonReadsJodaDateTime = __.read[String].map { str =>
      import org.joda.time.format.ISODateTimeFormat.dateTimeParser
      dateTimeParser.parseDateTime(str)
    }

    private[v0] implicit val jsonWritesJodaDateTime = new Writes[org.joda.time.DateTime] {
      def writes(x: org.joda.time.DateTime) = {
        import org.joda.time.format.ISODateTimeFormat.dateTime
        val str = dateTime.print(x)
        JsString(str)
      }
    }

    private[v0] implicit val jsonReadsJodaLocalDate = __.read[String].map { str =>
      import org.joda.time.format.ISODateTimeFormat.dateParser
      dateParser.parseLocalDate(str)
    }

    private[v0] implicit val jsonWritesJodaLocalDate = new Writes[org.joda.time.LocalDate] {
      def writes(x: org.joda.time.LocalDate) = {
        import org.joda.time.format.ISODateTimeFormat.date
        val str = date.print(x)
        JsString(str)
      }
    }

    implicit val jsonReadsErrorGenericErrorCode = new play.api.libs.json.Reads[io.flow.error.v0.models.GenericErrorCode] {
      def reads(js: play.api.libs.json.JsValue): play.api.libs.json.JsResult[io.flow.error.v0.models.GenericErrorCode] = {
        js match {
          case v: play.api.libs.json.JsString => play.api.libs.json.JsSuccess(io.flow.error.v0.models.GenericErrorCode(v.value))
          case _ => {
            (js \ "value").validate[String] match {
              case play.api.libs.json.JsSuccess(v, _) => play.api.libs.json.JsSuccess(io.flow.error.v0.models.GenericErrorCode(v))
              case err: play.api.libs.json.JsError => err
            }
          }
        }
      }
    }

    def jsonWritesErrorGenericErrorCode(obj: io.flow.error.v0.models.GenericErrorCode) = {
      play.api.libs.json.JsString(obj.toString)
    }

    def jsObjectGenericErrorCode(obj: io.flow.error.v0.models.GenericErrorCode) = {
      play.api.libs.json.Json.obj("value" -> play.api.libs.json.JsString(obj.toString))
    }

    implicit def jsonWritesErrorGenericErrorCode: play.api.libs.json.Writes[GenericErrorCode] = {
      new play.api.libs.json.Writes[io.flow.error.v0.models.GenericErrorCode] {
        def writes(obj: io.flow.error.v0.models.GenericErrorCode) = {
          jsonWritesErrorGenericErrorCode(obj)
        }
      }
    }

    implicit def jsonReadsErrorGenericError: play.api.libs.json.Reads[GenericError] = {
      (
        (__ \ "code").read[io.flow.error.v0.models.GenericErrorCode] and
        (__ \ "messages").read[Seq[String]]
      )(GenericError.apply _)
    }

    def jsObjectGenericError(obj: io.flow.error.v0.models.GenericError): play.api.libs.json.JsObject = {
      play.api.libs.json.Json.obj(
        "code" -> play.api.libs.json.JsString(obj.code.toString),
        "messages" -> play.api.libs.json.Json.toJson(obj.messages)
      )
    }

    implicit def jsonWritesErrorGenericError: play.api.libs.json.Writes[GenericError] = {
      new play.api.libs.json.Writes[io.flow.error.v0.models.GenericError] {
        def writes(obj: io.flow.error.v0.models.GenericError) = {
          jsObjectGenericError(obj)
        }
      }
    }
  }
}

