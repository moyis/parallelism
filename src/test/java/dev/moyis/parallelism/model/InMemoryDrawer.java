package dev.moyis.parallelism.model;

import java.time.Duration;

public class InMemoryDrawer implements Drawer, Required {
  private boolean gotKnife = false;
  private boolean gotLighter = false;

  @Override
  public Knife getKnife() {
    try {
      Thread.sleep(Duration.ofMillis(500));
      System.out.println("Retrieved knife from drawer 🔪");
      gotKnife = true;
      return new Knife();
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public Lighter getLighter() {
    try {
      Thread.sleep(Duration.ofMillis(500));
      System.out.println("Retrieved lighter from drawer 🔥");
      gotLighter = true;
      return new Lighter();
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public boolean executedAllSteps() {
    return gotKnife && gotLighter;
  }
}
