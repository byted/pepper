package org.corpus_tools.pepper.gui.client;

import javax.ws.rs.client.Client;

public class ServiceConnector {
	public static final String SERVICE_URL = "localhost:8080/pepper-rest/";
	
//	public static PepperModuleDesc demoGetModuleDesc(String query){
//		PepperModuleDesc moduleDesc = null;
//		try {
//			URL url = new URL(SERVICE_URL+query);
//			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//			connection.setRequestMethod("GET");
//			connection.setRequestProperty("Accept", MediaType.APPLICATION_XML.toString());
//			JAXBContext jc = JAXBContext.newInstance(PepperModuleDescMarshallable.class);
//			InputStream in = connection.getInputStream();
//			moduleDesc = ((PepperModuleDescMarshallable)jc.createUnmarshaller().unmarshal(in)).getPepperObject();
//		} catch (IOException | JAXBException e) {			
//			e.printStackTrace();
//		}
//		return moduleDesc;
//	}
	
	public void get(){
		
	}
}
