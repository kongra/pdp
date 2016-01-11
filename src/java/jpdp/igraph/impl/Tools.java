/*
 * Copyright (c) Konrad Grzanek. All rights reserved.
 * Created 2015-12-13
 */
package jpdp.igraph.impl;

import java.util.BitSet;

import jpdp.igraph.IGraph;

public final class Tools {

  public static long[] bitsOn3(BitSet bs) {
    final int N = bs.cardinality();
    final long[] indexes = new long[N];
    int nextSetBit = bs.nextSetBit(0);
    for (int i = 0; i < N; ++i) {
      indexes[i] = nextSetBit;
      nextSetBit = bs.nextSetBit(nextSetBit + 1);
    }
    return indexes;
  }

  public static BitSet[] makeAdjs(long range) {
    final int irange = (int) range;
    BitSet[] adjs = new BitSet[irange];
    for (int i = 0; i < irange; i++) {
      adjs[i] = new BitSet(irange);
    }
    return adjs;
  }

  public static void validateV(IGraph g, long v) {
    validateV(g.range(), v);
  }

  public static void validateV(long range, long v) {
    if (v < 0 || v >= range) {
      throw new IllegalArgumentException("Vertex " + v
          + " is out of valid range 0 .. " + (range - 1));
    }
  }

  private Tools() {

  }

}
