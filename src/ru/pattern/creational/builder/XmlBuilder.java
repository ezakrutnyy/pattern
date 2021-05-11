package ru.pattern.creational.builder;

/**
 * Created by Евгений on 17.08.2018.
 */
public interface XmlBuilder {

    public void startXmlProcess(XmlRequest xmlRequest);

    public void validateXmlParams();

    public void getDataXml();

    public void createXml();

    public void saveXml();

    public XmlResponce getXmlResponce();

}
