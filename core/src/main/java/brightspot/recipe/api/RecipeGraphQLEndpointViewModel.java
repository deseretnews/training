package brightspot.recipe.api;

import java.util.List;
import java.util.stream.Collectors;

import brightspot.recipe.Difficulty;
import brightspot.recipe.Recipe;
import com.psddev.cms.db.Site;
import com.psddev.cms.page.CurrentSite;
import com.psddev.cms.view.ViewInterface;
import com.psddev.cms.view.ViewModel;
import com.psddev.cms.view.ViewResponse;
import com.psddev.dari.db.Query;
import com.psddev.dari.db.QueryPhrase;
import com.psddev.dari.util.ObjectUtils;
import com.psddev.dari.util.PaginatedResult;
import com.psddev.dari.web.annotation.WebParameter;
import org.apache.commons.lang3.StringUtils;

@ViewInterface("Recipes")
public class RecipeGraphQLEndpointViewModel extends ViewModel<RecipeGraphQLEndpoint> {

    @CurrentSite
    private Site site;

    @WebParameter
    private int page;

    @WebParameter
    private String title;

    @RangeGraphQLParameter
    private RangeParameter totalTime;

    @RangeGraphQLParameter
    private RangeParameter prepTime;

    @RangeGraphQLParameter
    private RangeParameter inactivePrepTime;

    @RangeGraphQLParameter
    private RangeParameter cookTime;

    @WebParameter
    private Difficulty difficulty;

    @WebParameter
    private List<String> tags;

    @WebParameter
    private List<String> searchTerms;

    private PaginatedResult<Recipe> paginatedResult;

    @Override
    protected void onCreate(ViewResponse response) {
        int limit = 20;

        if (page < 1) {
            page = 1;
        }

        long offset = limit * (page - 1L);

        paginatedResult = buildQuery().select(offset, limit);
    }

    public Iterable<RecipeApiViewModel> getItems() {
        return createViews(RecipeApiViewModel.class, paginatedResult.getItems());
    }

    public Integer getNextPage() {
        return paginatedResult.hasNext()
            ? page + 1
            : null;
    }

    private Query<Recipe> buildQuery() {
        Query<Recipe> query = Query.from(Recipe.class)
            .where("* matches *") // force to solr for performance
            .and(site != null ? site.itemsPredicate() : null);

        if (!StringUtils.isBlank(title)) {
            query.and("title matches ?", title);
        }

        if (totalTime != null) {
            totalTime.updateQuery(query, "getTotalTime");
        }

        if (prepTime != null) {
            prepTime.updateQuery(query, "prepTime");
        }

        if (inactivePrepTime != null) {
            inactivePrepTime.updateQuery(query, "inactivePrepTime");
        }

        if (cookTime != null) {
            cookTime.updateQuery(query, "cookTime");
        }

        if (difficulty != null) {
            query.and("difficulty = ?", difficulty);
        }

        if (!ObjectUtils.isBlank(tags)) {
            query.and("getRecipeTagNames matches ?", tags);
        }

        if (!ObjectUtils.isBlank(searchTerms)) {
            query.and("* matches ?", searchTerms.stream()
                .map(s -> QueryPhrase.builder()
                    .phrase(s)
                    .proximity(3)
                    .build())
                .collect(Collectors.toList()));
        }

        return query;
    }
}
