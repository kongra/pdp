/*
 * Copyright (c) Konrad Grzanek. All rights reserved.
 * Created 2015-12-14
 */
package jpdp.igraph.impl;

import static jpdp.igraph.impl.Tools.bitsOn3;
import static jpdp.igraph.impl.Tools.makeAdjs;
import static jpdp.igraph.impl.Tools.validateV;

import java.util.BitSet;

import jpdp.igraph.Edge;
import jpdp.igraph.IGraph;
import jpdp.igraph.IMutableGraph;
import jpdp.igraph.IUnigraph;

public final class Unigraph extends AbstractGraph implements IUnigraph {

  public Unigraph(long range) {
    this(new BitSet((int) range), makeAdjs(range));
  }

  @Override
  public  long[] neighbors(long v) {
    return bitsOn3(adjs[(int) v]);
  }

  @Override
  public  Edge[] neighborEdges(long v) {
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
  public  IMutableGraph removeVertex(long v) {
    validateV(this, v);

    final int iv = (int) v;

    // Remove adjacency for all successors
    final BitSet succs = adjs[iv];
    for (int s = succs.nextSetBit(0); s != -1; s = succs.nextSetBit(s + 1)) {
      adjs[s].set(iv, false);
    }

    // Remove adjacency to all successors
    succs.clear();

    // Bye vertices!
    vs.set(iv, false);
    return this;
  }

  @Override
  public  IMutableGraph addEdge(long v1, long v2) {
    validateV(this, v1);
    validateV(this, v2);

    final int iv1 = (int) v1;
    final int iv2 = (int) v2;

    // Add both to the vertex set (they may be already present)
    vs.set(iv1);
    vs.set(iv2);

    // Make bidirectional adjacency
    adjs[iv1].set(iv2);
    adjs[iv2].set(iv1);

    return this;
  }

  @Override
  public  IMutableGraph removeEdge(long v1, long v2) {
    validateV(this, v1);
    validateV(this, v2);

    final int iv1 = (int) v1;
    final int iv2 = (int) v2;

    // Remove bidirectional adjacency
    adjs[iv1].set(iv2, false);
    adjs[iv2].set(iv1, false);

    return this;
  }

  @Override
  public  IGraph cloneme() {
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
