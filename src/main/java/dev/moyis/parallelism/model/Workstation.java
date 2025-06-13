package dev.moyis.parallelism.model;

import java.util.Optional;

public interface Workstation {

  FernetCup createFernetCup(Knife knife, Lighter lighter, EmptyBottle emptyBottle);

  Optional<FernetCola> getFernetCola(FernetCup fernetCup);
}
