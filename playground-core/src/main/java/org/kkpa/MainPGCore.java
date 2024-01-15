package org.kkpa;

import org.kkpa.pgutil.models.UserRecord;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class MainPGCore {
  public static void main(String[] args) {
    UserRecord userRecord = new UserRecord(1L, "Cristian", "Camilo");

  }

  public static String returnHello() { return "Hello"; }
}