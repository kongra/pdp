/*
 * Copyright (c) Konrad Grzanek. All rights reserved.
 * Created 2015-12-14
 */
package jpdp.igraph;

public interface IMutableGraph {

  IMutableGraph addVertex(long v);

  IMutableGraph removeVertex(long v);

  IMutableGraph addEdge(long v1, long v2);

  IMutableGraph removeEdge(long v1, long v2);

}
