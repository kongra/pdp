/*
 * Copyright (c) Konrad Grzanek. All rights reserved.
 * Created 2015-11-20
 */
package jpdp.core;

import java.util.BitSet;

public final class Indexing {

  public static void int2BitSet(BitSet bs, int n) {
    bs.clear();
    int i = 0;
    while (n != 0L) {
      if (n % 2L != 0) {
        bs.set(i);
      }
      i += 1;
      n = n >>> 1;
    }
  }

  private Indexing() {

  }

}
