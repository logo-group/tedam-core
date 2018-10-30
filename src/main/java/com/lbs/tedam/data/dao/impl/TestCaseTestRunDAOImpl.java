
package com.lbs.tedam.data.dao.impl;

import com.lbs.tedam.data.dao.TestCaseTestRunDAO;
import com.lbs.tedam.data.repository.TestCaseTestRunRepository;
import com.lbs.tedam.exception.localized.GeneralLocalizedException;
import com.lbs.tedam.model.TestCaseTestRun;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TestCaseTestRunDAOImpl extends BaseDAOImpl<TestCaseTestRun, Integer> implements TestCaseTestRunDAO {

    private static final long serialVersionUID = 1L;

    private transient TestCaseTestRunRepository repository;

    @Autowired
    public void setRepository(TestCaseTestRunRepository repository) {
        this.repository = repository;
        super.setRepository(repository);
    }

    @Override
    public List<TestCaseTestRun> findByTestCaseIdRange(Integer testCaseIdStart, Integer testCaseIdEnd, boolean deleted)
            throws GeneralLocalizedException {
        try {
            return repository.findByTestCaseIdRange(testCaseIdStart, testCaseIdEnd, deleted);
        } catch (Exception e) {
            throw new GeneralLocalizedException(e);
        }
    }

    @Override
    public List<TestCaseTestRun> findByTestSetIdRange(Integer testSetIdStart, Integer testSetIdEnd, boolean deleted)
            throws GeneralLocalizedException {
        try {
            return repository.findByTestSetIdRange(testSetIdStart, testSetIdEnd, deleted);
        } catch (Exception e) {
            throw new GeneralLocalizedException(e);
        }
    }

}
