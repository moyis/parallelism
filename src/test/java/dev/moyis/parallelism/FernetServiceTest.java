package dev.moyis.parallelism;

import static org.assertj.core.api.Assertions.assertThat;

import dev.moyis.parallelism.model.InMemoryDrawer;
import dev.moyis.parallelism.model.InMemoryFridge;
import dev.moyis.parallelism.model.InMemoryTrashCan;
import dev.moyis.parallelism.model.InMemoryWorkstation;
import dev.moyis.parallelism.model.Required;
import java.util.Set;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class FernetServiceTest {
  private final InMemoryDrawer inMemoryDrawer = new InMemoryDrawer();
  private final InMemoryFridge inMemoryFridge = new InMemoryFridge();
  private final InMemoryTrashCan inMemoryTrashCan = new InMemoryTrashCan();
  private final InMemoryWorkstation inMemoryWorkstation = new InMemoryWorkstation();
  private final Set<Required> requiredSteps = Set.of(inMemoryFridge, inMemoryDrawer, inMemoryTrashCan, inMemoryWorkstation);

  @Test
  @DisplayName("Executes all steps while preparing a fernet")
  public void preparesFernetCola() {
    var subject = aFernetService();
    var fernet = subject.prepareFernetCola();
    assertThat(fernet).isNotNull();
    assertThat(requiredSteps).allMatch(Required::executedAllSteps);
  }

  private FernetService aFernetService() {
    return new FernetService(inMemoryDrawer, inMemoryFridge, inMemoryTrashCan, inMemoryWorkstation);
  }
}
