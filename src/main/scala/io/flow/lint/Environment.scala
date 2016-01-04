package io.flow.lint

import scala.util.{Failure, Success, Try}

case class ApidocProfile(name: String, apiUrl: String, token: Option[String] = None)

/**
  * Parses the apidoc configuration file
  */
object ApidocConfig {

  private[this] val DefaultApiUrl = "http://api.apidoc.me"
  private[this] val DefaultPath = "~/.apidoc/config"

  /**
    * Loads apidoc configuration from the apidoc configuration file,
    * returning either an error or the configuration. You can set the
    * APIDOC_PROFILE environment variable if you want to parse a
    * specific profile.
    * 
    * @param path The path to the configuration file we are reading
    */
  def load(
    path: String = DefaultPath
  ): Either[String, ApidocProfile] = {
    val profile = Environment.optionalString("APIDOC_PROFILE").getOrElse("default")
    val envToken = Environment.optionalString("APIDOC_API_TOKEN")
    val envUri = Environment.optionalString("APIDOC_API_URI")

    Seq(envToken, envUri).flatten match {
      case Nil => {
        loadAllProfiles(path) match {
          case Left(errors) => Left(errors)
          case Right(profiles) => {
            profiles.find(_.name == profile) match {
              case None => Left(s"apidoc profile named[$profile] not found")
              case Some(p) => Right(p)
            }
          }
        }
      }
      case _ => {
        println("Using profile from env variables")
        Right(
          ApidocProfile(
            name = "environment",
            apiUrl = envUri.getOrElse(DefaultApiUrl),
            token = envToken
          )
        )
      }
    }
  }

  private[this] val Profile = """\[profile (.+)\]""".r
  private[this] val Default = """\[default\]""".r

  private[this] def loadAllProfiles(path: String): Either[String, Seq[ApidocProfile]] = {
    val fullPath = path.replaceFirst("^~", System.getProperty("user.home"))
    var allProfiles = scala.collection.mutable.ListBuffer[ApidocProfile]()

    Try(
      if (new java.io.File(fullPath).exists) {
        var currentProfile: Option[ApidocProfile] = None

        scala.io.Source.fromFile(fullPath).getLines.map(_.trim).foreach { l =>
          l match {
            case Profile(name) => {
              currentProfile.map { p => allProfiles += p }
              currentProfile = Some(ApidocProfile(name = name, apiUrl = DefaultApiUrl))
            }
            case Default() => {
              currentProfile.map { p => allProfiles += p }
              currentProfile = Some(ApidocProfile(name = "default", apiUrl = DefaultApiUrl))
            }
            case _ => {
              l.split("=").map(_.trim).toList match {
                case "token" :: value :: Nil => {
                  currentProfile = currentProfile.map(_.copy(token = Some(value)))
                }
                case "api_uri" :: value :: Nil => {
                  currentProfile = currentProfile.map(_.copy(apiUrl = value))
                }
                case _ => {
                  // ignore
                }
              }
            }
          }
        }

        currentProfile.map { p => allProfiles += p }
      }
    ) match {
      case Success(_) => {
        Right(allProfiles)
      }
      case Failure(ex) => Left(ex.toString)
    }
  }

}

/**
  * Helper to read configuration from environment
  */
object Environment {

  def optionalString(name: String): Option[String] = {
    sys.env.get(name).map(_.trim).map { value =>
      value match {
        case "" => {
          sys.error(s"Value for environment variable[$name] cannot be blank")
        }
        case _ => value
      }
    }
  }

}
