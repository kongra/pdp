/*
 * Copyright (c) Konrad Grzanek. All rights reserved.
 * Created 2015-11-19
 */
package jpdp.core;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

import clojure.lang.RT;

public final class Naive {

  public static boolean evalLinked(LinkedList<Rule> rules, Object conf) {
    final Iterator<Rule> it = rules.iterator();
    while (it.hasNext()) {
      final Rule rule = it.next();
      if (!RT.booleanCast(rule.f.invoke(conf))) {
        return false;
      }
    }
    return true;
  }

  public static boolean evalArray(ArrayList<Rule> rules, Object conf) {
    for (int i = 0, n = rules.size(); i < n; i++) {
      final Rule rule = rules.get(i);
      if (!RT.booleanCast(rule.f.invoke(conf))) {
        return false;
      }
    }
    return true;
  }

  private Naive() {

  }

}
