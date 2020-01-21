package ru.pattern.creational.builder.example5;

/**
 * Created by Евгений on 17.08.2018.
 */
public class XmlDirector {

    final private XmlBuilder xmlBuilder;

    public XmlDirector(final XmlBuilder xmlBuilder) {
        this.xmlBuilder = xmlBuilder;
    }

    public void startProcess(XmlRequest xmlRequest) {
        xmlBuilder.startXmlProcess(xmlRequest);
    }

    public XmlResponce endProcess() {
        return xmlBuilder.getXmlResponce();
    }

    public void process() {
        xmlBuilder.validateXmlParams();
        if (!checkXmlResponce())
            return;
        xmlBuilder.getDataXml();
        xmlBuilder.createXml();
        xmlBuilder.saveXml();

    }

    public boolean checkXmlResponce() {
        final XmlResponce xmlResponce = xmlBuilder.getXmlResponce();
        if (xmlResponce.getStatus().equals("ERROR")) {
            return false;
        }
        return  true;
    }


}
