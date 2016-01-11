/*
 * Copyright (c) Konrad Grzanek. All rights reserved.
 * Created 2015-12-15
 */
package jpdp.igraph.impl;

import static jpdp.igraph.impl.Tools.bitsOn3;
import static jpdp.igraph.impl.Tools.makeAdjs;
import static jpdp.igraph.impl.Tools.validateV;

import java.util.BitSet;

import jpdp.igraph.IGraph;
import jpdp.igraph.IMutableGraph;

abstract class AbstractGraph implements IGraph, IMutableGraph {

  @Override
  public long range() {
    return adjs.length;
  }

  @Override
  public long verticesCount() {
    return vs.cardinality();
  }

  @Override
  public boolean isEmpty() {
    return verticesCount() == 0;
  }

  @Override
  public long[] vertices() {
    return bitsOn3(vs);
  }

  @Override
  public boolean hasVertex(long v) {
    return vs.get((int) v);
  }

  @Override
  public boolean hasEdge(long v1, long v2) {
    return adjs[(int) v1].get((int) v2);
  }

  @Override
  public IMutableGraph addVertex(long v) {
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
