/*
 * Copyright (c) Konrad Grzanek. All rights reserved.
 * Created 18 lis 2015
 */
package jpdp.core;

public final class Value {

  public final Object origin;

  Value(Object origin) {
    this.origin = origin;
  }

  @Override
  public String toString() {
    return this.origin.toString();
  }

}
