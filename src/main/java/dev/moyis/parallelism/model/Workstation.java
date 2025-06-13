package dev.moyis.parallelism.model;

import java.util.Optional;

public interface Workstation {

  GlassMadeOfBottle cutBottle(Knife knife, Lighter lighter, EmptyBottle emptyBottle);

  void pourCola(Cola cola, GlassMadeOfBottle glassMadeOfBottle);
  void pourFernet(Fernet fernet, GlassMadeOfBottle glassMadeOfBottle);
  void pourIce(Ice ice, GlassMadeOfBottle glassMadeOfBottle);
  Optional<FernetCola> getFernetCola();
}
