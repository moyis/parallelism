package dev.moyis.parallelism;

import dev.moyis.parallelism.model.Drawer;
import dev.moyis.parallelism.model.FernetCola;
import dev.moyis.parallelism.model.Fridge;
import dev.moyis.parallelism.model.GlassMadeOfBottle;
import dev.moyis.parallelism.model.TrashCan;
import dev.moyis.parallelism.model.Workstation;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

public class FernetService {
  private final Drawer drawer;
  private final Fridge fridge;
  private final TrashCan trashCan;
  private final Workstation workstation;

  public FernetService(Drawer drawer, Fridge fridge, TrashCan trashCan, Workstation workstation) {
    this.drawer = drawer;
    this.fridge = fridge;
    this.trashCan = trashCan;
    this.workstation = workstation;
  }

  public Optional<FernetCola> prepareFernetCola() {
    var glassMadeOfBottle = CompletableFuture.supplyAsync(this::createGlass);
    var pourFernetTask = CompletableFuture.supplyAsync(fridge::getFernet)
        .thenAcceptAsync(fernet -> workstation.pourFernet(fernet, glassMadeOfBottle.join()));

    var pourIceTask = CompletableFuture.supplyAsync(fridge::getIce)
        .thenAcceptAsync(ice -> workstation.pourIce(ice, glassMadeOfBottle.join()));

    var pourColaTask = CompletableFuture.supplyAsync(fridge::getCola)
        .thenAcceptAsync(cola -> workstation.pourCola(cola, glassMadeOfBottle.join()));

    CompletableFuture.allOf(pourFernetTask, pourColaTask, pourIceTask).join();
    return workstation.getFernetCola();
  }

  private GlassMadeOfBottle createGlass() {
    var knife = CompletableFuture.supplyAsync(drawer::getKnife);
    var lighter = CompletableFuture.supplyAsync(drawer::getLighter);
    var emptyBottle = CompletableFuture.supplyAsync(trashCan::searchEmptyBottle);
    return workstation.cutBottle(knife.join(), lighter.join(), emptyBottle.join());
  }
}
