package io.flow.proxy

import com.bryzek.apidoc.spec.v0.models.Service
import io.flow.registry.v0.{Client => RegistryClient}
import Text._

case class Controller() extends io.flow.build.Controller {

  override val name = "Proxy"
  override val command = "proxy"

  def run(
    services: Seq[Service]
  ) (
    implicit ec: scala.concurrent.ExecutionContext
  ) {
    println("Building proxy from: " + services.map(_.name).mkString(", "))

    val registryClient = new RegistryClient()

    try {
      val version = "0.0.40" // todo

      build(services, version, "production") { service =>
        service.baseUrl.getOrElse {
          s"https://${service.name.toLowerCase}.api.flow.io"
        }
      }

      build(services, version, "development") { service =>
        registryClient.applications.getById(service.name).map { app =>
          println(s"${service.name} => APP: $app")
        }.recover {
          case io.flow.registry.v0.errors.UnitResponse(404) => {
            sys.error(s"Service[${service.name}] not found in registry at ${registryClient.baseUrl}")
          }
        }

        s"http://localhost:TODO"
      }
    } finally {
      registryClient.closeAsyncHttpClient()
    }
  }

  private[this] def build(
    services: Seq[Service],
    version: String,
    env: String
  ) (
    hostProvider: Service => String
  ) (
    implicit ec: scala.concurrent.ExecutionContext
  ) {
  
    val servicesYaml = services.map { service =>
      val host = hostProvider(service)
      ProxyBuilder(service, host).yaml()
    }.mkString("\n")

    val all = s"""version: $version
services:
${servicesYaml.indent(2)}
"""

    val path = s"/tmp/api-proxy.$env.config"
    writeToFile(path, all)
    println(s" - $env: $path")
  }

  private[this] def writeToFile(path: String, contents: String) {
    import java.io.{BufferedWriter, File, FileWriter}

    val bw = new BufferedWriter(new FileWriter(new File(path)))
    try {
      bw.write(contents)
    } finally {
      bw.close()
    }
  }
  
}
