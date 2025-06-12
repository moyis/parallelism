package dev.moyis.parallelism.model;

public interface Workstation {

  GlassMadeOfBottle cutBottle(Knife knife, Lighter lighter, EmptyBottle emptyBottle);

  FernetCola prepareFernetCola(
      GlassMadeOfBottle glassMadeOfBottle, Fernet fernet, Ice ice, Cola cola);
}
