package de.hu_berlin.german.korpling.saltnpepper.pepper.gui.components;

import com.vaadin.annotations.DesignRoot;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.declarative.Design;

import de.hu_berlin.german.korpling.saltnpepper.pepper.gui.controller.PepperGUIComponentDictionary;
import de.hu_berlin.german.korpling.saltnpepper.pepper.gui.controller.PepperGUIController;

@DesignRoot
public class PepperGUI extends VerticalLayout implements PepperGUIComponentDictionary{
	protected static final String GUI_WIDTH = "1024px";
	protected static final String GUI_HEIGHT = "768px";
	
	private Button btnNewWorkflow;
	private Button btnLoadWorkflow;
	private Button btnAbout;
	private Button btnImporters;
	private Button btnExporters;
	private Button btnManipulators;
	private Button btnResults;

	private VerticalLayout main;
		
	public PepperGUI(PepperGUIController GUIcontroller){	
		setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
		Design.read("gui.xml", this);
		main.addComponent(new PepperGUIMain(GUIcontroller));
		Design.read("importers.xml", main);
		btnNewWorkflow.setId(ID_BUTTON_NEW);
		btnLoadWorkflow.setId(ID_BUTTON_LOAD);
		btnAbout.setId(ID_BUTTON_ABOUT);
		btnImporters.setId(ID_BUTTON_IMPORTERS);
		btnExporters.setId(ID_BUTTON_EXPORTERS);
		btnManipulators.setId(ID_BUTTON_MANIPULATORS);
		btnResults.setId(ID_BUTTON_RESULTS);
		
		btnNewWorkflow.addClickListener(GUIcontroller);		
		btnLoadWorkflow.addClickListener(GUIcontroller);
		btnAbout.addClickListener(GUIcontroller);
		btnImporters.addClickListener(GUIcontroller);
		btnExporters.addClickListener(GUIcontroller);
		btnManipulators.addClickListener(GUIcontroller);
		btnResults.addClickListener(GUIcontroller);		
	}
}
