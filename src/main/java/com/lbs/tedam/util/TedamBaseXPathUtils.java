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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;
import java.io.IOException;

public class TedamBaseXPathUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(TedamBaseXPathUtils.class);

    protected TedamBaseXPathUtils() {
        // TedamBaseXPathUtils private constructor
    }

    /**
     * @param snapshot
     * @param compileParameter
     * @return <br>
     * This procedure returns an object of type Node depending on the given snapshot element and given compile parameter.
     * @author Tarik.Mikyas
     */
    protected static Node getNodeFromSnapshot(Element snapshot, String compileParameter) {
        XPath xpath = XPathFactory.newInstance().newXPath();
        XPathExpression tagExpr;
        Node node = null;
        try {
            tagExpr = xpath.compile(compileParameter);
            node = (Node) tagExpr.evaluate(snapshot, XPathConstants.NODE);
        } catch (Exception e) {
            LOGGER.error("" + e);
            return null;
        }
        return node;
    }

    /**
     * @param snapshot
     * @param compileParameter
     * @return <br>
     * This procedure returns an object of type NodeList depending on the given snapshot element and given compile parameter.
     * @author Tarik.Mikyas
     */
    protected static NodeList getNodeListFromSnapshot(Element snapshot, String compileParameter) {
        XPath xpath = XPathFactory.newInstance().newXPath();
        XPathExpression tagExpr;
        NodeList nodeList = null;
        try {
            tagExpr = xpath.compile(compileParameter);
            nodeList = (NodeList) tagExpr.evaluate(snapshot, XPathConstants.NODESET);
        } catch (Exception e) {
            LOGGER.error("" + e);
        }
        return nodeList;
    }

    /**
     * @param snapshot
     * @param compileParameter
     * @return <br>
     * This procedure returns an object of type Node depending on the given document element and given compile parameter.
     * @author Tarik.Mikyas
     */
    protected static Node getNodeFromDocument(Document document, String compileParameter) {
        XPath xpath = XPathFactory.newInstance().newXPath();
        XPathExpression tagExpr;
        Node node = null;
        try {
            tagExpr = xpath.compile(compileParameter);
            node = (Node) tagExpr.evaluate(document, XPathConstants.NODE);
        } catch (Exception e) {
            LOGGER.error("" + e);
        }
        return node;
    }

    /**
     * @param snapshot
     * @param compileParameter
     * @return <br>
     * This procedure returns an object of type NodeList depending on the given document element and given compile parameter.
     * @author Tarik.Mikyas
     */
    protected static NodeList getNodeListFromDocument(Document document, String compileParameter) {
        XPath xpath = XPathFactory.newInstance().newXPath();
        XPathExpression tagExpr;
        NodeList nodeList = null;
        try {
            tagExpr = xpath.compile(compileParameter);
            nodeList = (NodeList) tagExpr.evaluate(document, XPathConstants.NODESET);
        } catch (Exception e) {
            LOGGER.error("" + e);
        }
        return nodeList;
    }

    /**
     * @param filePath
     * @return <br>
     * Creates and returns a Document object according to the given filePath of the given String.
     * @author Tarik.Mikyas
     */
    protected static Document getDocumentFromFilePath(String filePath) {
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        Document document = null;
        try {
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            document = docBuilder.parse("file:///" + filePath);
        } catch (ParserConfigurationException e) {
            LOGGER.error("" + e);
        } catch (SAXException e) {
            LOGGER.error("" + e);
        } catch (IOException e) {
            LOGGER.error("" + e);
        }
        return document;
    }

}
