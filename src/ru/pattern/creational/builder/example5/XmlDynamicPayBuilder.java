package ru.pattern.creational.builder.example5;

import org.apache.commons.lang3.StringUtils;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by Евгений on 17.08.2018.
 */
public class XmlDynamicPayBuilder implements XmlBuilder {

    private XmlRequest xmlRequest;
    private XmlResponce xmlResponce;

    @Override
    public void startXmlProcess(XmlRequest xmlRequest) {
        this.xmlRequest = xmlRequest;
        this.xmlResponce = new XmlResponce("OK");
    }

    @Override
    public void validateXmlParams() {

        // Проверим что существует каталог для сохранения файла
        if (StringUtils.isBlank(xmlRequest.getPath())) {
            xmlResponce = new XmlResponce("ERROR", "Не задан каталог для сохранения файла .xml");
            return;
        }

        final Path path = Paths.get(xmlRequest.getPath());
        if (!Files.exists(path)) {
            xmlResponce = new XmlResponce("ERROR", "Не найден каталог для сохранения файла .xml");
            return;
        }


    }

    @Override
    public void getDataXml() {

    }

    @Override
    public void createXml() {

    }

    @Override
    public void saveXml() {

    }

    @Override
    public XmlResponce getXmlResponce() {
        return this.xmlResponce;
    }
}
