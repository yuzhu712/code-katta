package com.ashishpaliwal.codekatta.guava;

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;

/**
 *
 */
public class SimpleTests {
  public static void main(String[] args) {
    String zkConnString = "dummy";
    Preconditions.checkArgument(!Strings.isNullOrEmpty(zkConnString), "Invalid Zookeeper Connection String %s", zkConnString);
  }
}
