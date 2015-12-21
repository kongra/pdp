/*
 * Copyright (c) Konrad Grzanek. All rights reserved.
 * Created 2015-12-14
 */
package jpdp.igraph;

import static jpdp.igraph.Tools.bitsOn3;
import static jpdp.igraph.Tools.makeAdjs;
import static jpdp.igraph.Tools.validateV;

import java.util.BitSet;

public final class Unigraph extends AbstractGraph implements IUnigraph {

  public Unigraph(long range) {
    this(new BitSet((int) range), makeAdjs(range));
  }

  @Override
  public synchronized long[] neighbors(long v) {
    return bitsOn3(adjs[(int) v]);
  }

  @Override
  public synchronized Edge[] neighborEdges(long v) {
    final BitSet bs = adjs[(int) v];
    final int N = bs.cardinality();
    final Edge[] edges = new Edge[N];
    int v2 = bs.nextSetBit(0);
    for (int i = 0; i < N; ++i) {
      edges[i] = new Edge(v, v2);
      v2 = bs.nextSetBit(v2 + 1);
    }
    return edges;
  }

  @Override
  public synchronized IMutableGraph removeVertex(long v) {
    validateV(this, v);

    // Remove adjacency for all successors
    final BitSet succs = adjs[(int) v];
    for (int s = succs.nextSetBit(0); s != -1; s = succs.nextSetBit(s + 1)) {
      adjs[s].set((int) v, false);
    }

    // Remove adjacency to all successors
    succs.clear();

    // Bye vertices!
    vs.set((int) v, false);
    return this;
  }

  @Override
  public synchronized IMutableGraph addEdge(long v1, long v2) {
    validateV(this, v1);
    validateV(this, v2);

    // Add both to the vertex set (they may be already present)
    vs.set((int) v1);
    vs.set((int) v2);

    // Make bidirectional adjacency
    adjs[(int) v1].set((int) v2);
    adjs[(int) v2].set((int) v1);

    return this;
  }

  @Override
  public synchronized IMutableGraph removeEdge(long v1, long v2) {
    validateV(this, v1);
    validateV(this, v2);

    // Remove bidirectional adjacency
    adjs[(int) v1].set((int) v2, false);
    adjs[(int) v2].set((int) v1, false);

    return this;
  }

  @Override
  public synchronized IGraph cloneme() {
    final BitSet vs = (BitSet) this.vs.clone();

    final BitSet[] adjs = new BitSet[this.adjs.length];
    for (int i = 0; i < adjs.length; i++) {
      adjs[i] = (BitSet) this.adjs[i].clone();
    }

    return new Unigraph(vs, adjs);
  }

  private Unigraph(BitSet vs, BitSet[] adjs) {
    super(vs, adjs);
  }

}
