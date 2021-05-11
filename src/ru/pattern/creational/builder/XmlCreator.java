package ru.pattern.creational.builder;

/**
 * Created by Евгений on 17.08.2018.
 */
public class XmlCreator {
    public static void main(String[] args) {

        XmlDirector director = new XmlDirector(new XmlDynamicPayBuilder());
        XmlRequest request = new XmlRequest();
        request.setPath("D:\\envelope");
        director.startProcess(request);
        director.process();
        XmlResponce responce = director.endProcess();
        System.out.println(responce.getStatus()+ " "+responce.getDescription());

    }
}
