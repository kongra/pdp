/*
 * Copyright (c) Konrad Grzanek. All rights reserved.
 * Created 2015-12-18
 */
package jpdp.igraph.util;

import java.util.Arrays;

public final class I2I {

  public I2I(int range, int noValue) {
    this.noValue = noValue;
    this.entries = new int[range];
    clear();
  }

  public int range() {
    return entries.length;
  }

  public int size() {
    int s = 0;
    for (int e : entries) {
      if (e != noValue) {
        s += 1;
      }
    }
    return s;
  }

  public boolean isEmpty() {
    for (int e : entries) {
      if (e != noValue) {
        return false;
      }
    }
    return true;
  }

  public boolean containsKey(int n) {
    return entries[n] != noValue;
  }

  public boolean containsValue(int v) {
    for (int e : entries) {
      if (e == v) {
        return true;
      }
    }
    return false;
  }

  public int get(int n) {
    return entries[n];
  }

  public I2I put(int n, int v) {
    entries[n] = v;
    return this;
  }

  public I2I remove(int n) {
    put(n, noValue);
    return this;
  }

  public I2I clear() {
    for (int i = 0, n = range(); i < n; i++) {
      remove(i);
    }
    return this;
  }

  @Override
  public int hashCode() {
    return 31 + Arrays.hashCode(entries);
  }

  @Override
  public String toString() {
    StringBuilder buf = new StringBuilder("ii{");
    for (int n = nextNonNullEntryIndex(0); n != -1;) {
      buf.append(n).append(" ").append(entries[n]);
      n = nextNonNullEntryIndex(n + 1);
      if (n != -1) {
        buf.append(", ");
      }
    }
    return buf.append("}").toString();
  }

  private int nextNonNullEntryIndex(int fromIndex) {
    for (int n = fromIndex, end = range(); n < end; n++) {
      if (entries[n] != noValue) {
        return n;
      }
    }
    return -1;
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

  private final int[] entries;

  private final int noValue;

}
