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
