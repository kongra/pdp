/*
 * Copyright (c) Konrad Grzanek. All rights reserved.
 * Created 2015-12-18
 */
package jpdp.igraph.util;

import java.util.Arrays;

public class I2Just {

  public I2Just(long range) {
    entries = new Object[(int) range];
  }

  public long range() {
    return entries.length;
  }

  public long size() {
    long s = 0;
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

  public boolean containsKey(long n) {
    return entries[(int) n] != null;
  }

  public boolean containsValue(Object v) {
    for (Object e : entries) {
      if (v.equals(e)) {
        return true;
      }
    }
    return false;
  }

  public Object get(long n) {
    return entries[(int) n];
  }

  public I2Just put(long n, Object v) {
    entries[(int) n] = v;
    return this;
  }

  public I2Just remove(long n) {
    entries[(int) n] = null;
    return this;
  }

  public I2Just clear() {
    for (long i = 0, end = range(); i < end; i++) {
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
    if (I2Just.class != obj.getClass())
      return false;
    I2Just other = (I2Just) obj;
    return Arrays.equals(entries, other.entries);
  }

  @Override
  public String toString() {
    StringBuilder buf = new StringBuilder("i{");
    for (long n = nextNonNullEntryIndex(0); n != -1;) {
      buf.append(n).append(" ").append(entries[(int) n]);
      n = nextNonNullEntryIndex(n + 1);
      if (n != -1) {
        buf.append(", ");
      }
    }
    return buf.append("}").toString();
  }

  private long nextNonNullEntryIndex(long fromIndex) {
    for (long n = fromIndex, end = range(); n < end; n++) {
      if (null != entries[(int) n]) {
        return n;
      }
    }
    return -1;
  }

  private final Object[] entries;

}
