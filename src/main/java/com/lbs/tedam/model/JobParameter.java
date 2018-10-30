
package com.lbs.tedam.model;

import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Where(clause = "IS_DELETED=0")
@Table(name = "JOB_PARAMETER", uniqueConstraints = {@UniqueConstraint(columnNames = {"NAME", "PROJECT_ID"})})
public class JobParameter extends AbstractBaseEntity {

    /**
     * long serialVersionUID
     */
    private static final long serialVersionUID = 6707392479921987575L;

    /**
     * String name
     */
    @Column(name = "NAME", unique = true)
    @Size(min = 1, max = 255)
    private String name;

    @ManyToOne
    @JoinColumn(name = "PROJECT_ID")
    @Where(clause = "IS_DELETED=0")
    private Project project;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "JOB_PARAMETER_ID")
    @Where(clause = "IS_DELETED=0")
    private List<JobParameterValue> jobParameterValues = new ArrayList<>();

    public JobParameter() {
        // An empty constructor is needed for all beans
    }

    public JobParameter(String name, Project project) {
        super();
        this.name = name;
        this.project = project;

    }

    /**
     * this method getName <br>
     *
     * @return <br>
     * @author Tarik.Mikyas
     */
    public String getName() {
        return name;
    }

    /**
     * this method setName <br>
     *
     * @param name <br>
     * @author Tarik.Mikyas
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the project
     */
    public Project getProject() {
        return project;
    }

    /**
     * @param project the project to set
     */
    public void setProject(Project project) {
        this.project = project;
    }

    /**
     * this method getJobParameterValues <br>
     *
     * @return <br>
     * @author Tarik.Mikyas
     */
    public List<JobParameterValue> getJobParameterValues() {
        return jobParameterValues;
    }

    /**
     * this method setJobParameterValues <br>
     *
     * @param jobParameterValues <br>
     * @author Tarik.Mikyas
     */
    public void setJobParameterValues(List<JobParameterValue> jobParameterValues) {
        this.jobParameterValues = jobParameterValues;
    }

    @Override
    public String toString() {
        return name;
    }
}
