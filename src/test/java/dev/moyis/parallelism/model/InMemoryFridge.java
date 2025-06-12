package dev.moyis.parallelism.model;

import java.time.Duration;

public class InMemoryFridge implements Fridge, Required {
  private boolean gotCola = false;
  private boolean gotFernet = false;
  private boolean gotIce = false;

  @Override
  public Cola getCola() {
    try {
      Thread.sleep(Duration.ofMillis(900));
      System.out.println("Retrieved cola from fridge 🥤");
      gotCola = true;
      return new Cola();
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public Fernet getFernet() {
    try {
      Thread.sleep(Duration.ofMillis(800));
      System.out.println("Retrieved fernet from fridge 🌿");
      gotFernet = true;
      return new Fernet();
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public Ice getIce() {
    try {
      Thread.sleep(Duration.ofMillis(1200));
      System.out.println("Retrieved ice from fridge 🧊");
      gotIce = true;
      return new Ice();
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
  }

  public boolean executedAllSteps() {
    return gotFernet && gotIce && gotCola;
  }
}
