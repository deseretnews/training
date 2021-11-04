package almanac.temple;

import brightspot.core.permalink.AbstractPermalinkRule;
import com.psddev.cms.db.Site;

public class TemplePermalinkRule extends AbstractPermalinkRule {

    @Override
    public String createPermalink(Site site, Object object) {

        if (!(object instanceof Temple)) {
            return null;
        }

        Temple temple = (Temple) object;
        String templeSlug = temple.asSluggableData().getSlug();
        return "/almanac/temple/" + templeSlug;
    }
}
