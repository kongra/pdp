/*
 * Copyright (c) Konrad Grzanek. All rights reserved.
 * Created 2015-12-14
 */
package jpdp.igraph;

public interface IDigraph {

  long[] successors(long v);

  Edge[] successorEdges(long v);

  long[] predecessors(long v);

  Edge[] predecessorEdges(long v);

}
