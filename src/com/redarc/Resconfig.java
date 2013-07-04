package com.redarc;

import java.io.File;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.VisitorSupport;
import org.dom4j.io.*;
import org.dom4j.util.AttributeHelper;

public class Resconfig {
	private Document doc;
	private Resconfig(){
		SAXReader saxreader = new SAXReader();
		try {
			doc = saxreader.read(new File(/*System.getProperty("user.dir") + File.separator + */ "config.xml"));
			Element root = doc.getRootElement();
			root.accept(new ResVisitor());
			
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}
	public static Resconfig getInstance(){
		return new Resconfig();
	}
	
	public String getByTagName(String tagName){
		return null;
	}
	
	public class ResVisitor extends VisitorSupport{
		public void visit(Element elem){
			System.out.println(elem.getText());
		}
		public void visit(Attribute attr){
			System.out.println(attr.getName());
		}
	}

}
