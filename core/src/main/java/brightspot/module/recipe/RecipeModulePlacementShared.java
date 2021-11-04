package brightspot.module.recipe;

import java.util.Optional;

import brightspot.module.ModulePlacement;
import com.psddev.dari.db.Record;
import com.psddev.dari.db.Recordable;

@Recordable.DisplayName("Shared Recipe")
@Recordable.Embedded
public class RecipeModulePlacementShared extends Record implements
    ModulePlacement {

    private RecipeModule shared;

    // --- Getters/setters ---

    public RecipeModule getShared() {
        return shared;
    }

    public void setShared(RecipeModule shared) {
        this.shared = shared;
    }

    // --- Recordable support ---

    @Override
    public String getLabel() {
        return Optional.ofNullable(getShared())
            .map(Record::getLabel)
            .orElse(super.getLabel());
    }
}
