package io.github.spafka.io;

import io.github.spafka.spark.util.Utils;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.junit.Test;
import scala.runtime.AbstractFunction0;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

@Slf4j
public class zerocopy {

    String HOST = "localhost";
    Integer PORT = 8000;
    String FILE_PATH = "./test.txt";


    @Test
    public void init() throws IOException {


        var file = new File(FILE_PATH);

        FileOutputStream fileOutputStream = new FileOutputStream(file);
        byte[] bytes = "deep dark fantasy\n".getBytes(StandardCharsets.UTF_8);
        IntStream.rangeClosed(1, 1000000).forEach(x -> {
            try {
                IOUtils.writeChunked(bytes, fileOutputStream);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        fileOutputStream.close();


    }

    @Test
    public void _1() throws IOException, InterruptedException {
        File file = new File(FILE_PATH);

        Utils.bytesToString(file.length());
        Socket socket = new Socket(HOST, PORT);
        Long time = (Long) Utils.timeTakenMs(new AbstractFunction0<Void>() {
            @SneakyThrows
            @Override
            public Void apply() {
                InputStream inputStream = new FileInputStream(file);
                OutputStream outputStream = new DataOutputStream(socket.getOutputStream());

                byte[] buffer = new byte[4096];
                while (inputStream.read(buffer) >= 0) {
                    outputStream.write(buffer);
                }

                outputStream.close();
                socket.close();
                inputStream.close();
                return null;
            }
        })._2();
        log.info("{}",time);


    }
}
