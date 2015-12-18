/*
 * Copyright (c) Konrad Grzanek. All rights reserved.
 * Created 2015-12-18
 */
package jpdp.igraph.util;

import java.util.Arrays;

public final class I2I {

  public I2I(int range) {
    entries = new Entry[range];
    for (int i = 0; i < range; i++) {
      entries[i] = new Entry();
    }
  }

  public int range() {
    return entries.length;
  }

  public int size() {
    int s = 0;
    for (Entry e : entries) {
      if (!e.isNull) {
        s += 1;
      }
    }
    return s;
  }

  public boolean isEmpty() {
    for (Entry e : entries) {
      if (!e.isNull) {
        return false;
      }
    }
    return true;
  }

  public boolean containsKey(int n) {
    return !entries[n].isNull;
  }

  public boolean containsValue(int v) {
    for (Entry e : entries) {
      if (!e.isNull && e.n == v) {
        return true;
      }
    }
    return false;
  }

  public int get(int n) {
    final Entry e = entries[n];
    if (e.isNull) {
      throw new IllegalStateException("No value for key " + n);
    }
    return e.n;
  }

  public int safeGet(int n, int deflt) {
    final Entry e = entries[n];
    return e.isNull ? deflt : e.n;
  }

  public void put(int n, int v) {
    final Entry e = entries[n];
    e.n = v;
    e.isNull = false;
  }

  public void remove(int n) {
    entries[n].isNull = true;
  }

  public void clear() {
    for (int i = 0, n = range(); i < n; i++) {
      remove(i);
    }
  }

  @Override
  public int hashCode() {
    return 31 + Arrays.hashCode(entries);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    I2I other = (I2I) obj;
    return Arrays.equals(entries, other.entries);
  }

  private final Entry[] entries;

  private static final class Entry {

    boolean isNull = true;

    int n;

    Entry() {
    }

    @Override
    public int hashCode() {
      return isNull ? 1231 : 1237 + n;
    }

    @Override
    public boolean equals(Object obj) {
      if (this == obj)
        return true;
      if (obj == null)
        return false;
      if (getClass() != obj.getClass())
        return false;
      Entry other = (Entry) obj;
      return isNull ? other.isNull : !other.isNull && n == other.n;
    }

  }

}
