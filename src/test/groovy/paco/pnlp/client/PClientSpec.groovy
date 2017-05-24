package paco.pnlp.client

import spock.lang.Specification

class PClientSpec extends Specification {

  static final String ADDRESS = 'localhost'

  void 'tokenize a given text'() {
    given: 'a client instance'
    def client = P.from(ADDRESS)

    expect: 'to get the expected tokens'
    client.tokenize(sentence).size() == expectedTokens

    where: 'possible sentences are'
    expectedTokens | sentence
    3 | "this is Sparta"
    4 | "I told you so"
    6 | "Do not think about it pal"
  }
}
