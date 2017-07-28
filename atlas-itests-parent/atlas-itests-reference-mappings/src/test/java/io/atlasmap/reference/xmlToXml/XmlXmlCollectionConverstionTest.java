package io.atlasmap.reference.xmlToXml;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.Test;

import io.atlasmap.api.AtlasContext;
import io.atlasmap.api.AtlasSession;
import io.atlasmap.core.DefaultAtlasContext;
import io.atlasmap.reference.AtlasMappingBaseTest;

public class XmlXmlCollectionConverstionTest extends AtlasMappingBaseTest {
    
    @Test 
    public void testProcessCollectionListSimple() throws Exception {
        AtlasContext context = atlasContextFactory.createContext(new File("src/test/resources/xmlToXml/atlasmapping-collection-list-simple.xml").toURI());
        
        // contact<>.firstName -> contact<>.name
        
        String input = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
        input += "<XmlOA>";
        for (int i = 0; i < 3; i++) {
            input += "<contact><firstName>name" + i + "</firstName></contact>";            
        }
        input += "</XmlOA>";

        AtlasSession session = context.createSession();
        session.setInput(input);
        context.process(session);
        
        Object object = session.getOutput();
        assertNotNull(object);
        assertTrue(object instanceof String);
        String output = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>";
        output += "<XmlOA>";
        for (int i = 0; i < 3; i++) {
            output += "<contact><name>name" + i + "</name></contact>";            
        }
        output += "</XmlOA>";
        assertEquals(output, (String) object);
    }      
    
    @Test 
    public void testProcessCollectionArraySimple() throws Exception {
        AtlasContext context = atlasContextFactory.createContext(new File("src/test/resources/xmlToXml/atlasmapping-collection-array-simple.xml").toURI());
        
        // contact[].firstName -> contact[].name
        
        String input = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
        input += "<XmlOA>";
        for (int i = 0; i < 3; i++) {
            input += "<contact><firstName>name" + i + "</firstName></contact>";            
        }
        input += "</XmlOA>";

        AtlasSession session = context.createSession();
        session.setInput(input);
        context.process(session);
        
        Object object = session.getOutput();
        assertNotNull(object);
        assertTrue(object instanceof String);
        
        String output = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>";
        output += "<XmlOA>";
        for (int i = 0; i < 3; i++) {
            output += "<contact><name>name" + i + "</name></contact>";            
        }
        output += "</XmlOA>";
        
        assertEquals(output, (String) object);
    }   
    
    @Test 
    public void testProcessCollectionToNonCollection() throws Exception {
        AtlasContext context = atlasContextFactory.createContext(new File("src/test/resources/xmlToXml/atlasmapping-collection-to-noncollection.xml").toURI());                     

        // contact<>.firstName -> contact.name
        
        String input = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
        input += "<XmlOA>";
        for (int i = 0; i < 3; i++) {
            input += "<contact><firstName>name" + i + "</firstName></contact>";            
        }
        input += "</XmlOA>";

        AtlasSession session = context.createSession();
        session.setInput(input);
        context.process(session);
        
        Object object = session.getOutput();
        assertNotNull(object);
        assertTrue(object instanceof String);
        String output = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>";
        output += "<XmlOA>";
        output += "<contact><name>name2</name></contact>";
        output += "</XmlOA>";
        assertEquals(output, (String) object);
    }
    
    @Test 
    public void testProcessCollectionFromNonCollection() throws Exception {
        AtlasContext context = atlasContextFactory.createContext(new File("src/test/resources/xmlToXml/atlasmapping-collection-from-noncollection.xml").toURI());
        
        // contact.firstName -> contact<>.name
        
        String input = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
        input += "<XmlOA>";
        input += "<contact><firstName>name76</firstName></contact>";
        input += "</XmlOA>";

        AtlasSession session = context.createSession();
        session.setInput(input);
        context.process(session);
        
        String output = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>";
        output += "<XmlOA>";
        output += "<contact><name>name76</name></contact>";
        output += "</XmlOA>";
        
        Object object = session.getOutput();
        assertNotNull(object);
        assertTrue(object instanceof String);
        assertEquals(output, (String) object);
    }
}