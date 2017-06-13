package ru.pattern.structural.bridge.example3;

/**
 * Created by Евгений on 15.01.2017.
 */
public class FileDownloaderBridgeDemo {
    public static void main(String[] args) {
        FileDownloaderAbstractionImpl downloader = new FileDownloaderAbstractionImpl(new LinuxFileDownloadImplementor());
        Object file = downloader.download("C://games/test.txt");
        downloader.store(file);
    }

}

interface FileDownloaderAbstraction {
    public Object download(String path);

    public boolean store(Object object);

    // 1-1 Изменение абстракции не меняет реализацию
    //FileDownloaderAbstraction
    //public boolean delete(String object);
}

class FileDownloaderAbstractionImpl implements FileDownloaderAbstraction {

    private FileDownloadImplementor provider = null;

    public FileDownloaderAbstractionImpl(FileDownloadImplementor provider) {
        super();
        this.provider = provider;
    }

    @Override
    public Object download(String path)
    {
        return provider.downloadFile(path);
    }

    @Override
    public boolean store(Object object)
    {
        return provider.storeFile(object);
    }
}

interface FileDownloadImplementor {
    public Object downloadFile(String path);

    public boolean storeFile(Object object);

    // 2-2 Изменение реализации не сказываетсяна абстракции
    //FileDownloaderAbstraction
    //public boolean delete(String object);
}

class LinuxFileDownloadImplementor implements FileDownloadImplementor {
    @Override
    public Object downloadFile(String path) {
        return new Object();
    }

    @Override
    public boolean storeFile(Object object) {
        System.out.println("File downloaded successfully in LINUX !!");
        return true;
    }

}

class WindowsFileDownloadImplementor implements FileDownloadImplementor {
    @Override
    public Object downloadFile(String path) {
        return new Object();
    }

    @Override
    public boolean storeFile(Object object) {
        System.out.println("File downloaded successfully in WINDOWS !!");
        return true;
    }

}