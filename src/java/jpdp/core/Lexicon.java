/*
 * Copyright (c) Konrad Grzanek. All rights reserved.
 * Created 2015-11-18
 */
package jpdp.core;

import java.util.HashMap;
import java.util.Map;

public final class Lexicon {

  public Prop getProp(Object name) {
    return propsCache.get(name.toString());
  }

  public Value getValue(Object origin) {
    return valuesCache.get(origin);
  }

  Lexicon(Iterable<Object> propNames, Iterable<Object> valueOrigins) {
    this.propsCache = makePropsCache(propNames);
    this.valuesCache = makeValuesCache(valueOrigins);
  }

  private final Map<String, Prop> propsCache;

  private final Map<Object, Value> valuesCache;

  private static Map<String, Prop> makePropsCache(Iterable<Object> names) {
    final Map<String, Prop> cache = new HashMap<>();
    int code = 0;
    for (Object name : names) {
      String s = name.toString();
      if (cache.containsKey(s)) {
        throw new IllegalArgumentException("Duplicate property " + s);
      }
      cache.put(s, new Prop(s, code));
      code += 1;
    }
    return cache;
  }

  private static Map<Object, Value> makeValuesCache(Iterable<Object> origins) {
    final Map<Object, Value> cache = new HashMap<>();
    for (Object origin : origins) {
      if (cache.containsKey(origin)) {
        throw new IllegalArgumentException("Duplicate value origin " + origin);
      }
      cache.put(origin, new Value(origin));
    }
    return cache;
  }

}
