package dev.moyis.parallelism.model;

import java.time.Duration;

public class InMemoryFernetCup implements FernetCup, Required {
  private boolean pouredCola = false;
  private boolean pouredIce = false;
  private boolean pouredFernet = false;

  @Override
  public void pour(Cola cola) {
    try {
      System.out.println("Pouring cola 🍼🧊🌿🥤");
      Thread.sleep(Duration.ofMillis(400));
      pouredCola = true;
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
  }

  public void pour(Fernet fernet) {
    try {
      System.out.println("Pouring fernet 🍼🌿");
      Thread.sleep(Duration.ofMillis(300));
      pouredFernet = true;
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public void pour(Ice ice) {
    try {
      System.out.println("Putting ice 🍼🧊");
      Thread.sleep(Duration.ofMillis(500));
      pouredIce = true;
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public boolean isReady() {
    return pouredCola && pouredFernet && pouredIce;
  }

  @Override
  public boolean executedAllSteps() {
    return isReady();
  }
}
