package paco.pnlp.client.domain

/**
 * Represents a name found in a given text
 *
 * @since 0.1.0
 */
class Name {

  /**
   * The name found
   *
   * @since 0.1.0
   */
  String name

  /**
   * The probability to be a name
   *
   * @since 0.1.0
   */
  Double probability

  /**
   * Where the name starts in the text
   *
   * @since 0.1.0
   */
  Integer start

  /**
   * Where the name ends in the text
   *
   * @since 0.1.0
   */
  Integer end
}
