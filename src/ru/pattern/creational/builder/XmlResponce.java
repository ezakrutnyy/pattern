package ru.pattern.creational.builder;

/**
 * Created by Евгений on 17.08.2018.
 */
public class XmlResponce {
    private String status;
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public XmlResponce(String status, String description) {
        this.status = status;
        this.description = description;
    }

    public XmlResponce(String status) {
        this.status = status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
