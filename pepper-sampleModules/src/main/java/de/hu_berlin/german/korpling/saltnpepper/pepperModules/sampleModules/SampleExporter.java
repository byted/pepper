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
package de.hu_berlin.german.korpling.saltnpepper.pepperModules.sampleModules;

import java.util.List;

import org.osgi.service.component.annotations.Component;

import de.hu_berlin.german.korpling.saltnpepper.pepper.common.DOCUMENT_STATUS;
import de.hu_berlin.german.korpling.saltnpepper.pepper.modules.PepperExporter;
import de.hu_berlin.german.korpling.saltnpepper.pepper.modules.PepperMapper;
import de.hu_berlin.german.korpling.saltnpepper.pepper.modules.PepperModule;
import de.hu_berlin.german.korpling.saltnpepper.pepper.modules.PepperModuleProperties;
import de.hu_berlin.german.korpling.saltnpepper.pepper.modules.exceptions.PepperModuleNotReadyException;
import de.hu_berlin.german.korpling.saltnpepper.pepper.modules.impl.PepperExporterImpl;
import de.hu_berlin.german.korpling.saltnpepper.pepper.modules.impl.PepperMapperImpl;
import de.hu_berlin.u.saltnpepper.graph.Identifier;
import de.hu_berlin.u.saltnpepper.salt.common.SCorpus;
import de.hu_berlin.u.saltnpepper.salt.common.SCorpusGraph;
import de.hu_berlin.u.saltnpepper.salt.core.SNode;
import de.hu_berlin.u.saltnpepper.salt.util.SaltUtil;

/**
 * This class is a dummy implementation of a {@link PepperExporter} to show how
 * an exporter works in general. This implementation can be used as a template
 * for an own module. Therefore adapt the TODO's. <br/>
 * This dummy implementation just exports the corpus-structure and
 * document-structure to dot formatted files. The dot format is a mechanism to
 * store graph based data for visualizing them. With the tool GraphViz, such a
 * graph could be converted to a png, svg ... file. For more information about
 * dot and GraphViz, see: http://www.graphviz.org/.
 * 
 * @author Florian Zipser
 * @version 1.0
 * 
 */
// TODO /1/: change the name of the component, for example use the format name
// and the ending Exporter (FORMATExporterComponent)
@Component(name = "SampleExporterComponent", factory = "PepperExporterComponentFactory")
public class SampleExporter extends PepperExporterImpl implements PepperExporter {
	// =================================================== mandatory
	// ===================================================
	/**
	 * <strong>OVERRIDE THIS METHOD FOR CUSTOMIZATION</strong>
	 * 
	 * A constructor for your module. Set the coordinates, with which your
	 * module shall be registered. The coordinates (modules name, version and
	 * supported formats) are a kind of a fingerprint, which should make your
	 * module unique.
	 */
	public SampleExporter() {
		super();
		// TODO change the name of the module, for example use the format name
		// and the ending Exporter (FORMATExporter)
		setName("SampleExporter");
		setName(ENDING_XML);
		// TODO change the version of your module, we recommend to synchronize
		// this value with the maven version in your pom.xml
		setVersion("1.1.0");
		// TODO change "dot" with format name and 1.0 with format version to
		// support
		addSupportedFormat("dot", "1.0", null);
		// TODO change file ending, here it is set to 'dot' to create dot files
		setDocumentEnding("dot");
		// TODO change if necessary, this means, that the method
		// exportCorpusStructure will create a file-structure corresponding to
		// the given corpus-structure. One folder per SCorpus object
		setExportMode(EXPORT_MODE.DOCUMENTS_IN_FILES);
	}

	/**
	 * This method creates a {@link PepperMapper}. <br/>
	 * In this dummy implementation an instance of {@link SampleMapper} is
	 * created and its location to where the document-structure should be
	 * exported to is set.
	 */
	@Override
	public PepperMapper createPepperMapper(Identifier Identifier) {
		PepperMapper mapper = new SampleMapper();
		mapper.setResourceURI(getIdentifier2ResourceTable().get(Identifier));
		return (mapper);
	}

	public static class SampleMapper extends PepperMapperImpl {
		/**
		 * Stores each document-structure to location given by
		 * {@link #getResourceURI()}.
		 */
		@Override
		public DOCUMENT_STATUS mapSDocument() {
			// workaround to deal with a bug in Salt
			SCorpusGraph sCorpusGraph = getDocument().getGraph();

			SaltUtil.save_DOT(getDocument(), getResourceURI());

			// workaround to deal with a bug in Salt
			if (getDocument().getGraph() == null) {
				getDocument().setGraph(sCorpusGraph);
			}

			addProgress(1.0);
			return (DOCUMENT_STATUS.COMPLETED);
		}

		/**
		 * Storing the corpus-structure once
		 */
		@Override
		public DOCUMENT_STATUS mapSCorpus() {
			List<SNode> roots = getCorpus().getGraph().getRoots();
			if ((roots != null) && (!roots.isEmpty())) {
				if (getCorpus().equals(roots.get(0))) {
					SaltUtil.save_DOT(getCorpus().getGraph(), getResourceURI());
				}
			}

			return (DOCUMENT_STATUS.COMPLETED);
		}
	}

	// =================================================== optional
	// ===================================================
	/**
	 * <strong>OVERRIDE THIS METHOD FOR CUSTOMIZATION</strong>
	 * 
	 * This method is called by the pepper framework after initializing this
	 * object and directly before start processing. Initializing means setting
	 * properties {@link PepperModuleProperties}, setting temporary files,
	 * resources etc. . returns false or throws an exception in case of
	 * {@link PepperModule} instance is not ready for any reason.
	 * 
	 * @return false, {@link PepperModule} instance is not ready for any reason,
	 *         true, else.
	 */
	@Override
	public boolean isReadyToStart() throws PepperModuleNotReadyException {
		// TODO make some initializations if necessary
		return (super.isReadyToStart());
	}
}
