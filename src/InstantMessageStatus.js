/**
 * This class describes the information and current status of a call.
 */
export default class InstantMessageStatus {
  constructor({ accountId, toUri, code, reason }) {
    this._accountId = accountId;
    this._toUri = toUri;
    this._code = code;
    this._reason = reason;
  }

  /**
   * The account ID where this message belongs.
   * @returns {int}
   */
  getAccountId() {
    return this._accountId;
  }

  /**
   * URI of the destination message.
   * @returns {String}
   */
  getToUri() {
    return this._toUri;
  }

  /**
   * Code.
   * @returns {int}
   */
  getCode() {
    return this._code;
  }

  /**
   * Reason.
   * @returns {string}
   */
  getReason() {
    return this._reason;
  }
}
