package dev.moyis.parallelism.model;

import java.time.Duration;

public class InMemoryTrashCan implements TrashCan, Required {
  private boolean foundBottle = false;
  @Override
  public EmptyBottle searchEmptyBottle() {
    try {
      Thread.sleep(Duration.ofMillis(1600));
      System.out.println("Found empty cola bottle in the trash 🍼");
      foundBottle = true;
      return new EmptyBottle();
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public boolean executedAllSteps() {
    return  foundBottle;
  }
}
