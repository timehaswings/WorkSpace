package com.wings.xml;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

public class MyDom {

	public Document getDocument(File file){
		Document document=null;
		try {
			SAXReader reader=new SAXReader();
			document=reader.read(file);
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		return document;
	}
	
	public Element getRootElement(File file){
		Element root=null;
		try {
			SAXReader reader=new SAXReader();
			Document document=reader.read(file);
			root=document.getRootElement();
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		return root;
	}
	
	public Element getRoot(Document document){
        return document.getRootElement();
    }
	
	public void getAllNode(Document document){
		Iterator<Node> it = document.nodeIterator();
		 while(it.hasNext()){
			Node node = it.next();
            String name = node.getName();
            System.out.println(name);
            //继续取出下面的子节点，只有标签节点才有子节点
            if(node instanceof Element){   //这里限制了元素一定是Element，如果不是Elment就不会进入这个语句块中
                Element element = (Element)node;
                Iterator<Node> it1 = element.nodeIterator(); //在这里Node是类型，但是类型Element属于Node的类型（节点类型）
                while(it1.hasNext()){
                    Node node1 = it1.next();
                    System.out.println(node1.getName());
                }
            }
		 }
	}
	
	//递归的将每个标签节点输出
    public void getChildElement(Element element){
        System.out.println(element.getName()+":");
        List<Attribute> attribute = element.attributes();
        for(Attribute attr : attribute){
            System.out.print(attr.getName()+"="+attr.getValue()+",");
        }
        String content = element.getText();
        if(!content.equals("") && !content.equals("\n"))
        	System.out.println(content);
        List<Element> list = element.elements();
        for(Element li : list){
            getChildElement(li);
        }
    }
    
    //输出根标签以及根标签下的各级标签
    public void getNode(File file){
        getChildElement(getRoot(getDocument(file)));
    }
    
    public void getRootNode(Element element){
    	System.out.println(element.getName()+":---------");
        List<Attribute> attribute = element.attributes();
        for(Attribute attr : attribute){
        	String t=attr.getValue();
        	String n=attr.getName();
        	if(!t.equals("") && !t.equals("false") && !n.equals("package"))
        		System.out.print(attr.getName()+"="+attr.getValue()+",");
        }
        System.out.println();
        List<Element> list = element.elements();
        for(Element li : list){
        	getRootNode(li);
        }
    }
    
    public void getAllRootNode(File file){
    	getRootNode(getRoot(getDocument(file)));
    }
}
