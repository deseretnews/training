package brightspot.recipe.api;

import java.util.Collections;
import java.util.List;

import com.psddev.dari.db.Recordable;
import com.psddev.dari.db.Singleton;
import com.psddev.graphql.cda.ContentDeliveryApiEndpoint;
import com.psddev.graphql.cda.ContentDeliveryEntryPointField;

@Recordable.DisplayName("Recipes (GraphQL)")
public class RecipeGraphQLEndpoint extends ContentDeliveryApiEndpoint implements Singleton {
    @Override
    protected String getPathSuffix() {
        return "/recipes";
    }

    @Override
    public List<ContentDeliveryEntryPointField> getQueryEntryFields() {
        return Collections.singletonList(new ContentDeliveryEntryPointField(
            RecipeGraphQLEndpointViewModel.class,
            "Recipes",
            null));
    }
}
