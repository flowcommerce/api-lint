/**
 * Generated by API Builder - https://www.apibuilder.io
 * Service version: 0.14.85
 * apibuilder 0.14.96 app.apibuilder.io/apicollective/apibuilder-common/latest/play_2_x_standalone_json
 */
package io.apibuilder.common.v0.models {

  final case class Audit(
    createdAt: _root_.org.joda.time.DateTime,
    createdBy: io.apibuilder.common.v0.models.ReferenceGuid,
    updatedAt: _root_.org.joda.time.DateTime,
    updatedBy: io.apibuilder.common.v0.models.ReferenceGuid
  )

  final case class Healthcheck(
    status: String
  )

  /**
   * Represents a reference to another model.
   */
  final case class Reference(
    guid: _root_.java.util.UUID,
    key: String
  )

  final case class ReferenceGuid(
    guid: _root_.java.util.UUID
  )

}

package io.apibuilder.common.v0.models {

  package object json {
    import play.api.libs.json.__
    import play.api.libs.json.JsString
    import play.api.libs.json.Writes
    import play.api.libs.functional.syntax._
    import io.apibuilder.common.v0.models.json._

    private[v0] implicit val jsonReadsUUID = __.read[String].map { str =>
      _root_.java.util.UUID.fromString(str)
    }

    private[v0] implicit val jsonWritesUUID = new Writes[_root_.java.util.UUID] {
      def writes(x: _root_.java.util.UUID) = JsString(x.toString)
    }

    private[v0] implicit val jsonReadsJodaDateTime = __.read[String].map { str =>
      _root_.org.joda.time.format.ISODateTimeFormat.dateTimeParser.parseDateTime(str)
    }

    private[v0] implicit val jsonWritesJodaDateTime = new Writes[_root_.org.joda.time.DateTime] {
      def writes(x: _root_.org.joda.time.DateTime) = {
        JsString(_root_.org.joda.time.format.ISODateTimeFormat.dateTime.print(x))
      }
    }

    private[v0] implicit val jsonReadsJodaLocalDate = __.read[String].map { str =>
      _root_.org.joda.time.format.ISODateTimeFormat.dateTimeParser.parseLocalDate(str)
    }

    private[v0] implicit val jsonWritesJodaLocalDate = new Writes[_root_.org.joda.time.LocalDate] {
      def writes(x: _root_.org.joda.time.LocalDate) = {
        JsString(_root_.org.joda.time.format.ISODateTimeFormat.date.print(x))
      }
    }

    implicit def jsonReadsApibuilderCommonAudit: play.api.libs.json.Reads[Audit] = {
      for {
        createdAt <- (__ \ "created_at").read[_root_.org.joda.time.DateTime]
        createdBy <- (__ \ "created_by").read[io.apibuilder.common.v0.models.ReferenceGuid]
        updatedAt <- (__ \ "updated_at").read[_root_.org.joda.time.DateTime]
        updatedBy <- (__ \ "updated_by").read[io.apibuilder.common.v0.models.ReferenceGuid]
      } yield Audit(createdAt, createdBy, updatedAt, updatedBy)
    }

    def jsObjectAudit(obj: io.apibuilder.common.v0.models.Audit): play.api.libs.json.JsObject = {
      play.api.libs.json.Json.obj(
        "created_at" -> play.api.libs.json.JsString(_root_.org.joda.time.format.ISODateTimeFormat.dateTime.print(obj.createdAt)),
        "created_by" -> jsObjectReferenceGuid(obj.createdBy),
        "updated_at" -> play.api.libs.json.JsString(_root_.org.joda.time.format.ISODateTimeFormat.dateTime.print(obj.updatedAt)),
        "updated_by" -> jsObjectReferenceGuid(obj.updatedBy)
      )
    }

    implicit def jsonWritesApibuilderCommonAudit: play.api.libs.json.Writes[Audit] = {
      new play.api.libs.json.Writes[io.apibuilder.common.v0.models.Audit] {
        def writes(obj: io.apibuilder.common.v0.models.Audit) = {
          jsObjectAudit(obj)
        }
      }
    }

    implicit def jsonReadsApibuilderCommonHealthcheck: play.api.libs.json.Reads[Healthcheck] = {
      (__ \ "status").read[String].map { x => new Healthcheck(status = x) }
    }

    def jsObjectHealthcheck(obj: io.apibuilder.common.v0.models.Healthcheck): play.api.libs.json.JsObject = {
      play.api.libs.json.Json.obj(
        "status" -> play.api.libs.json.JsString(obj.status)
      )
    }

    implicit def jsonWritesApibuilderCommonHealthcheck: play.api.libs.json.Writes[Healthcheck] = {
      new play.api.libs.json.Writes[io.apibuilder.common.v0.models.Healthcheck] {
        def writes(obj: io.apibuilder.common.v0.models.Healthcheck) = {
          jsObjectHealthcheck(obj)
        }
      }
    }

    implicit def jsonReadsApibuilderCommonReference: play.api.libs.json.Reads[Reference] = {
      for {
        guid <- (__ \ "guid").read[_root_.java.util.UUID]
        key <- (__ \ "key").read[String]
      } yield Reference(guid, key)
    }

    def jsObjectReference(obj: io.apibuilder.common.v0.models.Reference): play.api.libs.json.JsObject = {
      play.api.libs.json.Json.obj(
        "guid" -> play.api.libs.json.JsString(obj.guid.toString),
        "key" -> play.api.libs.json.JsString(obj.key)
      )
    }

    implicit def jsonWritesApibuilderCommonReference: play.api.libs.json.Writes[Reference] = {
      new play.api.libs.json.Writes[io.apibuilder.common.v0.models.Reference] {
        def writes(obj: io.apibuilder.common.v0.models.Reference) = {
          jsObjectReference(obj)
        }
      }
    }

    implicit def jsonReadsApibuilderCommonReferenceGuid: play.api.libs.json.Reads[ReferenceGuid] = {
      (__ \ "guid").read[_root_.java.util.UUID].map { x => new ReferenceGuid(guid = x) }
    }

    def jsObjectReferenceGuid(obj: io.apibuilder.common.v0.models.ReferenceGuid): play.api.libs.json.JsObject = {
      play.api.libs.json.Json.obj(
        "guid" -> play.api.libs.json.JsString(obj.guid.toString)
      )
    }

    implicit def jsonWritesApibuilderCommonReferenceGuid: play.api.libs.json.Writes[ReferenceGuid] = {
      new play.api.libs.json.Writes[io.apibuilder.common.v0.models.ReferenceGuid] {
        def writes(obj: io.apibuilder.common.v0.models.ReferenceGuid) = {
          jsObjectReferenceGuid(obj)
        }
      }
    }
  }
}

