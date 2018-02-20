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
package uk.ac.ebi.ampt2d.accession.sample;

import com.fasterxml.jackson.annotation.JsonIgnore;
import uk.ac.ebi.ampt2d.accession.HashableMessage;
import uk.ac.ebi.ampt2d.accession.Message;

import java.util.Map;
import java.util.stream.Collectors;

public class SampleMessage implements HashableMessage<String>, Message {

    private Map<String, String> sampleProperties;

    SampleMessage() {
    }

    public SampleMessage(Map<String, String> sampleProperties) {
        this.sampleProperties = sampleProperties;
    }

    @Override
    @JsonIgnore
    public String getHashableMessage() {
        return this.sampleProperties.values().stream().sorted().collect(Collectors.joining(","));
    }

    public Map<String, String> getSampleProperties() {
        return sampleProperties;
    }

    @Override
    @JsonIgnore
    public String getMessage() {
        return getHashableMessage();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SampleMessage that = (SampleMessage) o;

        return getHashableMessage() != null ? getHashableMessage().equals(that.getHashableMessage()) : that
                .getHashableMessage() == null;
    }

    @Override
    public int hashCode() {
        return getHashableMessage() != null ? getHashableMessage().hashCode() : 0;
    }

    @Override
    public String toString() {
        return "{" +
                "sampleProperties=" + sampleProperties +
                '}';
    }
}