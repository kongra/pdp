/*
 * Copyright (c) Konrad Grzanek. All rights reserved.
 * Created 2015-12-18
 */
package jpdp.igraph.util;

import java.util.Arrays;

public class I2Just<T> {

  public I2Just(int range) {
    entries = new Object[range];
  }

  public int range() {
    return entries.length;
  }

  public int size() {
    int s = 0;
    for (Object e : entries) {
      if (null != e) {
        s += 1;
      }
    }
    return s;
  }

  public boolean isEmpty() {
    for (Object e : entries) {
      if (null != e) {
        return false;
      }
    }
    return true;
  }

  public boolean containsKey(int n) {
    return entries[n] != null;
  }

  public boolean containsValue(Object v) {
    for (Object e : entries) {
      if (v.equals(e)) {
        return true;
      }
    }
    return false;
  }

  @SuppressWarnings("unchecked")
  public T get(int n) {
    return (T) entries[n];
  }

  public I2Just put(int n, T v) {
    entries[n] = v;
    return this;
  }

  public I2Just remove(int n) {
    entries[n] = null;
    return this;
  }

  public I2Just clear() {
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
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    I2Just other = (I2Just) obj;
    return Arrays.equals(entries, other.entries);
  }

  @Override
  public String toString() {
    StringBuilder buf = new StringBuilder("i{");
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
      if (null != entries[n]) {
        return n;
      }
    }
    return -1;
  }

  private final Object[] entries;

}
