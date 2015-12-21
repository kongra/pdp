/*
 * Copyright (c) Konrad Grzanek. All rights reserved.
 * Created 2015-12-14
 */
package jpdp.igraph;

public interface IDigraph {

  int[] successors(int v);

  Edge[] successorEdges(int v);

  int[] predecessors(int v);

  Edge[] predecessorEdges(int v);

}
