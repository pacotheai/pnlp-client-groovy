package paco.pnlp.client

import groovy.transform.TupleConstructor
import groovyx.net.http.HttpBuilder
import paco.pnlp.client.domain.Name
import paco.pnlp.client.domain.Sentiment

/**
 * Client to access pnlp service endpoints
 *
 * @since 0.1.0
 */
@TupleConstructor
class PClient {

  HttpBuilder httpBuilder

  /**
   * Tokenizes the sentence passed as parameter using the default
   * tokenize model
   *
   * @param sentence the text you want to tokenize
   * @return a list of tokens
   * @since 0.1.0
   */
  List<String> tokenize(String sentence) {
    return listItems('/api/v1/tokenizer/me', sentence)
  }

  /**
   * Finds the sentences found in the text passed as parameter using
   * the default loaded model.
   *
   * @param text the text we want to extract the sentences from
   * @return a list of found sentences
   * @since 0.1.0
   */
  List<String> sentences(String text) {
    return listItems('/api/v1/sentences/me', text)
  }

  /**
   * Finds all names representing a person using the default
   * loaded model
   *
   * @param text the text containing person names
   * @return a list of {@link Name} instances
   * @since 0.1.0
   */
  List<Name> findPersonNames(String text) {
    return listItems('/api/v1/finder/person', text).collect(this.&toName)
  }

  /**
   * Finds all names representing a location using the default
   * loaded model
   *
   * @param text the text containing the locations
   * @return a list of {@link Name} instances
   * @since 0.1.0
   */
  List<Name> findLocationNames(String text) {
    return listItems('/api/v1/finder/location', text).collect(this.&toName)
  }

  private Name toName(Map m) {
    return convert(m, Name)
  }

  private List listItems(String path, String text) {
    return httpBuilder.post(List) {
      request.uri.path = path
      request.body = [text: text]
    }
  }

  /**
   * Returns wether a given text has a positive meaning or not
   *
   * @param text the evaluated text
   * @return an instance of {@link Sentiment}
   * @since 0.1.0
   */
  Sentiment sentiment(String text) {
    Map m = httpBuilder.post(Map) {
      request.uri.path = '/api/v1/sentiment/me'
      request.body = [text: text]
    }

    return convert(m, Sentiment)
  }

  private static <T> T convert(Map m, Class<T> type) {
    return type.newInstance(m)
  }
}
