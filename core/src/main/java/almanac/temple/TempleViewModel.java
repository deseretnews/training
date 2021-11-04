package almanac.temple;

import brightspot.core.page.AbstractContentPageViewModel;
import com.psddev.cms.view.PageEntryView;
import com.psddev.styleguide.almanac.temple.TempleView;
import com.psddev.styleguide.almanac.temple.TempleViewGalleryField;

public class TempleViewModel extends AbstractContentPageViewModel<Temple> implements TempleView, PageEntryView {
    @Override
    public Iterable<? extends TempleViewGalleryField> getGallery() {
        return createViews(TempleViewGalleryField.class, model.getGallery());
    }

    @Override
    public CharSequence getName() {
        return model.getName();
    }

    @Override
    public CharSequence getPageSubHeading() {
        return model.getDescription();
    }
}
