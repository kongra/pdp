/*
 * Copyright (c) Konrad Grzanek. All rights reserved.
 * Created 2015-11-18
 */
package jpdp.core;

public final class Prop {

  public final String name;

  public final int code;

  Prop(String name, int code) {
    this.name = name;
    this.code = code;
  }

  @Override
  public String toString() {
    return this.name;
  }

}
