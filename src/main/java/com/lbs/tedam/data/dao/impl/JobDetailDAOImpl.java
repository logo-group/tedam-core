
package com.lbs.tedam.data.dao.impl;

import com.lbs.tedam.data.dao.JobDetailDAO;
import com.lbs.tedam.data.repository.JobDetailRepository;
import com.lbs.tedam.exception.localized.GeneralLocalizedException;
import com.lbs.tedam.exception.localized.LocalizedException;
import com.lbs.tedam.model.Client;
import com.lbs.tedam.model.JobDetail;
import com.lbs.tedam.model.Project;
import com.lbs.tedam.model.TestSet;
import com.lbs.tedam.util.EnumsV2.CommandStatus;
import com.lbs.tedam.util.EnumsV2.TedamBoolean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Canberk.Erkmen
 */
@Component
public class JobDetailDAOImpl extends BaseDAOImpl<JobDetail, Integer> implements JobDetailDAO {

    /**
     * long serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    private transient JobDetailRepository repository;

    @Autowired
    public void setRepository(JobDetailRepository repository) {
        this.repository = repository;
        super.setRepository(repository);
    }

    @Override
    public List<JobDetail> getJobDetailListByJobId(int jobId) throws LocalizedException {
        try {
            List<JobDetail> resultList = repository.findByJobId(jobId);
            return resultList;
        } catch (Exception e) {
            throw new GeneralLocalizedException(e);
        }
    }

    @Override
    public void updateJobDetailStatus(JobDetail jobDetail, CommandStatus jobDetailStatus) throws LocalizedException {
        try {
            jobDetail.setStatus(jobDetailStatus);
            jobDetail.setDateUpdated(LocalDateTime.now());
            save(jobDetail);
        } catch (LocalizedException e) {
            throw e;
        } catch (Exception e) {
            throw new GeneralLocalizedException(e);
        }
    }

    @Override
    public List<JobDetail> getJobDetailListByProject(Project project) throws LocalizedException {
        try {
            List<JobDetail> resultList = repository.findByTestSetProjectAndTestSetDeleted(project, TedamBoolean.FALSE.getBooleanValue());
            return resultList;
        } catch (Exception e) {
            throw new GeneralLocalizedException(e);
        }
    }

    @Override
    public List<JobDetail> getJobDetailByClient(Client client) throws LocalizedException {
        try {
            List<JobDetail> result = repository.findByClientAndTestSetDeleted(client, TedamBoolean.FALSE.getBooleanValue());
            return result;
        } catch (Exception e) {
            throw new GeneralLocalizedException(e);
        }
    }

    @Override
    public List<JobDetail> getJobDetailListByTestSet(TestSet testSet) throws LocalizedException {
        try {
            List<JobDetail> resultList = repository.findByTestSetAndDeleted(testSet, TedamBoolean.FALSE.getBooleanValue());
            return resultList;
        } catch (Exception e) {
            throw new GeneralLocalizedException(e);
        }
    }

    @Override
    public void resetJobDetail(Integer jobId) throws LocalizedException {
        try {
            repository.resetJobDetail(jobId);
        } catch (Exception e) {
            throw new GeneralLocalizedException(e);
        }
    }

    @Override
    public void resetJobDetailClient(Integer clientId) throws LocalizedException {
        try {
            repository.resetJobDetailClient(clientId);
        } catch (Exception e) {
            throw new GeneralLocalizedException(e);
        }
    }
}
