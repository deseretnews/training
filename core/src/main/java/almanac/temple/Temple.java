package almanac.temple;

import brightspot.core.image.ImageOption;
import brightspot.core.slug.Sluggable;
import com.psddev.cms.db.Content;
import com.psddev.dari.util.Utils;
import java.util.Set;

public class Temple extends Content implements Sluggable {
    private String name;
    private String description;
    private Set<ImageOption> gallery;

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Set<ImageOption> getGallery() {
        return gallery;
    }

    @Override
    public String getSluggableSlugFallback() {
        return Utils.toNormalized(getName());
    }
}
