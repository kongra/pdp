/*
 * Copyright (c) Konrad Grzanek. All rights reserved.
 * Created 2015-12-13
 */
package jpdp.igraph;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;

public final class IGraphTools {

  public static List<Integer> bitsOn1(BitSet bs) {
    final List<Integer> indexes = new ArrayList<>();
    for (int i = bs.nextSetBit(0); i != -1; i = bs.nextSetBit(i + 1)) {
      indexes.add(i);
    }
    return indexes;
  }

  public static List<Integer> bitsOn2(BitSet bs) {
    final int N = bs.cardinality();
    final List<Integer> indexes = new ArrayList<>(N);
    int nextSetBit = bs.nextSetBit(0);
    for (int i = 0; i < N; ++i) {
      indexes.add(nextSetBit);
      nextSetBit = bs.nextSetBit(nextSetBit + 1);
    }
    return indexes;
  }

  public static int[] bitsOn3(BitSet bs) {
    final int N = bs.cardinality();
    final int[] indexes = new int[N];
    int nextSetBit = bs.nextSetBit(0);
    for (int i = 0; i < N; ++i) {
      indexes[i] = nextSetBit;
      nextSetBit = bs.nextSetBit(nextSetBit + 1);
    }
    return indexes;
  }

  public static BitSet[] makeAdjs(int range) {
    BitSet[] adjs = new BitSet[range];
    for (int i = 0; i < range; i++) {
      adjs[i] = new BitSet(range);
    }
    return adjs;
  }

  public static void validateV(IGraph g, int v) {
    validateV(g.range(), v);
  }

  public static void validateV(int range, int v) {
    if (v < 0 || v >= range) {
      throw new IllegalArgumentException("Vertex " + v
          + " is out of range 0 .. " + (range - 1));
    }
  }

  private IGraphTools() {

  }

}
