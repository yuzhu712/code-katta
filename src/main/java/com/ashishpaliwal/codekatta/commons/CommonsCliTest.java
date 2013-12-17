package com.ashishpaliwal.codekatta.commons;

import org.apache.commons.cli.*;

/**
 *
 */
public class CommonsCliTest {

  public static void main(String[] args) throws Exception {
    Options options = new Options();

    Option option = new Option("n", "name", true, "the name of this agent");
    option.setRequired(false);
    options.addOption(option);

    option = new Option("z", "zkConnString", true, "Zookeeper Connection String");
    option.setRequired(false);
    options.addOption(option);

    CommandLineParser parser = new GnuParser();
    CommandLine commandLine = parser.parse(options, args);

    System.out.println(commandLine.hasOption("zkConnString"));
  }
}
