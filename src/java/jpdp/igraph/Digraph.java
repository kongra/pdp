/*
 * Copyright (c) Konrad Grzanek. All rights reserved.
 * Created 2015-12-15
 */
package jpdp.igraph;

import static jpdp.igraph.IGraphTools.bitsOn3;
import static jpdp.igraph.IGraphTools.makeAdjs;
import static jpdp.igraph.IGraphTools.validateV;

import java.util.BitSet;

public final class Digraph extends AbstractGraph implements IDigraph {

  public Digraph(int range) {
    this(new BitSet(range), makeAdjs(range), makeAdjs(range));
  }

  @Override
  public synchronized int[] predecessors(int v) {
    return bitsOn3(ins[v]);
  }

  @Override
  public void removeVertex(int v) {
    validateV(this, v);

    // Remove adjacency for all predecessors
    final BitSet preds = this.ins[v];
    for (int p = preds.nextSetBit(0); p != -1; p = preds.nextSetBit(p + 1)) {
      super.adjs[p].set(v, false);
    }

    // Remove ins for all successors
    final BitSet succs = super.adjs[v];
    for (int s = succs.nextSetBit(0); s != -1; s = succs.nextSetBit(s + 1)) {
      this.ins[s].set(v, false);
    }

    // Remove adjacency to all successors
    super.adjs[v].clear();

    // Bye vertices!
    super.vs.set(v, false);
  }

  @Override
  public void addEdge(int v1, int v2) {
    validateV(this, v1);
    validateV(this, v2);

    // Add both to the vertex set (may be already present)
    super.vs.set(v1);
    super.vs.set(v2);

    // Make unidirectional adjacency
    super.adjs[v1].set(v2);
    this.ins[v2].set(v1);
  }

  @Override
  public void removeEdge(int v1, int v2) {
    validateV(this, v1);
    validateV(this, v2);

    // Remove unidirectional adjacency
    super.adjs[v1].set(v2, false);
    this.ins[v2].set(v1, false);
  }

  @Override
  public synchronized IGraph cloneme() {
    final BitSet vs = (BitSet) super.vs.clone();

    final BitSet[] adjs = new BitSet[super.adjs.length];
    for (int i = 0; i < adjs.length; i++) {
      adjs[i] = (BitSet) super.adjs[i].clone();
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
