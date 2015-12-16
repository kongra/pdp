/*
 * Copyright (c) Konrad Grzanek. All rights reserved.
 * Created 2015-12-14
 */
package jpdp.igraph;

import static jpdp.igraph.IGraphTools.makeAdjs;
import static jpdp.igraph.IGraphTools.validateV;

import java.util.BitSet;

public final class Unigraph extends AbstractGraph {

  public Unigraph(int range) {
    this(new BitSet(range), makeAdjs(range));
  }

  @Override
  public synchronized void removeVertex(int v) {
    validateV(this, v);

    // Remove adjacency for all successors
    final BitSet succs = super.adjs[v];
    for (int s = succs.nextSetBit(0); s != -1; s = succs.nextSetBit(s + 1)) {
      super.adjs[s].set(v, false);
    }

    // Remove adjacency to all successors
    succs.clear();

    // Bye vertices!
    super.vs.set(v, false);
  }

  @Override
  public synchronized void addEdge(int v1, int v2) {
    validateV(this, v1);
    validateV(this, v2);

    // Add both to the vertex set (they may be already present)
    super.vs.set(v1);
    super.vs.set(v2);

    // Make bidirectional adjacency
    super.adjs[v1].set(v2);
    super.adjs[v2].set(v1);
  }

  @Override
  public synchronized void removeEdge(int v1, int v2) {
    validateV(this, v1);
    validateV(this, v2);

    // Remove bidirectional adjacency
    super.adjs[v1].set(v2, false);
    super.adjs[v2].set(v1, false);
  }

  @Override
  public synchronized IGraph cloneme() {
    final BitSet vs = (BitSet) super.vs.clone();

    final BitSet[] adjs = new BitSet[super.adjs.length];
    for (int i = 0; i < adjs.length; i++) {
      adjs[i] = (BitSet) super.adjs[i].clone();
    }

    return new Unigraph(vs, adjs);
  }

  private Unigraph(BitSet vs, BitSet[] adjs) {
    super(vs, adjs);
  }

}
