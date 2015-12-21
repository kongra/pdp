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

  public Unigraph(int range) {
    this(new BitSet(range), makeAdjs(range));
  }

  @Override
  public synchronized int[] neighbors(int v) {
    return bitsOn3(adjs[v]);
  }

  @Override
  public synchronized Edge[] neighborEdges(int v) {
    final BitSet bs = adjs[v];
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
  public synchronized IMutableGraph removeVertex(int v) {
    validateV(this, v);

    // Remove adjacency for all successors
    final BitSet succs = adjs[v];
    for (int s = succs.nextSetBit(0); s != -1; s = succs.nextSetBit(s + 1)) {
      adjs[s].set(v, false);
    }

    // Remove adjacency to all successors
    succs.clear();

    // Bye vertices!
    vs.set(v, false);
    return this;
  }

  @Override
  public synchronized IMutableGraph addEdge(int v1, int v2) {
    validateV(this, v1);
    validateV(this, v2);

    // Add both to the vertex set (they may be already present)
    vs.set(v1);
    vs.set(v2);

    // Make bidirectional adjacency
    adjs[v1].set(v2);
    adjs[v2].set(v1);

    return this;
  }

  @Override
  public synchronized IMutableGraph removeEdge(int v1, int v2) {
    validateV(this, v1);
    validateV(this, v2);

    // Remove bidirectional adjacency
    adjs[v1].set(v2, false);
    adjs[v2].set(v1, false);

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
