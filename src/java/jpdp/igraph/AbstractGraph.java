/*
 * Copyright (c) Konrad Grzanek. All rights reserved.
 * Created 2015-12-15
 */
package jpdp.igraph;

import static jpdp.igraph.IGraphTools.bitsOn3;
import static jpdp.igraph.IGraphTools.makeAdjs;
import static jpdp.igraph.IGraphTools.validateV;

import java.util.BitSet;

abstract class AbstractGraph implements IGraph, IMutableGraph {

  @Override
  public int range() { // No need to synchronize, adjs.length is final
    return adjs.length;
  }

  @Override
  public synchronized int verticesCount() {
    return vs.cardinality();
  }

  @Override
  public boolean isEmpty() {
    return verticesCount() == 0;
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
  public synchronized Edge[] successorEdges(int v) {
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
  public synchronized IMutableGraph addVertex(int v) {
    validateV(this, v);
    vs.set(v);
    return this;
  }

  AbstractGraph(int range) {
    this(new BitSet(range), makeAdjs(range));
  }

  AbstractGraph(BitSet vs, BitSet[] adjs) {
    this.vs = vs;
    this.adjs = adjs;
  }

  final BitSet vs;

  final BitSet[] adjs;

}
