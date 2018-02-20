/*
 *
 * Copyright 2018 EMBL - European Bioinformatics Institute
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package uk.ac.ebi.ampt2d.accession.study;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import uk.ac.ebi.ampt2d.accession.study.persistence.StudyAccessioningDatabaseService;
import uk.ac.ebi.ampt2d.accession.study.persistence.StudyAccessioningRepository;

@Configuration
@ConditionalOnProperty(name = "services", havingValue = "study-accession")
public class StudyConfiguration {

    @Autowired
    private StudyAccessioningRepository repository;

    @Bean
    @ConditionalOnProperty(name = "services", havingValue = "study-accession")
    public StudyAccessioningService studyAccessionService() {
        return new StudyAccessioningService(studyAccessioningDatabaseService());
    }

    @Bean
    @ConditionalOnProperty(name = "services", havingValue = "study-accession")
    public StudyAccessioningDatabaseService studyAccessioningDatabaseService() {
        return new StudyAccessioningDatabaseService(repository);
    }

}