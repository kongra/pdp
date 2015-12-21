/*
 * Copyright (c) Konrad Grzanek. All rights reserved.
 * Created 18 gru 2015
 */
package jpdp.igraph.util;

import java.util.BitSet;

public final class ISet {

  public ISet(long range) {
    this.entries = new BitSet((int) range);
    this.range = range;
  }

  public long range() {
    return range;
  }

  public long size() {
    return entries.cardinality();
  }

  public boolean isEmpty() {
    return size() == 0;
  }

  public boolean contains(long n) {
    return entries.get((int) n);
  }

  public ISet add(long n) {
    entries.set((int) n);
    return this;
  }

  public ISet remove(long n) {
    entries.set((int) n, false);
    return this;
  }

  public ISet clear() {
    entries.clear();
    return this;
  }

  @Override
  public int hashCode() {
    return 31 + entries.hashCode();
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    ISet other = (ISet) obj;
    return entries.equals(other.entries);
  }

  @Override
  public String toString() {
    StringBuilder buf = new StringBuilder("#i{");
    for (long n = entries.nextSetBit(0); n != -1;) {
      buf.append(n);
      n = entries.nextSetBit((int) (n + 1));
      if (n != -1) {
        buf.append(" ");
      }
    }
    return buf.append("}").toString();
  }

  private final BitSet entries;

  private final long range;

}
