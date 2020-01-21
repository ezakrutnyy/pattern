package ru.pattern.structural.decorator.example4;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.zip.Deflater;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.InflaterInputStream;

public class DataSourceDemo {
    public static void main(String[] args) {
        String path = "out/DataSource.txt";
        DataSource dataSource = new CompressionDecorator(new EncryptionDecorator(new FileDataSource(path)));
        dataSource.writeData("Hello worldewrrewrwerwerwerwerwerwerwerwerwer!");
        System.out.println(dataSource.readData());
    }
}

interface DataSource {

    void writeData(String data);

    String readData();
}

class FileDataSource implements DataSource {

    private String path;

    public FileDataSource(String path) {
        this.path = path;
    }


    @Override
    public void writeData(String data) {
        Path filePath = Paths.get(path);
        try(BufferedWriter writer = Files.newBufferedWriter(filePath)) {
            writer.write(data);
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public String readData() {
        String res = null;
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(path))) {
            res = reader.readLine();
        } catch (IOException ex) {
            System.out.println(ex);
        }
        return res;
    }
}

class DataSourceDecorator implements DataSource {

    private DataSource wrapper;

    public DataSourceDecorator(DataSource source) {
        this.wrapper = source;
    }

    @Override
    public void writeData(String data) {
        wrapper.writeData(data);
    }

    @Override
    public String readData() {
        return wrapper.readData();
    }
}

class EncryptionDecorator extends DataSourceDecorator {

    public EncryptionDecorator(DataSource source) {
        super(source);
    }

    @Override
    public void writeData(String data) {
        super.writeData(encode(data));
    }

    @Override
    public String readData() {
        return decode(super.readData());
    }

    private String encode(String data) {
        byte[] result = data.getBytes();
        for (int i = 0; i < result.length; i++) {
            result[i] += (byte) 1;
        }
        return Base64.getEncoder().encodeToString(result);
    }

    private String decode(String data) {
        byte[] result = Base64.getDecoder().decode(data);
        for (int i = 0; i < result.length; i++) {
            result[i] -= (byte) 1;
        }
        return new String(result);
    }
}

class CompressionDecorator extends DataSourceDecorator {

    public CompressionDecorator(DataSource source) {
        super(source);
    }

    @Override
    public void writeData(String data) {
        super.writeData(compress(data));
    }

    @Override
    public String readData() {
        return decompress(super.readData());
    }

    private String compress(String stringData) {
        byte[] data = stringData.getBytes();
        try {
            ByteArrayOutputStream bout = new ByteArrayOutputStream(512);
            DeflaterOutputStream dos = new DeflaterOutputStream(bout, new Deflater(6));
            dos.write(data);
            dos.close();
            bout.close();
            return Base64.getEncoder().encodeToString(bout.toByteArray());
        } catch (Exception ex) {
            System.out.println(ex);
        }

        return null;
    }

    private String decompress(String stringData) {
        byte[] data = Base64.getDecoder().decode(stringData);
        try {
            InputStream in = new ByteArrayInputStream(data);
            InflaterInputStream iin = new InflaterInputStream(in);
            ByteArrayOutputStream bout = new ByteArrayOutputStream(512);
            int b;
            while ((b = iin.read()) != -1) {
                bout.write(b);
            }
            in.close();
            iin.close();
            bout.close();
            return new String(bout.toByteArray());
        } catch (Exception ex) {
            System.out.println(ex);
        }

        return null;
    }
}