package dev.moyis.parallelism.model;

import java.time.Duration;

public class InMemoryWorkstation implements Workstation, Required {

  private boolean cutBottle = false;
  private boolean preparedFernet = false;

  @Override
  public GlassMadeOfBottle cutBottle(Knife knife, Lighter lighter, EmptyBottle emptyBottle) {
    try {
      System.out.println("Start cutting bottle 🪚🍼");
      Thread.sleep(Duration.ofMillis(700));
      System.out.println("Burning border 🔥🍼");
      Thread.sleep(Duration.ofMillis(900));
      cutBottle = true;
      return new GlassMadeOfBottle();
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public FernetCola prepareFernetCola(
      GlassMadeOfBottle glassMadeOfBottle, Fernet fernet, Ice ice, Cola cola) {
    try {
      System.out.println("Putting ice in glass 🍼🧊");
      Thread.sleep(Duration.ofMillis(300));
      System.out.println("Pouring fernet 🍼🧊🌿");
      Thread.sleep(Duration.ofMillis(200));
      System.out.println("Pouring cola 🍼🧊🌿🥤");
      Thread.sleep(Duration.ofMillis(200));
      System.out.println("Mixing with finger 👇🍼🧊🌿🥤");
      Thread.sleep(Duration.ofMillis(100));
      preparedFernet = true;
      System.out.println("Fernet prepared! ✨🍼✨");
      return new FernetCola();
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public boolean executedAllSteps() {
    return cutBottle && preparedFernet;
  }
}
