/*
 * Copyright (c) Konrad Grzanek. All rights reserved.
 * Created 2015-11-19
 */
package jpdp.core;

public class Value {

  public final String repr;

  public Value(Object origin) {
    if (null == origin)
      throw new IllegalArgumentException("Origin is null.");
    this.repr = origin.toString();
  }

  @Override
  public String toString() {
    return this.repr;
  }

}
