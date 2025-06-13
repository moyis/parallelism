package dev.moyis.parallelism.model;

public interface FernetCup {
    void pour(Ice ice);
    void pour(Fernet fernet);
    void pour(Cola cola);
    boolean isReady();
}
