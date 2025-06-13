package dev.moyis.parallelism;

import dev.moyis.parallelism.model.Cola;
import dev.moyis.parallelism.model.Drawer;
import dev.moyis.parallelism.model.Fernet;
import dev.moyis.parallelism.model.FernetCola;
import dev.moyis.parallelism.model.Fridge;
import dev.moyis.parallelism.model.GlassMadeOfBottle;
import dev.moyis.parallelism.model.Ice;
import dev.moyis.parallelism.model.TrashCan;
import dev.moyis.parallelism.model.Workstation;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FernetService {
  private final Drawer drawer;
  private final Fridge fridge;
  private final TrashCan trashCan;
  private final Workstation workstation;

  private ExecutorService executor = Executors.newFixedThreadPool(10);

  public FernetService(Drawer drawer, Fridge fridge, TrashCan trashCan, Workstation workstation) {
    this.drawer = drawer;
    this.fridge = fridge;
    this.trashCan = trashCan;
    this.workstation = workstation;
  }

  public FernetCola prepareFernetCola() {
    try {
      var glassMadeOfBottle = executor.submit(this::createGlass);
      var ingredients = executor.submit(this::getGetIngredients);
      return workstation.prepareFernetCola(
          glassMadeOfBottle.get(),
          ingredients.get().fernet(),
          ingredients.get().ice(),
          ingredients.get().cola());
    } catch (ExecutionException | InterruptedException e) {
      throw new RuntimeException(e);
    }
  }

  private Ingredients getGetIngredients() {
    var fernet = fridge.getFernet();
    var ice = fridge.getIce();
    var cola = fridge.getCola();
    return new Ingredients(fernet, ice, cola);
  }

  private record Ingredients(Fernet fernet, Ice ice, Cola cola) {}

  private GlassMadeOfBottle createGlass() {
    var knife = drawer.getKnife();
    var lighter = drawer.getLighter();
    var emptyBottle = trashCan.searchEmptyBottle();
    var glassMadeOfBottle = workstation.cutBottle(knife, lighter, emptyBottle);
    return glassMadeOfBottle;
  }
}
