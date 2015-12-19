/*
 * Copyright (c) Konrad Grzanek. All rights reserved.
 * Created 2015-12-14
 */
package jpdp.igraph;

public interface IMutableGraph {

  IMutableGraph addVertex(int v);

  IMutableGraph removeVertex(int v);

  IMutableGraph addEdge(int v1, int v2);

  IMutableGraph removeEdge(int v1, int v2);

}
