/*
 * Copyright (c) Konrad Grzanek. All rights reserved.
 * Created 2015-11-19
 */
package jpdp.core;

public final class Prop {

  public final String name;

  public final Integer code;

  public Prop(String name, Integer code) {
    if (null == name)
      throw new IllegalArgumentException("Name is null.");
    if (null == code)
      throw new IllegalArgumentException("Code is null.");
    this.name = name;
    this.code = code;
  }

  @Override
  public String toString() {
    return this.name;
  }

}
