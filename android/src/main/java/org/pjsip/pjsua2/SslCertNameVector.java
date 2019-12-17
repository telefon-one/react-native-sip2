/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 3.0.7
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package org.pjsip.pjsua2;

public class SslCertNameVector {
  private transient long swigCPtr;
  protected transient boolean swigCMemOwn;

  protected SslCertNameVector(long cPtr, boolean cMemoryOwn) {
    swigCMemOwn = cMemoryOwn;
    swigCPtr = cPtr;
  }

  protected static long getCPtr(SslCertNameVector obj) {
    return (obj == null) ? 0 : obj.swigCPtr;
  }

  protected void finalize() {
    delete();
  }

  public synchronized void delete() {
    if (swigCPtr != 0) {
      if (swigCMemOwn) {
        swigCMemOwn = false;
        pjsua2JNI.delete_SslCertNameVector(swigCPtr);
      }
      swigCPtr = 0;
    }
  }

  public SslCertNameVector() {
    this(pjsua2JNI.new_SslCertNameVector__SWIG_0(), true);
  }

  public SslCertNameVector(long n) {
    this(pjsua2JNI.new_SslCertNameVector__SWIG_1(n), true);
  }

  public long size() {
    return pjsua2JNI.SslCertNameVector_size(swigCPtr, this);
  }

  public long capacity() {
    return pjsua2JNI.SslCertNameVector_capacity(swigCPtr, this);
  }

  public void reserve(long n) {
    pjsua2JNI.SslCertNameVector_reserve(swigCPtr, this, n);
  }

  public boolean isEmpty() {
    return pjsua2JNI.SslCertNameVector_isEmpty(swigCPtr, this);
  }

  public void clear() {
    pjsua2JNI.SslCertNameVector_clear(swigCPtr, this);
  }

  public void add(SslCertName x) {
    pjsua2JNI.SslCertNameVector_add(swigCPtr, this, SslCertName.getCPtr(x), x);
  }

  public SslCertName get(int i) {
    return new SslCertName(pjsua2JNI.SslCertNameVector_get(swigCPtr, this, i), false);
  }

  public void set(int i, SslCertName val) {
    pjsua2JNI.SslCertNameVector_set(swigCPtr, this, i, SslCertName.getCPtr(val), val);
  }

}
