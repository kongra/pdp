/*
 * Copyright (c) Konrad Grzanek. All rights reserved.
 * Created 2015-12-14
 */
package jpdp.igraph;

import static jpdp.igraph.IGraphTools.bitsOn3;
import static jpdp.igraph.IGraphTools.validateV;

import java.util.BitSet;

public final class Unigraph implements IGraph, IMutableGraph, Cloneable {

  public Unigraph(int range) {
    this(new BitSet(range), IGraphTools.makeAdjs(range));
  }

  @Override
  public int range() { // No need to synchronize, adjs.length is final
    return adjs.length;
  }

  @Override
  public synchronized int[] vertices() {
    return bitsOn3(vs);
  }

  @Override
  public synchronized boolean hasVertex(int v) {
    return vs.get(v);
  }

  @Override
  public synchronized boolean hasEdge(int v1, int v2) {
    return adjs[v1].get(v2);
  }

  @Override
  public synchronized int[] successors(int v) {
    return bitsOn3(adjs[v]);
  }

  @Override
  public synchronized void addVertex(int v) {
    validateV(this, v);
    vs.set(v);
  }

  @Override
  public synchronized void removeVertex(int v) {
    validateV(this, v);

    // Remove adjacency from all successors
    final BitSet succs = adjs[v];
    for (int s = succs.nextSetBit(0); s != -1; s = succs.nextSetBit(s + 1)) {
      adjs[s].set(v, false);
    }

    // Remove adjacency to all successors
    adjs[v].clear();

    // Bye vertices!
    vs.set(v, false);
  }

  @Override
  public synchronized void addEdge(int v1, int v2) {
    validateV(this, v1);
    validateV(this, v2);

    // Add both to the vertex set (may be already present)
    vs.set(v1);
    vs.set(v2);

    // Make bidirectional adjacency
    adjs[v1].set(v2);
    adjs[v2].set(v1);
  }

  @Override
  public synchronized void removeEdge(int v1, int v2) {
    validateV(this, v1);
    validateV(this, v2);

    // Remove bidirectional adjacency.
    adjs[v1].set(v2, false);
    adjs[v2].set(v1, false);
  }

  @Override
  public synchronized Object clone() throws CloneNotSupportedException {
    final BitSet vs = (BitSet) this.vs.clone();

    final BitSet[] adjs = new BitSet[this.adjs.length];
    for(int i = 0; i < adjs.length; i++) {
      adjs[i] = (BitSet) this.adjs[i].clone();
    }

    return new Unigraph(vs, adjs);
  }

  private Unigraph(BitSet vs, BitSet[] adjs) {
    this.vs = vs;
    this.adjs = adjs;
  }

  private final BitSet vs;

  private final BitSet[] adjs;
}
