package ru.pattern.structural.decorator;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.zip.*;

public class DecoderMessageDecoratorRunner {
    public static void main(String[] args) {
        MessageSource messageSource = new CompressionMessageDecorator(
                new EncryptionMessageDecorator(
                        new FileMessageSource("resources\\decoder\\mess.txt")));
        messageSource.writeData("Congratulation! Mister Win, you win a good prize!!!");
        String mess = messageSource.readData();
        System.out.println("Read data: " + mess);
    }
}

interface MessageSource {

    void writeData(String message);

    String readData();
}

class FileMessageSource implements MessageSource {

    private final Path path;

    public FileMessageSource(String pathName) {
        this.path = Paths.get(pathName);
    }

    @Override
    public void writeData(String message) {
        try (BufferedWriter writer = Files.newBufferedWriter(path)) {
            writer.write(message);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public String readData() {
        try (BufferedReader reader = Files.newBufferedReader(path)) {
            return reader.readLine();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}

abstract class MessageSourceDecorator implements MessageSource {

    protected final MessageSource wrappee;

    public MessageSourceDecorator(MessageSource messageSource) {
        this.wrappee = messageSource;
    }

    @Override
    public void writeData(String message) {
        wrappee.writeData(message);
    }

    @Override
    public String readData() {
        return wrappee.readData();
    }
}

class EncryptionMessageDecorator extends MessageSourceDecorator {

    public EncryptionMessageDecorator(MessageSource messageSource) {
        super(messageSource);
    }

    @Override
    public void writeData(String message) {
        super.writeData(encode(message));
    }

    @Override
    public String readData() {
        return decode(super.readData());
    }

    private String encode(String message) {
        return Base64.getEncoder().encodeToString(message.getBytes());
    }

    private String decode(String message) {
        return new String(Base64.getDecoder().decode(message.getBytes()));
    }
}

class CompressionMessageDecorator extends MessageSourceDecorator {

    public CompressionMessageDecorator(MessageSource messageSource) {
        super(messageSource);
    }

    @Override
    public void writeData(String message) {
        super.writeData(compress(message));
    }

    @Override
    public String readData() {
        return decompress(super.readData());
    }

    private String compress(String message) {
        ByteArrayOutputStream bout = null;
        DeflaterOutputStream dos = null;
        try {
            bout = new ByteArrayOutputStream(512);
            dos = new DeflaterOutputStream(bout, new Deflater(6));
            dos.write(message.getBytes());
            dos.close();
            bout.close();
            return Base64.getEncoder().encodeToString(bout.toByteArray());
        } catch (Exception ex) {
            ex.printStackTrace();
            if (dos != null) {
                try {
                    dos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (bout != null) {
                try {
                    bout.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return null;
    }

    private String decompress(String message) {
        byte[] data = Base64.getDecoder().decode(message);
        ByteArrayOutputStream bout = null;
        InflaterInputStream iin = null;
        try {
            bout = new ByteArrayOutputStream(1024);
            iin = new InflaterInputStream(new ByteArrayInputStream(data));
            int b;
            while ((b = iin.read()) != -1) {
                bout.write(b);
            }
            iin.close();
            bout.close();
            return new String(bout.toByteArray());
        } catch (Exception ex) {
            ex.printStackTrace();
            if (iin != null) {
                try {
                    iin.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (bout != null) {
                try {
                    bout.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return null;
    }
}

class ZipMessageDecorator extends MessageSourceDecorator {

    public ZipMessageDecorator(MessageSource messageSource) {
        super(messageSource);
    }

    @Override
    public void writeData(String message) {
        super.writeData(zip(message));
    }

    @Override
    public String readData() {
        return unzip(super.readData());
    }

    private String zip(String message) {
        try (ZipOutputStream zos = new ZipOutputStream(Files.newOutputStream(Paths.get("resources\\decoder\\mess.zip")))) {
            zos.putNextEntry(new ZipEntry("mess.txt"));
            zos.write(message.getBytes());
            zos.closeEntry();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return message;
    }

    private String unzip(String message) {
        try (ZipInputStream zin = new ZipInputStream(new FileInputStream("resources\\decoder\\mess.zip"))) {
            ZipEntry entry;
            String name;
            long size;
            while ((entry = zin.getNextEntry()) != null) {

                name = entry.getName(); // получим название файла
                size = entry.getSize();  // получим его размер в байтах
                System.out.printf("File name: %s \t File size: %d \n", name, size);

                // распаковка
                FileOutputStream fout = new FileOutputStream("resources\\decoder" + name);
                for (int c = zin.read(); c != -1; c = zin.read()) {
                    fout.write(c);
                }
                fout.flush();
                zin.closeEntry();
                fout.close();
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        return message;
    }


}