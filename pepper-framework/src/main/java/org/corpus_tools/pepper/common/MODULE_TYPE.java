/**
 * Copyright 2009 Humboldt-Universität zu Berlin, INRIA.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 *
 */
package org.corpus_tools.pepper.common;

/**
 * Names of types of {@link org.corpus_tools.pepper.modules.PepperModule}:
 * <ul>
 * <li>{@link #IMPORTER} - for
 * {@link org.corpus_tools.pepper.modules.PepperExporter}</li>
 * <li>{@link #MANIPULATOR} - for
 * {@link org.corpus_tools.pepper.modules.PepperManipulator}</li>
 * <li>{@link #EXPORTER} - for
 * {@link org.corpus_tools.pepper.modules.PepperExporter}</li>
 * </ul>
 **/
public enum MODULE_TYPE {
	IMPORTER, MANIPULATOR, EXPORTER
};
