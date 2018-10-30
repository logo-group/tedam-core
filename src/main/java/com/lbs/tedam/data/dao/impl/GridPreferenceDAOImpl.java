
package com.lbs.tedam.data.dao.impl;

import com.lbs.tedam.data.dao.GridPreferenceDAO;
import com.lbs.tedam.data.repository.GridPreferenceRepository;
import com.lbs.tedam.exception.localized.GeneralLocalizedException;
import com.lbs.tedam.exception.localized.LocalizedException;
import com.lbs.tedam.model.GridPreference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GridPreferenceDAOImpl extends BaseDAOImpl<GridPreference, Integer> implements GridPreferenceDAO {

    private static final long serialVersionUID = 1L;

    private transient GridPreferenceRepository repository;

    @Autowired
    public void setRepository(GridPreferenceRepository repository) {
        this.repository = repository;
        super.setRepository(repository);
    }

    @Override
    public GridPreference findByUserIdAndProjectIdAndViewIdAndGridId(Integer userId, Integer projectId, String viewId,
                                                                     String gridId) throws LocalizedException {
        try {
            return repository.findByUserIdAndProjectIdAndViewIdAndGridId(userId, projectId, viewId, gridId);
        } catch (Exception e) {
            throw new GeneralLocalizedException(e);
        }
    }

}
