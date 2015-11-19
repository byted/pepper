package org.corpus_tools.pepper.service.adapters;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlValue;

import org.corpus_tools.pepper.modules.PepperModuleProperty;
import org.corpus_tools.pepper.service.interfaces.PepperMarshallable;

@XmlRootElement
@XmlSeeAlso({String.class})
public class PepperModulePropertyMarshallable<PT> implements PepperMarshallable<PepperModuleProperty<PT>>{
		
	public PepperModulePropertyMarshallable(){		
	}
	
	@Override
	public PepperModuleProperty<PT> getPepperObject() {
		return new PepperModuleProperty<PT>(name, type, description, value, required);
	}
	
	private String name;
	
	@XmlElement
	public String getName(){		
		return name;
	}
	
	@XmlElement
	public void setName(String name){
		this.name = name;
	}
	
	private boolean required;
	
	@XmlElement
	public boolean getRequired(){
		return required;
	}
	
	@XmlElement
	public void setRequired(boolean required){
		this.required = required;
	}
	
	private Class<PT> type;
	
	@XmlElement
	public Class<PT> getType(){
		return type;
	}
	
	@XmlElement
	public void setType(Class<PT> type){
		this.type = type;
	}
	
	private String description;
	
	@XmlElement
	public String getDescription(){
		return description;
	}
	
	@XmlElement
	public void setDescription(String description){
		this.description = description;
	}
	
	/** SERVICE -> PEPPER, value is defaultValue
	 * 	PEPPER -> SERVICE, value is user modified value (can be default)
	 * */
	private PT value;
	
	@XmlElement()
	public PT getValue(){
		return value;
	}
	
	@XmlElement	
	public void setValue(PT value){
		this.value = value;
	}	
	
}
