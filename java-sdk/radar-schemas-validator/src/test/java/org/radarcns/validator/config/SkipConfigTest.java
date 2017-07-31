package org.radarcns.validator.config;

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

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.radarcns.validator.config.SkipConfig.skipFile;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;
import org.junit.Test;

/**
 * TODO.
 */
public class SkipConfigTest {

    @Test
    public void testGetPath() {
        String root = "/Users/developer/Repositories/RADAR-Schemas/";

        Set<Path> skipPaths = new HashSet<>();

        skipPaths.add(Paths.get(".DS_Store"));

        assertTrue(skipFile(new File(root.concat("commons/passive/.DS_Store")), skipPaths));
        assertTrue(skipFile(new File(root.concat(".DS_Store")), skipPaths));

        skipPaths.clear();
        skipPaths.add(Paths.get("*.md"));

        assertTrue(skipFile(new File(root.concat("commons/passive/empatica/README.md")),
                skipPaths));
        assertFalse(skipFile(new File(root.concat("specification/passive/schema.avsc")),
                skipPaths));

        skipPaths.clear();
        skipPaths.add(Paths.get("commons/**"));

        assertTrue(skipFile(new File(root.concat("commons/passive/empatica/README.md")),
                skipPaths));
        assertFalse(skipFile(new File(root.concat("specification/passive/README.md")),
                skipPaths));

        skipPaths.clear();
        skipPaths.add(Paths.get("commons/**/README.md"));

        assertTrue(skipFile(new File(root.concat("commons/passive/empatica/README.md")),
                skipPaths));
        assertFalse(skipFile(new File(root.concat("specification/passive/README.md")),
                skipPaths));

        skipPaths.add(Paths.get("commons/monitor/application/application_external_time.avsc"));
        assertTrue(skipFile(new File(root.concat("commons/monitor/application/"
                + "application_external_time.avsc")), skipPaths));
        assertFalse(skipFile(new File(root.concat("commons/passive/application/"
                + "application_external_time.avsc")), skipPaths));
        assertFalse(skipFile(new File(root.concat("commons/monitor/application/"
                + "application_record_counts.avsc")), skipPaths));

        skipPaths.add(Paths.get("commons/**/*.avsc"));
        assertTrue(skipFile(new File(root.concat("commons/monitor/application/"
                + "application_external_time.avsc")), skipPaths));
        assertFalse(skipFile(new File(root.concat("restapi/data/acceleration.avsc")), skipPaths));
    }




}
