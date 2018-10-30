
package com.lbs.tedam.data.dao.impl;

import com.lbs.tedam.data.dao.FormDefinitionDAO;
import com.lbs.tedam.data.repository.FormDefinitonRepository;
import com.lbs.tedam.exception.localized.GeneralLocalizedException;
import com.lbs.tedam.exception.localized.LocalizedException;
import com.lbs.tedam.model.FormDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FormDefinitionDAOImpl extends BaseDAOImpl<FormDefinition, Integer> implements FormDefinitionDAO {

    /**
     * long serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    private transient FormDefinitonRepository repository;

    @Autowired
    public void setRepository(FormDefinitonRepository repository) {
        this.repository = repository;
        super.setRepository(repository);
    }

    @Override
    public FormDefinition getFormDefByNameAndMode(String name, String mode) throws LocalizedException {
        try {
            FormDefinition formDefinition = repository.findByNameAndMode(name, mode);
            return formDefinition;
        } catch (Exception e) {
            throw new GeneralLocalizedException(e);
        }
    }

    @Override
    public List<Integer> getUpdatedFormDefinitionIdList(String version) throws LocalizedException {
        try {
            List<Integer> updatedFormDefinitionIdList = repository.getUpdatedFormDefinitionIdList(version);
            return updatedFormDefinitionIdList;
        } catch (Exception e) {
            throw new GeneralLocalizedException(e);
        }
    }
}
