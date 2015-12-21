/*
 * Copyright (c) Konrad Grzanek. All rights reserved.
 * Created 2015-12-15
 */
package jpdp.igraph;

import static jpdp.igraph.Tools.bitsOn3;
import static jpdp.igraph.Tools.makeAdjs;
import static jpdp.igraph.Tools.validateV;

import java.util.BitSet;

abstract class AbstractGraph implements IGraph, IMutableGraph {

  @Override
  public long range() { // No need to synchronize, adjs.length is final
    return adjs.length;
  }

  @Override
  public synchronized long verticesCount() {
    return vs.cardinality();
  }

  @Override
  public boolean isEmpty() {
    return verticesCount() == 0;
  }

  @Override
  public synchronized long[] vertices() {
    return bitsOn3(vs);
  }

  @Override
  public synchronized boolean hasVertex(long v) {
    return vs.get((int) v);
  }

  @Override
  public synchronized boolean hasEdge(long v1, long v2) {
    return adjs[(int) v1].get((int) v2);
  }

  @Override
  public synchronized IMutableGraph addVertex(long v) {
    validateV(this, v);
    vs.set((int) v);
    return this;
  }

  AbstractGraph(long range) {
    this(new BitSet((int) range), makeAdjs(range));
  }

  AbstractGraph(BitSet vs, BitSet[] adjs) {
    this.vs = vs;
    this.adjs = adjs;
  }

  final BitSet vs;

  final BitSet[] adjs;

}
