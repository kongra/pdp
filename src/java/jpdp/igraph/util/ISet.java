/*
 * Copyright (c) Konrad Grzanek. All rights reserved.
 * Created 18 gru 2015
 */
package jpdp.igraph.util;

import java.util.BitSet;

public final class ISet {

  public ISet(int range) {
    this.entries = new BitSet(range);
  }

  public int size() {
    return entries.cardinality();
  }

  public boolean isEmpty() {
    return size() == 0;
  }

  public boolean contains(int n) {
    return entries.get(n);
  }

  public void add(int n) {
    entries.set(n);
  }

  public void remove(int n) {
    entries.set(n, false);
  }

  public void clear() {
    entries.clear();
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

  private final BitSet entries;

}
