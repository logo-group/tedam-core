
package com.lbs.tedam.data.dao.impl;

import com.lbs.tedam.data.dao.FormFieldDAO;
import com.lbs.tedam.data.repository.FormFieldRepository;
import com.lbs.tedam.exception.localized.GeneralLocalizedException;
import com.lbs.tedam.exception.localized.LocalizedException;
import com.lbs.tedam.model.FormField;
import com.lbs.tedam.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FormFieldDAOImpl extends BaseDAOImpl<FormField, Integer> implements FormFieldDAO {

    /**
     * long serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    private transient FormFieldRepository repository;

    @Autowired
    public void setRepository(FormFieldRepository repository) {
        this.repository = repository;
        super.setRepository(repository);
    }

    @Override
    public List<FormField> getFormFieldListByVersionAndFormDefId(String version, int formId) throws LocalizedException {
        try {
            List<FormField> formFieldList = repository.findByVersionAndFormDefinitionId(version, formId);
            return formFieldList;
        } catch (Exception e) {
            throw new GeneralLocalizedException(e);
        }
    }

    @Override
    public String[][] getFieldsOfVersionsAndForms(String version, int formId) throws LocalizedException {
        try {
            String[][] formFieldList = repository.getFieldsOfVersionsAndForms(version, formId);
            return formFieldList;
        } catch (Exception e) {
            throw new GeneralLocalizedException(e);
        }
    }

    @Override
    public List<FormField> getHistoryOfTag(int formDefinitionId, String tag) throws LocalizedException {
        try {
            List<FormField> formFieldList = repository.findByTagAndFormDefinitionId(tag, formDefinitionId);
            return formFieldList;
        } catch (Exception e) {
            throw new GeneralLocalizedException(e);
        }
    }

    @Override
    public String[][] getControlFieldsOfVersionsAndForms(String version, int formId) throws LocalizedException {
        try {
            String[][] formFieldList = repository.getControlFieldsOfVersionsAndForms(version, formId, Constants.VALUE_NULL);
            return formFieldList;
        } catch (Exception e) {
            throw new GeneralLocalizedException(e);
        }
    }

    @Override
    public String getCaptionBySnapshotValue(Integer snapshotValueId, String tag) throws LocalizedException {
        try {
            String caption = repository.getCaptionBySnapshotValue(snapshotValueId, tag);
            return caption;
        } catch (Exception e) {
            throw new GeneralLocalizedException(e);
        }
    }

    @Override
    public List<FormField> findByFormDefinitionId(int formId) throws LocalizedException {
        try {
            List<FormField> formFieldList = repository.findByFormDefinitionId(formId);
            return formFieldList;
        } catch (Exception e) {
            throw new GeneralLocalizedException(e);
        }
    }

    @Override
    public List<FormField> findByFormDefinitionIdAndStatus(int formId, boolean status) throws LocalizedException {
        try {
            List<FormField> formFieldList = repository.findByStatusAndFormDefinitionId(status, formId);
            return formFieldList;
        } catch (Exception e) {
            throw new GeneralLocalizedException(e);
        }
    }
}
