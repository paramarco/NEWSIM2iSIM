package newSIM2iSIMConvertor;

import java.util.Vector;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XPathHelper {
	public XPathHelper() {
	}

	public String getSubFieldData(Document document, String fieldName, String subfieldName) {
		return getSubFieldDataByNodeName(document, "fields", fieldName, subfieldName);
	}

	public String getFieldData(Document document, String fieldName) {
		return getFieldDataByNodeName(document, "fields", fieldName);
	}

	public String getInfoFieldData(Document document, String fieldName) {
		return getFieldDataByNodeName(document, "info", fieldName);
	}

	private String getSubFieldDataByNodeName(Document document, String nodeName, String fieldName, String subfieldName) {
		String retVal = "";
		XPathFactory factory = XPathFactory.newInstance();
		XPath xpath = factory.newXPath();
		try {
			XPathExpression expr = xpath.compile("/fdr/" + nodeName + "/" + fieldName + "/" + subfieldName);
			Object result = expr.evaluate(document, XPathConstants.NODE);
			retVal = getTextFromTextNode((Node) result);
		} catch (XPathExpressionException e) {
			e.printStackTrace();
			return retVal;
		}
		return retVal;
	}

	private String getFieldDataByNodeName(Document document, String nodeName, String fieldName) {
		String retVal = "";
		XPathFactory factory = XPathFactory.newInstance();
		XPath xpath = factory.newXPath();
		try {
			XPathExpression expr = xpath.compile("/fdr/" + nodeName + "/" + fieldName);
			Object result = expr.evaluate(document, XPathConstants.NODE);
			retVal = getTextFromTextNode((Node) result);
		} catch (XPathExpressionException e) {
			e.printStackTrace();
			return retVal;
		}
		return retVal;
	}

	@SuppressWarnings("unused")
	private Vector<String> getErrorFieldData(Document document) {
		XPathFactory factory = XPathFactory.newInstance();
		XPath xpath = factory.newXPath();
		Vector<String> errMsgs = new Vector<String>();
		try {
			XPathExpression expr = xpath.compile("/fdr/errors/*");
			Object result = expr.evaluate(document, XPathConstants.NODESET);
			NodeList nodelist = (NodeList) result;
			for (int i = 0; i < nodelist.getLength(); i++) {
				errMsgs.add(getTextFromTextNode(nodelist.item(i)));
			}
		} catch (XPathExpressionException e) {
			e.printStackTrace();
			
		}
		return errMsgs;
	}

	private String getTextFromTextNode(Node node) {
		String retVal = "";
		NodeList nodeList = node.getChildNodes();
		for (int i = 0; i < nodeList.getLength(); i++) {
			node = nodeList.item(i);
			if (node.getNodeType() == Node.TEXT_NODE) {
				return retVal = node.getNodeValue();
			}
		}
		return retVal;
	}
}
