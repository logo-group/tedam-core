
package com.lbs.tedam.data.dao.impl;

import com.lbs.tedam.data.dao.PropertyDAO;
import com.lbs.tedam.data.repository.PropertyRepository;
import com.lbs.tedam.exception.localized.GeneralLocalizedException;
import com.lbs.tedam.exception.localized.LocalizedException;
import com.lbs.tedam.model.Property;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PropertyDAOImpl extends BaseDAOImpl<Property, Integer> implements PropertyDAO {

    /**
     * long serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    private transient PropertyRepository repository;

    @Autowired
    public void setRepository(PropertyRepository repository) {
        this.repository = repository;
        super.setRepository(repository);
    }

    @Override
    public List<Property> getPropertyListByValue(String value) throws LocalizedException {
        try {
            List<Property> resultList = repository.findByValue(value);
            return resultList;
        } catch (Exception e) {
            throw new GeneralLocalizedException(e);
        }
    }

    @Override
    public Property getPropertyByNameAndParameter(String name, String parameter) throws LocalizedException {
        try {
            Property property = null;
            List<Property> resultList = repository.findByNameAndParameter(name, parameter);
            if (resultList.size() > 0)
                property = resultList.get(0);
            return property;
        } catch (Exception e) {
            throw new GeneralLocalizedException(e);
        }
    }

    @Override
    public List<Property> getPropertyListByNameAndParameter(String name, String parameter) throws LocalizedException {
        try {
            List<Property> propertyList = repository.findByNameAndParameter(name, parameter);
            return propertyList;
        } catch (Exception e) {
            throw new GeneralLocalizedException(e);
        }
    }

    @Override
    public List<Property> getPropertyListByNameAndParameterStartingWithIgnoreCase(String name, String parameter) throws LocalizedException {
        try {
            List<Property> propertyList = repository.findByNameAndParameterStartingWithIgnoreCase(name, parameter);
            return propertyList;
        } catch (Exception e) {
            throw new GeneralLocalizedException(e);
        }
    }

    @Override
    public List<Property> getPropertyListByName(String name) throws LocalizedException {
        try {
            List<Property> propertyList = repository.findByName(name);
            return propertyList;
        } catch (Exception e) {
            throw new GeneralLocalizedException(e);
        }
    }
}
