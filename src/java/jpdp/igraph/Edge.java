/*
 * Copyright (c) Konrad Grzanek. All rights reserved.
 * Created 2015-12-17
 */
package jpdp.igraph;

public final class Edge {

  public final long v1;

  public final long v2;

  public Edge(long v1, long v2) {
    this.v1 = v1;
    this.v2 = v2;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + (int) (v1 ^ (v1 >>> 32));
    result = prime * result + (int) (v2 ^ (v2 >>> 32));
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (Edge.class != obj.getClass())
      return false;
    Edge other = (Edge) obj;
    return v1 == other.v1 && v2 == other.v2;
  }

  @Override
  public String toString() {
    return v1 + "-" + v2;
  }

}
