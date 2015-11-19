/*
 * Copyright (c) Konrad Grzanek. All rights reserved.
 * Created 2015-11-19
 */
package jpdp.core;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

import clojure.lang.IFn;
import clojure.lang.RT;

public final class Naive {

  public static boolean evalLinked(LinkedList<IFn> rules, Object conf) {
    final Iterator<IFn> it = rules.iterator();
    while (it.hasNext()) {
      final IFn rule = it.next();
      if (!RT.booleanCast(rule.invoke(conf))) {
        return false;
      }
    }
    return true;
  }

  public static boolean evalArray(ArrayList<IFn> rules, Object conf) {
    for (int i = 0, n = rules.size(); i < n; i++) {
      final IFn rule = rules.get(i);
      if (!RT.booleanCast(rule.invoke(conf))) {
        return false;
      }
    }
    return true;
  }

  private Naive() {

  }

}
