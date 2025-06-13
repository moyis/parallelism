package dev.moyis.parallelism.model;

import java.time.Duration;
import java.util.Optional;

public class InMemoryWorkstation implements Workstation, Required {

  private boolean cutBottle = false;
  private boolean preparedFernet = false;

  @Override
  public FernetCup createFernetCup(Knife knife, Lighter lighter, EmptyBottle emptyBottle) {
    try {
      System.out.println("Start cutting bottle 🪚🍼");
      Thread.sleep(Duration.ofMillis(700));
      System.out.println("Burning border 🔥🍼");
      Thread.sleep(Duration.ofMillis(900));
      cutBottle = true;
      return new InMemoryFernetCup();
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public Optional<FernetCola> getFernetCola(FernetCup fernetCup) {
    if (!fernetCup.isReady()) {
      return Optional.empty();
    }
    try {
      System.out.println("Mixing with finger 👇🍼🧊🌿🥤");
      Thread.sleep(Duration.ofMillis(200));
      preparedFernet = true;
      return Optional.of(new FernetCola());
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public boolean executedAllSteps() {
    return cutBottle && preparedFernet;
  }
}
