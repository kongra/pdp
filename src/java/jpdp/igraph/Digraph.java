/*
 * Copyright (c) Konrad Grzanek. All rights reserved.
 * Created 2015-12-15
 */
package jpdp.igraph;

import static jpdp.igraph.Tools.bitsOn3;
import static jpdp.igraph.Tools.makeAdjs;
import static jpdp.igraph.Tools.validateV;

import java.util.BitSet;

public final class Digraph extends AbstractGraph implements IDigraph {

  public Digraph(long range) {
    this(new BitSet((int) range), makeAdjs(range), makeAdjs(range));
  }

  @Override
  public synchronized long[] predecessors(long v) {
    return bitsOn3(ins[(int) v]);
  }

  @Override
  public Edge[] predecessorEdges(long v) {
    final BitSet bs = ins[(int) v];
    final int N = bs.cardinality();
    final Edge[] edges = new Edge[N];
    int v2 = bs.nextSetBit(0);
    for (int i = 0; i < N; ++i) {
      edges[i] = new Edge(v2, v);
      v2 = bs.nextSetBit(v2 + 1);
    }
    return edges;
  }

  @Override
  public synchronized long[] successors(long v) {
    return bitsOn3(adjs[(int) v]);
  }

  @Override
  public synchronized Edge[] successorEdges(long v) {
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

    // Remove adjacency for all predecessors
    final BitSet preds = ins[(int) v];
    for (int p = preds.nextSetBit(0); p != -1; p = preds.nextSetBit(p + 1)) {
      adjs[p].set((int) v, false);
    }

    // Remove ins for all successors
    final BitSet succs = adjs[(int) v];
    for (int s = succs.nextSetBit(0); s != -1; s = succs.nextSetBit(s + 1)) {
      ins[s].set((int) v, false);
    }

    // Remove adjacency to all successors
    adjs[(int) v].clear();

    // Bye vertices!
    vs.set((int) v, false);

    return this;
  }

  @Override
  public synchronized IMutableGraph addEdge(long v1, long v2) {
    validateV(this, v1);
    validateV(this, v2);

    // Add both to the vertex set (may be already present)
    vs.set((int) v1);
    vs.set((int) v2);

    // Make unidirectional adjacency
    adjs[(int) v1].set((int) v2);
    ins[(int) v2].set((int) v1);

    return this;
  }

  @Override
  public synchronized IMutableGraph removeEdge(long v1, long v2) {
    validateV(this, v1);
    validateV(this, v2);

    // Remove unidirectional adjacency
    adjs[(int) v1].set((int) v2, false);
    ins[(int) v2].set((int) v1, false);

    return this;
  }

  @Override
  public synchronized IGraph cloneme() {
    final BitSet vs = (BitSet) this.vs.clone();

    final BitSet[] adjs = new BitSet[this.adjs.length];
    for (int i = 0; i < adjs.length; i++) {
      adjs[i] = (BitSet) this.adjs[i].clone();
    }

    final BitSet[] ins = new BitSet[this.ins.length];
    for (int i = 0; i < ins.length; i++) {
      ins[i] = (BitSet) this.ins[i].clone();
    }

    return new Digraph(vs, adjs, ins);
  }

  private Digraph(BitSet vs, BitSet[] adjs, BitSet[] ins) {
    super(vs, adjs);
    this.ins = ins;
  }

  private final BitSet[] ins;

}
