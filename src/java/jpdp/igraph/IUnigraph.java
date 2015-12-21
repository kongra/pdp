/*
 * Copyright (c) Konrad Grzanek. All rights reserved.
 * Created 21 gru 2015
 */
package jpdp.igraph;

public interface IUnigraph {

  int[] neighbors(int v);

  Edge[] neighborEdges(int v);

}
