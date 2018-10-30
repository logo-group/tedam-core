/*
 * Copyright 2014-2019 Logo Business Solutions
 * (a.k.a. LOGO YAZILIM SAN. VE TIC. A.S)
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package com.lbs.tedam.model;

import com.lbs.tedam.util.EnumsV2.TedamUserFavoriteType;
import com.lbs.tedam.util.EnumsV2.TedamUserRole;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Where(clause = "IS_DELETED=0")
@Table(name = "TEDAM_USER")
public class TedamUser extends AbstractBaseEntity {

    /**
     * long serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "USER_NAME", unique = true)
    private String userName;

    @NotNull
    @Size(min = 4, max = 255)
    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "ROLE")
    private TedamUserRole role;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Where(clause = "IS_DELETED=0")
    private List<Project> projects = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "TEDAM_USER_ID")
    private List<TedamUserFavorite> userFavorites = new ArrayList<>();

    public TedamUser() {
    }

    public TedamUser(String userName, String password, TedamUserRole role) {
        super();
        this.password = password;
        this.userName = userName;
        this.role = role;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public TedamUserRole getRole() {
        return role;
    }

    public void setRole(TedamUserRole role) {
        this.role = role;
    }

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }

    public List<TedamUserFavorite> getUserFavorites() {
        return userFavorites;
    }

    public void setUserFavorites(List<TedamUserFavorite> userFavorites) {
        this.userFavorites = userFavorites;
    }

    public List<Client> getUserFavoritesClient() {
        List<Client> list = userFavorites.stream().filter(userFavorite -> TedamUserFavoriteType.CLIENT.equals(userFavorite.getFavoriteType()))
                .map(userFavorite -> userFavorite.getClient()).collect(Collectors.toList());
        return list;
    }

    public List<Job> getUserFavoritesJob() {
        List<Job> list = userFavorites.stream().filter(userFavorite -> TedamUserFavoriteType.JOB.equals(userFavorite.getFavoriteType())).map(userFavorite -> userFavorite.getJob())
                .collect(Collectors.toList());
        return list;
    }

    public List<Environment> getUserFavoritesEnvironments(Project project) {
        List<Environment> userFavoriteEnvironmentsList = new ArrayList<>();
        for (TedamUserFavorite favEnv : userFavorites) {
            if (favEnv.getFavoriteType().equals(TedamUserFavoriteType.ENVIRONMENT)
                    && favEnv.getEnvironment().getProject().equals(project)) {
                userFavoriteEnvironmentsList.add(favEnv.getEnvironment());
            }
        }

        return userFavoriteEnvironmentsList;
    }

}
