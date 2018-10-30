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

package com.lbs.tedam.data.repository;

import com.lbs.tedam.model.JobCommand;
import com.lbs.tedam.util.EnumsV2.CommandStatus;
import com.lbs.tedam.util.EnumsV2.ExecutionStatus;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Repository for entity JobCommand.
 */
public interface JobCommandRepository extends BaseRepository<JobCommand, Integer> {

    @Transactional
    @Modifying
    @Query("update JobCommand jc set jc.commandStatus = ?2, jc.executionStatus = ?3 , jc.dateUpdated = ?4 where jc.id = ?1")
    public void updateCommandStatusExecutionStatusById(Integer id, CommandStatus commandStatus, ExecutionStatus executionStatus, LocalDateTime now);

    public List<JobCommand> findByJobDetailIdOrderByDraftCommandRunOrderAsc(Integer jobDetailId);

}
