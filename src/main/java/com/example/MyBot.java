package com.example;

import java.io.InputStream;
import java.net.URL;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.GetFile;
import org.telegram.telegrambots.meta.api.objects.File;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.PhotoSize;
import org.telegram.telegrambots.meta.api.objects.Update;

public class MyBot extends TelegramLongPollingBot {
    public MyBot(){
        // @SuppressWarnings("enum Identifier")
    };

    @Override
    public void onUpdateReceived(Update update) {
        // System.out.println(update.getMessage().getText());

        if (update.hasMessage()) {
            Message message = update.getMessage();
            List<PhotoSize> photo = message.getPhoto();
            for (PhotoSize PhotoSize : photo) {
                try {
                    System.out.println(" The Photo is : " + message.getPhoto());
                    saveFileFolder(PhotoSize.getFileId(), "Sanjar.png");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public String getBotToken() {
        return "6916303667:AAEYuJeum7d9zKqMX_SyrBgq8ssm7Kd4WP4";
    }

    @Override
    public String getBotUsername() {
        return "sanjarsFirstJavaTelegramBot";
    }

    private void saveFileFolder(String fileId, String fileName) throws Exception {
        GetFile getFile = new GetFile(fileId);

        File tgFile = execute(getFile);
        String fileUrl = tgFile.getFileUrl(getBotToken());

        URL url = new URL(fileUrl);
        InputStream inputStream = url.openStream();

        FileUtils.copyInputStreamToFile(inputStream, new java.io.File(fileName));
    }
}
