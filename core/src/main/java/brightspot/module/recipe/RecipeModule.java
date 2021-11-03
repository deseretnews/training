package brightspot.module.recipe;

import brightspot.module.SharedModule;
import brightspot.util.NoUrlsWidget;
import com.psddev.cms.db.ToolUi;
import com.psddev.dari.db.Recordable;

@Recordable.DisplayName("Recipe")
@ToolUi.FieldDisplayOrder({
    "internalName"
})
public class RecipeModule extends AbstractRecipeModule implements
    NoUrlsWidget,
    SharedModule {

    @Required
    private String internalName;

    // --- Getters/setters ---

    public String getInternalName() {
        return internalName;
    }

    public void setInternalName(String internalName) {
        this.internalName = internalName;
    }

    // --- Recordable support ---

    @Override
    public String getLabel() {
        return getInternalName();
    }
}
