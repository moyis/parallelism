package dev.moyis.parallelism.model;

import java.time.Duration;
import java.util.Optional;

public class InMemoryWorkstation implements Workstation, Required {

  private boolean cutBottle = false;
  private boolean preparedFernet = false;
  private boolean pouredIce = false;
  private boolean pouredCola = false;
  private boolean pouredFernet = false;

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
  public void pourCola(Cola cola, GlassMadeOfBottle glassMadeOfBottle) {
    try {
      System.out.println("Pouring cola 🍼🧊🌿🥤");
      Thread.sleep(Duration.ofMillis(200));
      pouredCola = true;
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public void pourFernet(Fernet fernet, GlassMadeOfBottle glassMadeOfBottle) {
    try {
      System.out.println("Pouring fernet 🍼🌿");
      Thread.sleep(Duration.ofMillis(200));
      pouredFernet = true;
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public void pourIce(Ice ice, GlassMadeOfBottle glassMadeOfBottle) {
    try {
      System.out.println("Putting ice 🍼🧊");
      Thread.sleep(Duration.ofMillis(300));
      pouredIce = true;
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public Optional<FernetCola> getFernetCola() {
    if (!pouredFernet || !pouredIce || !pouredCola) {
      return Optional.empty();
    }
    try {
      System.out.println("Mixing with finger 👇🍼🧊🌿🥤");
      Thread.sleep(Duration.ofMillis(100));
      preparedFernet = true;
      return Optional.of(new FernetCola());
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public boolean executedAllSteps() {
    return cutBottle && preparedFernet && pouredIce && pouredCola && pouredFernet;
  }
}
