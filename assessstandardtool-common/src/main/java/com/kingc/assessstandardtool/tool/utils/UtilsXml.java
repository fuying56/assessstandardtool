package com.kingc.assessstandardtool.tool.utils;

import org.dom4j.Element;

public class UtilsXml{

    /**
     * 获取Element指定子节点的字符串
     * @param element
     * @param text
     * @return
     */
    public static String getElementText(Element element,String text){
        Element e = element.element(text);
        return e == null?null:e.getText().trim();
    }
    /**
     * 获取Element指定子节点的字符串
     * @param element
     * @param text
     * @return
     */
    public static Double getElementDouble(Element element,String text){
        String elementText = getElementText(element, text);
        return elementText == null?null:Double.valueOf(elementText);
    }
    /**
     * 获取Element指定子节点的字符串
     * @param element
     * @param text
     * @return
     */
    public static Integer getElementInteger(Element element,String text){
        String elementText = getElementText(element, text);
        return elementText == null?null:Integer.valueOf(elementText);
    }
    /**
     * 获取Element指定子节点的字符串
     * @param element
     * @param text
     * @return
     */
    public static Boolean getElementBoolean(Element element,String text){
        String elementText = getElementText(element, text);
        return elementText == null?null:Boolean.valueOf(elementText);
    }
}