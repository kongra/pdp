/*
 * Copyright (c) Konrad Grzanek. All rights reserved.
 * Created 2015-11-19
 */
package jpdp.core;

public final class Conf {

  private final Value[] values;

  public Conf(int n) {
    values = new Value[n];
  }

  public synchronized Value get(Prop prop) {
    return values[prop.code];
  }

  public synchronized void set(Prop prop, Value value) {
    values[prop.code] = value;
  }
}
