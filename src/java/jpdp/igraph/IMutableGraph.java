/*
 * Copyright (c) Konrad Grzanek. All rights reserved.
 * Created 2015-12-14
 */
package jpdp.igraph;

public interface IMutableGraph {

  void addVertex(int v);

  void removeVertex(int v);

  void addEdge(int v1, int v2);

  void removeEdge(int v1, int v2);

}
