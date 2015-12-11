/*
 * Copyright (c) Konrad Grzanek. All rights reserved.
 * Created 2015-11-20
 */
package jpdp.core;

import clojure.lang.IFn;

public final class Rule {

  public final IFn f;

  public Rule(IFn f) {
    this.f = f;
  }

}
