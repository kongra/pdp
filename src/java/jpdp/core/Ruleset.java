/*
 * Copyright (c) Konrad Grzanek. All rights reserved.
 * Created 18 lis 2015
 */
package jpdp.core;

public final class Ruleset {

  public final Lexicon lexicon;

  public final int n;

  public final int beam;

  public Ruleset(Iterable<Object> propNames, Iterable<Object> valueOrigins,
      int n, int beam) {
    this.lexicon = new Lexicon(propNames, valueOrigins);
    this.n = n;
    this.beam = beam;
  }

}
