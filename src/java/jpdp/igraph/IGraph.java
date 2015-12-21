/*
 * Copyright (c) Konrad Grzanek. All rights reserved.
 * Created 2015-12-14
 */
package jpdp.igraph;

public interface IGraph {

  long range();

  long[] vertices();

  long verticesCount();

  boolean isEmpty();

  boolean hasVertex(long v);

  boolean hasEdge(long v1, long v2);

  public IGraph cloneme();

}
