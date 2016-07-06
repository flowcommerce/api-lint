/**
 * Generated by apidoc - http://www.apidoc.me
 * Service version: 0.11.26
 * apidoc:0.11.29 http://www.apidoc.me/bryzek/apidoc-common/0.11.26/play_2_x_standalone_json
 */
package com.bryzek.apidoc.common.v0.models {

  case class Audit(
    createdAt: _root_.org.joda.time.DateTime,
    createdBy: com.bryzek.apidoc.common.v0.models.ReferenceGuid,
    updatedAt: _root_.org.joda.time.DateTime,
    updatedBy: com.bryzek.apidoc.common.v0.models.ReferenceGuid
  )

  case class Healthcheck(
    status: String
  )

  /**
   * Represents a reference to another model.
   */
  case class Reference(
    guid: _root_.java.util.UUID,
    key: String
  )

  case class ReferenceGuid(
    guid: _root_.java.util.UUID
  )

}

package com.bryzek.apidoc.common.v0.models {

  package object json {
    import play.api.libs.json.__
    import play.api.libs.json.JsString
    import play.api.libs.json.Writes
    import play.api.libs.functional.syntax._
    import com.bryzek.apidoc.common.v0.models.json._

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

    implicit def jsonReadsApidoccommonAudit: play.api.libs.json.Reads[Audit] = {
      (
        (__ \ "created_at").read[_root_.org.joda.time.DateTime] and
        (__ \ "created_by").read[com.bryzek.apidoc.common.v0.models.ReferenceGuid] and
        (__ \ "updated_at").read[_root_.org.joda.time.DateTime] and
        (__ \ "updated_by").read[com.bryzek.apidoc.common.v0.models.ReferenceGuid]
      )(Audit.apply _)
    }

    def jsObjectAudit(obj: com.bryzek.apidoc.common.v0.models.Audit) = {
      play.api.libs.json.Json.obj(
        "created_at" -> play.api.libs.json.JsString(_root_.org.joda.time.format.ISODateTimeFormat.dateTime.print(obj.createdAt)),
        "created_by" -> jsObjectReferenceGuid(obj.createdBy),
        "updated_at" -> play.api.libs.json.JsString(_root_.org.joda.time.format.ISODateTimeFormat.dateTime.print(obj.updatedAt)),
        "updated_by" -> jsObjectReferenceGuid(obj.updatedBy)
      )
    }

    implicit def jsonWritesApidoccommonAudit: play.api.libs.json.Writes[Audit] = {
      new play.api.libs.json.Writes[com.bryzek.apidoc.common.v0.models.Audit] {
        def writes(obj: com.bryzek.apidoc.common.v0.models.Audit) = {
          jsObjectAudit(obj)
        }
      }
    }

    implicit def jsonReadsApidoccommonHealthcheck: play.api.libs.json.Reads[Healthcheck] = {
      (__ \ "status").read[String].map { x => new Healthcheck(status = x) }
    }

    def jsObjectHealthcheck(obj: com.bryzek.apidoc.common.v0.models.Healthcheck) = {
      play.api.libs.json.Json.obj(
        "status" -> play.api.libs.json.JsString(obj.status)
      )
    }

    implicit def jsonWritesApidoccommonHealthcheck: play.api.libs.json.Writes[Healthcheck] = {
      new play.api.libs.json.Writes[com.bryzek.apidoc.common.v0.models.Healthcheck] {
        def writes(obj: com.bryzek.apidoc.common.v0.models.Healthcheck) = {
          jsObjectHealthcheck(obj)
        }
      }
    }

    implicit def jsonReadsApidoccommonReference: play.api.libs.json.Reads[Reference] = {
      (
        (__ \ "guid").read[_root_.java.util.UUID] and
        (__ \ "key").read[String]
      )(Reference.apply _)
    }

    def jsObjectReference(obj: com.bryzek.apidoc.common.v0.models.Reference) = {
      play.api.libs.json.Json.obj(
        "guid" -> play.api.libs.json.JsString(obj.guid.toString),
        "key" -> play.api.libs.json.JsString(obj.key)
      )
    }

    implicit def jsonWritesApidoccommonReference: play.api.libs.json.Writes[Reference] = {
      new play.api.libs.json.Writes[com.bryzek.apidoc.common.v0.models.Reference] {
        def writes(obj: com.bryzek.apidoc.common.v0.models.Reference) = {
          jsObjectReference(obj)
        }
      }
    }

    implicit def jsonReadsApidoccommonReferenceGuid: play.api.libs.json.Reads[ReferenceGuid] = {
      (__ \ "guid").read[_root_.java.util.UUID].map { x => new ReferenceGuid(guid = x) }
    }

    def jsObjectReferenceGuid(obj: com.bryzek.apidoc.common.v0.models.ReferenceGuid) = {
      play.api.libs.json.Json.obj(
        "guid" -> play.api.libs.json.JsString(obj.guid.toString)
      )
    }

    implicit def jsonWritesApidoccommonReferenceGuid: play.api.libs.json.Writes[ReferenceGuid] = {
      new play.api.libs.json.Writes[com.bryzek.apidoc.common.v0.models.ReferenceGuid] {
        def writes(obj: com.bryzek.apidoc.common.v0.models.ReferenceGuid) = {
          jsObjectReferenceGuid(obj)
        }
      }
    }
  }
}

