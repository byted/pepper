/**
 * Copyright 2009 Humboldt University of Berlin, INRIA.
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
package de.hu_berlin.german.korpling.saltnpepper.pepper.pepperFW.tests.PepperConverter;

import java.io.IOException;
import java.util.Hashtable;

import junit.framework.TestCase;
import junit.textui.TestRunner;

import org.eclipse.emf.common.util.URI;

import de.hu_berlin.german.korpling.saltnpepper.pepper.pepperFW.PepperConverter;
import de.hu_berlin.german.korpling.saltnpepper.pepper.pepperFW.PepperFWFactory;
import de.hu_berlin.german.korpling.saltnpepper.pepper.pepperModules.impl.PepperExporterImpl;
import de.hu_berlin.german.korpling.saltnpepper.pepper.pepperModules.impl.PepperImporterImpl;
import de.hu_berlin.german.korpling.saltnpepper.pepper.pepperModules.impl.PepperManipulatorImpl;
import de.hu_berlin.german.korpling.saltnpepper.pepper.pepperParams.ExporterParams;
import de.hu_berlin.german.korpling.saltnpepper.pepper.pepperParams.ImporterParams;
import de.hu_berlin.german.korpling.saltnpepper.pepper.pepperParams.ModuleParams;
import de.hu_berlin.german.korpling.saltnpepper.pepper.pepperParams.PepperJobParams;
import de.hu_berlin.german.korpling.saltnpepper.pepper.pepperParams.PepperParams;
import de.hu_berlin.german.korpling.saltnpepper.pepper.pepperParams.PepperParamsFactory;
import de.hu_berlin.german.korpling.saltnpepper.salt.saltCommon.sCorpusStructure.SCorpusGraph;
import de.hu_berlin.german.korpling.saltnpepper.salt.saltCore.SElementId;

/**
 * This class tests if the pepper frameworks sets the resource folder of every module correctly.
 * Also checks if several modules of same class gets same folder.
 * <p>
 * The following operations are tested:
 * <ul>
 *   <li>{@link de.hu_berlin.german.korpling.saltnpepper.pepper.pepperFW.PepperJob#start() <em>Start</em>}</li>
 * </ul>
 * </p>
 */
public class ResourceFolderTest extends TestCase 
{
	/**
	 * The fixture for this Pepper Job test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 */
	protected PepperConverter fixture = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 */
	public static void main(String[] args) {
		TestRunner.run(ResourceFolderTest.class);
	}

	/**
	 * Constructs a new Pepper Job test case with the given name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 */
	public ResourceFolderTest(String name) {
		super(name);
	}

	/**
	 * Sets the fixture for this Pepper Job test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 */
	protected void setFixture(PepperConverter fixture) {
		this.fixture = fixture;
	}

	/**
	 * Returns the fixture for this Pepper Job test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 */
	protected PepperConverter getFixture() {
		return fixture;
	}

