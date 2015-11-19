package org.corpus_tools.pepper.service.adapters;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

@XmlRootElement
@XmlSeeAlso({FormatDescMarshallable.class})
public class FormatDescs extends ArrayList<FormatDescMarshallable>{
		
	private List<FormatDescMarshallable> formatDescs;
	
	@XmlElement(name = "FormatDescss")
	public List<FormatDescMarshallable> getFormatDescs(){
		return formatDescs;
	}
}
