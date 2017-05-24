package paco.pnlp.client

import static groovyx.net.http.HttpBuilder.configure

import groovyx.net.http.NativeHandlers
import groovyx.net.http.ChainedHttpConfig
import groovyx.net.http.FromServer

/**
 * Entrypoint to access the pnlp service
 *
 * @since 0.1.0
 */
class P {

  static final String APPLICATION_JSON = 'application/json'

  /**
   * Builds an instance of {@link PClient} from a given server name/ip
   *
   * @param address the server address: ip or server name
   * @return an instance of {@link PClient}
   * @since 0.1.0
   */
  static PClient from(final String address) {
    def config = configure {
      request.uri = "http://$address:5050"
      request.contentType = APPLICATION_JSON
      response.parser(APPLICATION_JSON, P.&toJSON)
    }

    return new PClient(config)
  }

  private static <U> U toJSON (ChainedHttpConfig config, FromServer resp) {
    return (U) NativeHandlers
      .Parsers
      .json(config, resp)
  }
}