	/**
	 * <!-- begin-user-doc -->
	 * @see junit.framework.TestCase#setUp()
	 */
	@Override
	protected void setUp() throws Exception 
	{
		setFixture(PepperFWFactory.eINSTANCE.createPepperConverter());
		usedResFolders= new Hashtable<String, URI>();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see junit.framework.TestCase#tearDown()
	 *
	 */
	@Override
	protected void tearDown() throws Exception {
		setFixture(null);
	}
	
	public static class TestImporter extends PepperImporterImpl
	{
		public TestImporter()
		{
			this.name= "TestImporter";
			this.setSymbolicName(this.getClass().getName());
		}
		@Override
		public void importCorpusStructure(SCorpusGraph corpusGraph)
		{
			assertNotNull(this.getResources());
			assertTrue(this.getResources().isFile());
			if (usedResFolders.containsKey(this.getSymbolicName()))
				assertEquals(usedResFolders.get(this.getSymbolicName()), this.getResources());
			else usedResFolders.put(this.getSymbolicName(), this.getResources());
		}
		
		@Override
		public void start(SElementId sElementId) 
		{
			assertNotNull(this.getResources());
			if (usedResFolders.containsKey(this.getSymbolicName()))
				assertEquals(usedResFolders.get(this.getSymbolicName()), this.getResources());
			else usedResFolders.put(this.getSymbolicName(), this.getResources());
		}
	}
	
	public static class TestExporter extends PepperExporterImpl
	{
		public TestExporter()
		{
			this.name= "TestExporter";
			this.setSymbolicName(this.getClass().getName());
		}
		
		@Override
		public void start(SElementId sElementId) 
		{
			assertNotNull(this.getResources());
			assertTrue(this.getResources().isFile());
			if (usedResFolders.containsKey(this.getSymbolicName()))
				assertEquals(usedResFolders.get(this.getSymbolicName()), this.getResources());
			else usedResFolders.put(this.getSymbolicName(), this.getResources());
		}
	}
	
	public static class TestManipulator extends PepperManipulatorImpl
	{	
		public TestManipulator()
		{
			this.name= "TestManipulator";
			this.setSymbolicName(this.getClass().getName());
		}
		
		@Override
		public void start(SElementId sElementId) 
		{
			assertNotNull(this.getResources());
			assertTrue(this.getResources().isFile());
			if (usedResFolders.containsKey(this.getSymbolicName()))
				assertEquals(usedResFolders.get(this.getSymbolicName()), this.getResources());
			else usedResFolders.put(this.getSymbolicName(), this.getResources());
		}
	}
	
	/**
	 * Stores the URI�s of each Module 
	 */
	private static Hashtable<String, URI> usedResFolders= null;
	
	/**
	 * Checks for one importer, one exporter and one manipulator, if everyone has a resource folder.
	 * @throws IOException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 *
	 */
	//TODO fixme
	public void testTMPFolder() throws IOException, InstantiationException, IllegalAccessException
	{
//		PepperConverterAdapter pepperAdapter= new PepperConverterAdapter();
//		PepperParams pepperParams= null;
//		
//		{//set pepper module classes to test
//			@SuppressWarnings("unchecked")
//			Class[] pepperModuleClasses= {TestImporter.class, TestManipulator.class, TestExporter.class};
//			pepperAdapter.setPepperModuleClasses(pepperModuleClasses);
//		}//set pepper module classes to test
//		
//		{//create pepper params
//			pepperParams= PepperParamsFactory.eINSTANCE.createPepperParams();
//			PepperJobParams pepperJobParams= PepperParamsFactory.eINSTANCE.createPepperJobParams();
//			pepperParams.getPepperJobParams().add(pepperJobParams);
//			pepperJobParams.setId(0);
//			
//			URI alibiUri= URI.createFileURI(".");
//			
//			{//set importer
//				ImporterParams imParams= PepperParamsFactory.eINSTANCE.createImporterParams();
//				imParams.setModuleName("TestImporter");
//				imParams.setSourcePath(alibiUri);
//				pepperJobParams.getImporterParams().add(imParams);	
//			}//set importer
//			
//			{//set manipulator
//				ModuleParams modParams= PepperParamsFactory.eINSTANCE.createModuleParams();
//				modParams.setModuleName("TestManipulator");
//				pepperJobParams.getModuleParams().add(modParams);	
//			}//set manipulator
//			
//			{//set exporter
//				ExporterParams exParams= PepperParamsFactory.eINSTANCE.createExporterParams();
//				exParams.setModuleName("TestExporter");
//				exParams.setDestinationPath(alibiUri);
//				pepperJobParams.getExporterParams().add(exParams);	
//			}//set exporter
//		}//create pepper params
//		
//		{//start conversion
//			pepperAdapter.start(pepperParams);
//		}//start conversion
	}
	
	/**
	 * Checks for two importers, two exporter and two manipulator of same type, if ever kind of module 
	 * has the same resource folder.
	 * @throws IOException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 *
	 */
	//TODO fixme
//	public void testTMPFolder2() throws IOException, InstantiationException, IllegalAccessException
//	{
//		PepperConverterAdapter pepperAdapter= new PepperConverterAdapter();
//		PepperParams pepperParams= null;
//		
//		{//set pepper module classes to test
//			@SuppressWarnings("unchecked")
//			Class[] pepperModuleClasses= {TestImporter.class, TestManipulator.class, TestExporter.class};
//			pepperAdapter.setPepperModuleClasses(pepperModuleClasses);
//		}//set pepper module classes to test
//		
//		{//create pepper params
//			pepperParams= PepperParamsFactory.eINSTANCE.createPepperParams();
//			PepperJobParams pepperJobParams= PepperParamsFactory.eINSTANCE.createPepperJobParams();
//			pepperParams.getPepperJobParams().add(pepperJobParams);
//			pepperJobParams.setId(0);
//			
//			URI alibiUri= URI.createFileURI(".");
//			
//			{//set importer
//				ImporterParams imParams= PepperParamsFactory.eINSTANCE.createImporterParams();
//				imParams.setModuleName("TestImporter");
//				imParams.setSourcePath(alibiUri);
//				pepperJobParams.getImporterParams().add(imParams);	
//				
//				imParams= PepperParamsFactory.eINSTANCE.createImporterParams();
//				imParams.setModuleName("TestImporter");
//				imParams.setSourcePath(alibiUri);
//				pepperJobParams.getImporterParams().add(imParams);	
//			}//set importer
//			
//			{//set manipulator
//				ModuleParams modParams= PepperParamsFactory.eINSTANCE.createModuleParams();
//				modParams.setModuleName("TestManipulator");
//				pepperJobParams.getModuleParams().add(modParams);	
//				
//				modParams= PepperParamsFactory.eINSTANCE.createModuleParams();
//				modParams.setModuleName("TestManipulator");
//				pepperJobParams.getModuleParams().add(modParams);
//			}//set manipulator
//			
//			{//set exporter
//				ExporterParams exParams= PepperParamsFactory.eINSTANCE.createExporterParams();
//				exParams.setModuleName("TestExporter");
//				exParams.setDestinationPath(alibiUri);
//				pepperJobParams.getExporterParams().add(exParams);	
//				
//				exParams= PepperParamsFactory.eINSTANCE.createExporterParams();
//				exParams.setModuleName("TestExporter");
//				exParams.setDestinationPath(alibiUri);
//				pepperJobParams.getExporterParams().add(exParams);
//			}//set exporter
//		}//create pepper params
//		
//		{//start conversion
//			pepperAdapter.start(pepperParams);
//		}//start conversion
//	}
	
} //PepperConvertJobTest