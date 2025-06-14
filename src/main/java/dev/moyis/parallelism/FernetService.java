package dev.moyis.parallelism;

import dev.moyis.parallelism.model.Drawer;
import dev.moyis.parallelism.model.FernetCola;
import dev.moyis.parallelism.model.Fridge;
import dev.moyis.parallelism.model.TrashCan;
import dev.moyis.parallelism.model.Workstation;
import java.util.Optional;

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
    var knife = drawer.getKnife();
    var lighter = drawer.getLighter();
    var emptyBottle = trashCan.searchEmptyBottle();
    var fernet = fridge.getFernet();
    var ice = fridge.getIce();
    var cola = fridge.getCola();
    var fernetCup = workstation.createFernetCup(knife, lighter, emptyBottle);
    fernetCup.pour(ice);
    fernetCup.pour(fernet);
    fernetCup.pour(cola);
    return workstation.getFernetCola(fernetCup);
  }
}
