/*
 * Copyright (c) Konrad Grzanek. All rights reserved.
 * Created 2015-12-13
 */
package jpdp.igraph;

import java.util.BitSet;

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
    BitSet[] adjs = new BitSet[(int) range];
    for (int i = 0; i < range; i++) {
      adjs[i] = new BitSet((int) range);
    }
    return adjs;
  }

  public static void validateV(IGraph g, long v) {
    validateV(g.range(), v);
  }

  public static void validateV(long range, long v) {
    if (v < 0 || v >= range) {
      throw new IllegalArgumentException("Vertex " + v
          + " is out of range 0 .. " + (range - 1));
    }
  }

  private Tools() {

  }

}
