/*
 * Copyright (c) Konrad Grzanek. All rights reserved.
 * Created 18 gru 2015
 */
package test.jpdp.igraph.util;

import jpdp.igraph.util.I2I;
import jpdp.igraph.util.ISet;

public class TestIGraphUtil {

  public static void main(String[] args) {
    // test1();
    test2();

  }

  private static void test2() {
    I2I m = new I2I(10, -1);
    System.out.println(m);

    m.put(1, 2);
    System.out.println(m);

    m.put(3, 4);
    System.out.println(m);

    m.put(5, 6);
    System.out.println(m);
  }

  private static void test1() {
    ISet s = new ISet(10);
    System.out.println(s);

    s.add(1);
    System.out.println(s);

    s.add(2);
    System.out.println(s);

    s.add(3);
    System.out.println(s);
  }

}
