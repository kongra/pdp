/*
 * Copyright (c) Konrad Grzanek. All rights reserved.
 * Created 2015-12-15
 */
package jpdp.igraph.impl;

import static jpdp.igraph.impl.Tools.bitsOn3;
import static jpdp.igraph.impl.Tools.makeAdjs;
import static jpdp.igraph.impl.Tools.validateV;

import java.util.BitSet;

import jpdp.igraph.Edge;
import jpdp.igraph.IDigraph;
import jpdp.igraph.IGraph;
import jpdp.igraph.IMutableGraph;

public final class Digraph extends AbstractGraph implements IDigraph {

  public Digraph(long range) {
    this(new BitSet((int) range), makeAdjs(range), makeAdjs(range));
  }

  @Override
  public  long[] predecessors(long v) {
    return bitsOn3(ins[(int) v]);
  }

  @Override
  public  Edge[] predecessorEdges(long v) {
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
  public  long[] successors(long v) {
    return bitsOn3(adjs[(int) v]);
  }

  @Override
  public  Edge[] successorEdges(long v) {
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

    // Remove adjacency for all predecessors
    final BitSet preds = ins[iv];
    for (int p = preds.nextSetBit(0); p != -1; p = preds.nextSetBit(p + 1)) {
      adjs[p].set(iv, false);
    }

    // Remove ins for all successors
    final BitSet succs = adjs[iv];
    for (int s = succs.nextSetBit(0); s != -1; s = succs.nextSetBit(s + 1)) {
      ins[s].set(iv, false);
    }

    // Remove adjacency to all successors
    adjs[iv].clear();

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

    // Add both to the vertex set (may be already present)
    vs.set(iv1);
    vs.set(iv2);

    // Make unidirectional adjacency
    adjs[iv1].set(iv2);
    ins[iv2].set(iv1);

    return this;
  }

  @Override
  public  IMutableGraph removeEdge(long v1, long v2) {
    validateV(this, v1);
    validateV(this, v2);

    final int iv1 = (int) v1;
    final int iv2 = (int) v2;

    // Remove unidirectional adjacency
    adjs[iv1].set(iv2, false);
    ins[iv2].set(iv1, false);

    return this;
  }

  @Override
  public  IGraph cloneme() {
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
