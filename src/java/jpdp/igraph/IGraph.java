/*
 * Copyright (c) Konrad Grzanek. All rights reserved.
 * Created 2015-12-14
 */
package jpdp.igraph;

public interface IGraph {

  int range();

  int[] vertices();

  int verticesCount();

  boolean isEmpty();

  boolean hasVertex(int v);

  boolean hasEdge(int v1, int v2);

  public IGraph cloneme();

}
