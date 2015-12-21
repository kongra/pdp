/*
 * Copyright (c) Konrad Grzanek. All rights reserved.
 * Created 2015-12-18
 */
package jpdp.igraph.util;

import java.util.Arrays;

public final class I2I {

  public I2I(long range, long noValue) {
    this.noValue = noValue;
    this.entries = new long[(int) range];
    clear();
  }

  public long range() {
    return entries.length;
  }

  public long size() {
    long s = 0;
    for (long e : entries) {
      if (e != noValue) {
        s += 1;
      }
    }
    return s;
  }

  public boolean isEmpty() {
    for (long e : entries) {
      if (e != noValue) {
        return false;
      }
    }
    return true;
  }

  public boolean containsKey(long n) {
    return entries[(int) n] != noValue;
  }

  public boolean containsValue(long v) {
    for (long e : entries) {
      if (e == v) {
        return true;
      }
    }
    return false;
  }

  public long get(long n) {
    return entries[(int) n];
  }

  public I2I put(long n, long v) {
    entries[(int) n] = v;
    return this;
  }

  public I2I remove(long n) {
    put(n, noValue);
    return this;
  }

  public I2I clear() {
    for (long i = 0, n = range(); i < n; i++) {
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
      if (entries[(int) n] != noValue) {
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

  private final long[] entries;

  private final long noValue;

}
