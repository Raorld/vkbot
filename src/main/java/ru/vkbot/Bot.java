package ru.vkbot;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import com.vk.api.sdk.client.TransportClient;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.GroupActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.httpclient.HttpTransportClient;
import com.vk.api.sdk.objects.messages.*;
import com.vk.api.sdk.queries.messages.MessagesGetLongPollHistoryQuery;

public class Bot {
    public static void main(String[] args) throws ClientException, ApiException, InterruptedException
    {
        TransportClient transportClient = new HttpTransportClient();
        VkApiClient vk = new VkApiClient(transportClient);
        Random random = new Random();
        Keyboard keyboard = new Keyboard();

        List<List<KeyboardButton>> allKey = new ArrayList<>();
        List<KeyboardButton> line1 = new ArrayList<>();
        List<KeyboardButton> line2 = new ArrayList<>();
        List<KeyboardButton> line3 = new ArrayList<>();
        line1.add(new KeyboardButton().setAction(new KeyboardButtonAction().setLabel("Первый сезон").setType(KeyboardButtonActionType.TEXT)).setColor(KeyboardButtonColor.DEFAULT));
        line1.add(new KeyboardButton().setAction(new KeyboardButtonAction().setLabel("Второй сезон").setType(KeyboardButtonActionType.TEXT)).setColor(KeyboardButtonColor.DEFAULT));
        line2.add(new KeyboardButton().setAction(new KeyboardButtonAction().setLabel("Третий сезон").setType(KeyboardButtonActionType.TEXT)).setColor(KeyboardButtonColor.DEFAULT));
        line2.add(new KeyboardButton().setAction(new KeyboardButtonAction().setLabel("Четвертый сезон").setType(KeyboardButtonActionType.TEXT)).setColor(KeyboardButtonColor.DEFAULT));
        line2.add(new KeyboardButton().setAction(new KeyboardButtonAction().setLabel("Пятый сезон").setType(KeyboardButtonActionType.TEXT)).setColor(KeyboardButtonColor.DEFAULT));
        line3.add(new KeyboardButton().setAction(new KeyboardButtonAction().setLabel("Шестой сезон").setType(KeyboardButtonActionType.TEXT)).setColor(KeyboardButtonColor.DEFAULT));
        line3.add(new KeyboardButton().setAction(new KeyboardButtonAction().setLabel("Седьмой сезон").setType(KeyboardButtonActionType.TEXT)).setColor(KeyboardButtonColor.DEFAULT));

        allKey.add(line1);
        allKey.add(line2);
        allKey.add(line3);



        keyboard.setButtons(allKey);
        GroupActor actor = new GroupActor(204504132, "006927667f795883f789af3b4b3d58b8de0e8f6dac4836dd7f6d8aaeac139b388bc6c3baa22c59369814f");
        Integer ts = vk.messages().getLongPollServer(actor).execute().getTs();
        while (true){
            MessagesGetLongPollHistoryQuery historyQuery =  vk.messages().getLongPollHistory(actor).ts(ts);
            List<Message> messages = historyQuery.execute().getMessages().getItems();
            if (!messages.isEmpty()){
                messages.forEach(message -> {

                    System.out.println(message.toString());
                    try {
                        if (message.getText().equals("Привет")){
                            vk.messages().send(actor).message("Привет, держи кнопки!").userId(message.getFromId()).randomId(random.nextInt(10000)).keyboard(keyboard).execute();
                        }
                        else if (message.getText().equals("Первый сезон")){
                            vk.messages().send(actor).message("слаби").userId(message.getFromId()).randomId(random.nextInt(10000)).execute();
                        }
                        else if (message.getText().equals("Второй сезон")){
                            vk.messages().send(actor).message("слаби").userId(message.getFromId()).randomId(random.nextInt(10000)).execute();
                        }
                        else if (message.getText().equals("Третий сезон")){
                            vk.messages().send(actor).message("слаби").userId(message.getFromId()).randomId(random.nextInt(10000)).execute();
                        }
                        else if (message.getText().equals("Четвертый сезон")){
                            vk.messages().send(actor).message("слаби").userId(message.getFromId()).randomId(random.nextInt(10000)).execute();
                        }
                        else if (message.getText().equals("Пятый сезон")){
                            vk.messages().send(actor).message("слаби").userId(message.getFromId()).randomId(random.nextInt(10000)).execute();
                        }
                        else if (message.getText().equals("Шестой сезон")){
                            vk.messages().send(actor).message("слаби").userId(message.getFromId()).randomId(random.nextInt(10000)).execute();
                        }
                        else if (message.getText().equals("Седьмой сезон")){
                            vk.messages().send(actor).message("слаби").userId(message.getFromId()).randomId(random.nextInt(10000)).execute();
                        }

                    }
                    catch (ApiException | ClientException e) {e.printStackTrace();}
                });
            }
            ts = vk.messages().getLongPollServer(actor).execute().getTs();
            Thread.sleep(500);
        }
    }
}