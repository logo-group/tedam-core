/*
 * Copyright 2014-2019 Logo Business Solutions
 * (a.k.a. LOGO YAZILIM SAN. VE TIC. A.S)
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package com.lbs.tedam.util;

import com.lbs.tedam.exception.DocumentBuildException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.io.StringWriter;

/**
 * @author Tarik.Mikyas Element and Document object<br>
 */
public class TedamDOMUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(TedamDOMUtils.class);

    private TedamDOMUtils() {
        // TedamDOMUtils private constructor
    }

    /**
     * It opens the xml document in the filePath given as parameter and returns the document in Document type.
     *
     * @param filePath
     * @return
     * @throws ParserConfigurationException
     * @throws SAXException
     * @throws IOException
     * @author Ozgur.Ozbil
     */
    public static Document domParserStarter(String filePath) {
        Document document = null;
        DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
        domFactory.setValidating(false);
        try {
            domFactory.setFeature("http://xml.org/sax/features/namespaces", false);
            domFactory.setFeature("http://xml.org/sax/features/validation", false);
            domFactory.setFeature("http://apache.org/xml/features/nonvalidating/load-dtd-grammar", false);
            domFactory.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);
            domFactory.setNamespaceAware(false);
            DocumentBuilder builder = domFactory.newDocumentBuilder();
            File fXmlFile = new File(filePath);
            document = builder.parse(fXmlFile);
        } catch (ParserConfigurationException e) {
            LOGGER.error("" + e);
        } catch (SAXException e) {
            LOGGER.error("" + e);
        } catch (IOException e) {
            LOGGER.error("" + e);
        }
        return document;
    }

    /**
     * @param node
     * @return <br>
     * returns true if the type of the incoming Node object is Node.TEXT_NODE or Node.COMMENT_NODE; returns false if not
     * @author Tarik.Mikyas
     */
    public static boolean isDummyNode(Node node) {
        if (node.getNodeType() == Node.TEXT_NODE || node.getNodeType() == Node.COMMENT_NODE) {
            return true;
        }
        return false;
    }

    /**
     * This method converts the xml file from the Element class into a single line in the String type.
     *
     * @param element
     * @return
     * @throws TransformerException
     * @author Tarik.Mikyas
     */
    public static String elementToString(Element element) {
        String result = "";
        try {
            StringWriter sw = new StringWriter();
            TransformerFactory factory = TransformerFactory.newInstance();
			Transformer serializer = factory.newTransformer();
            serializer.transform(new DOMSource(element), new StreamResult(sw));
            result = sw.toString();
        } catch (TransformerException e) {
            LOGGER.error("transform failed", e);
        }
        return result;
    }

    /**
     * The snapshot parameter returns the formName information from the xml file in path.
     *
     * @param filePath
     * @return
     * @author Ozgur.Ozbil
     */
    public static String getExpectedFormName(String filePath) {
        if (filePath.isEmpty() || filePath.endsWith(Constants.FILE_EXTENSION_PDF)) {
            return "";
        }
        Document doc;
        try {
            // The xml opens.
            doc = TedamDOMUtils.domParserStarter(filePath);
            // From within formName information is returned.
            if (doc == null) {
                throw new DocumentBuildException("From the domParserStarter procedure, the document object returned null.");
            }
            return TedamXPathUtils.getFormNameFromSnapshot(doc.getDocumentElement());
        } catch (DocumentBuildException e) {
            LOGGER.error("" + e);
            return Constants.EMPTY_STRING;
        }

    }
}
