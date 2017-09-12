package org.radarcns.specifications.source;

/*
 * Copyright 2017 King's College London and The Hyve
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import org.radarcns.catalogue.DataType;
import org.radarcns.specifications.source.passive.Sensor;

import java.util.Objects;

import static org.radarcns.specifications.util.Labels.PROCESSING_STATE;
import static org.radarcns.specifications.util.Labels.TOPIC;
import static org.radarcns.specifications.util.Labels.UNIT;

public class KafkaActor {

    private final String doc;

    private final double sampleRate;

    private final String unit;

    private final DataType dataType;

    private final Topic topic;

    private static final String NULL_MESSAGE = " in ".concat(
        Sensor.class.getName()).concat(" cannot be null.");

    public KafkaActor(String doc, double sampleRate, String unit, DataType dataType, Topic topic) {
        Objects.requireNonNull(dataType, PROCESSING_STATE.concat(NULL_MESSAGE));
        Objects.requireNonNull(topic, TOPIC.concat(NULL_MESSAGE));
        Objects.requireNonNull(unit, UNIT.concat(NULL_MESSAGE));

        this.doc = doc;
        this.sampleRate = sampleRate;
        this.unit = unit;
        this.dataType = dataType;
        this.topic = topic;
    }

    public String getDoc() {
        return doc;
    }

    public double getSampleRate() {
        return sampleRate;
    }

    public String getUnit() {
        return unit;
    }

    public DataType getProcessingState() {
        return dataType;
    }

    public Topic getTopic() {
        return topic;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        KafkaActor that = (KafkaActor) o;
        return Double.compare(that.sampleRate, sampleRate) == 0 &&
                Objects.equals(doc, that.doc) &&
                Objects.equals(unit, that.unit) &&
                dataType == that.dataType &&
                Objects.equals(topic, that.topic);
    }

    @Override
    public int hashCode() {
        return Objects.hash(doc, sampleRate, unit, dataType, topic);
    }
}
