import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.io.File;
import java.sql.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class AirsoftGearRentBot extends TelegramLongPollingBot {
    int counterMessage = 0;
    int idProduct = 0;
    String methodReceipt;
    LocalDate startDay;
    LocalDate endDay;
    List<CatalogItem> basket = new ArrayList<>();
    List<CatalogItem> catalog = new ArrayList<>();
    List<Order> orderList = new ArrayList<>();
    User user;
    Boolean isAdmin = false;
    String category;
    String name = "";
    String quantity = "";

    public static void main(String[] args) throws TelegramApiException {
        TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
        AirsoftGearRentBot bot = new AirsoftGearRentBot();
        botsApi.registerBot(bot);
    }

    @Override
    public void onUpdateReceived(Update update) {
        Message message = update.getMessage();
        if (update.hasMessage() && update.getMessage().hasText()){
            System.out.println(message.getFrom() + " wrote " + message.getText());
            switch (counterMessage){
                case 1:{
                    name = message.getText();
                    sendMessage(message.getChatId(), "Введите количество");
                    counterMessage++;
                    System.out.println(name + " name");
                    break;
                }
                case 2:{
                    quantity = message.getText();
                    sendMessage(message.getChatId(), "Меняем количество");
                    counterMessage++;
                }
                case 3:{
                    System.out.println(name + " nam " + quantity + " qun");
                    counterMessage = 1;

                    switch (category) {
                        case "Винтовки", "Пулемёты", "Дробовики", "Пистолеты", "Автоматические винтовки" -> {
                            String sqlQuery = "UPDATE public.Оружие SET \"Количество\" = " + quantity +
                                    " WHERE \"Название\" = '" + name + "'";
                            try{
                                Connection connection = createConnection();
                                Statement updateQuantityStatement = connection.createStatement();
                                int rowsAffected1 = updateQuantityStatement.executeUpdate(sqlQuery);
                                System.out.println("Изменено " + rowsAffected1 + " строк в таблице");
                                sendMessage(message.getChatId(), "Строка изменена");
                            } catch (SQLException e) {
                                throw new RuntimeException(e);
                            }
                        }
                        case "Расходники"->{
                            String sqlQuery = "UPDATE public.Расходники SET \"Количество\" = " + quantity +
                                    " WHERE \"Название\" = '" + name + "'";
                            try{
                                Connection connection = createConnection();
                                Statement updateQuantityStatement = connection.createStatement();
                                int rowsAffected1 = updateQuantityStatement.executeUpdate(sqlQuery);
                                System.out.println("Изменено " + rowsAffected1 + " строк в таблице");
                                sendMessage(message.getChatId(), "Строка изменена");
                            } catch (SQLException e) {
                                throw new RuntimeException(e);
                            }
                        }
                        case "Магазины"->{
                            String sqlQuery = "UPDATE public.Магазины SET \"Количество\" = " + quantity +
                                    " WHERE \"Название\" = '" + name + "'";
                            try{
                                Connection connection = createConnection();
                                Statement updateQuantityStatement = connection.createStatement();
                                int rowsAffected1 = updateQuantityStatement.executeUpdate(sqlQuery);
                                System.out.println("Изменено " + rowsAffected1 + " строк в таблице");
                                sendMessage(message.getChatId(), "Строка изменена");
                            } catch (SQLException e) {
                                throw new RuntimeException(e);
                            }
                        }
                        case "Радио_связь"->{
                            String sqlQuery = "UPDATE public.Радио_связь SET \"Количество\" = " + quantity +
                                    " WHERE \"Название\" = '" + name + "'";
                            try{
                                Connection connection = createConnection();
                                Statement updateQuantityStatement = connection.createStatement();
                                int rowsAffected1 = updateQuantityStatement.executeUpdate(sqlQuery);
                                System.out.println("Изменено " + rowsAffected1 + " строк в таблице");
                                sendMessage(message.getChatId(), "Строка изменена");
                            } catch (SQLException e) {
                                throw new RuntimeException(e);
                            }
                        }
                        case "Игровая_пиротехника"->{
                            String sqlQuery = "UPDATE public.Игровая_пиротехника SET \"Количество\" = " + quantity +
                                    " WHERE \"Название\" = '" + name + "'";
                            try{
                                Connection connection = createConnection();
                                Statement updateQuantityStatement = connection.createStatement();
                                int rowsAffected1 = updateQuantityStatement.executeUpdate(sqlQuery);
                                System.out.println("Изменено " + rowsAffected1 + " строк в таблице");
                                sendMessage(message.getChatId(), "Строка изменена");
                            } catch (SQLException e) {
                                throw new RuntimeException(e);
                            }
                        }
                        case "Обвесы"->{
                            String sqlQuery = "UPDATE public.Обвесы SET \"Количество\" = " + quantity +
                                    " WHERE \"Название\" = '" + name + "'";
                            try{
                                Connection connection = createConnection();
                                Statement updateQuantityStatement = connection.createStatement();
                                int rowsAffected1 = updateQuantityStatement.executeUpdate(sqlQuery);
                                System.out.println("Изменено " + rowsAffected1 + " строк в таблице");
                                sendMessage(message.getChatId(), "Строка изменена");
                            } catch (SQLException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    }
                }
            }
            switch (message.getText()){
                case "/start"-> {
                    user  = message.getFrom();
                    System.out.println("chatId " + message.getChatId());
                    sendMessage(message.getChatId(), "Вы клиент или админ?");
                    sendInlineKeyboard(message);
                }
                case "Корзина"->{
                    System.out.println("update");
                    System.out.println(basket.isEmpty());
                    if (basket.isEmpty()) {
                        sendMessage(message.getChatId(), "Корзина пустая");
                        break;
                    }
                    try {
                        sendBasket(message);
                    } catch (TelegramApiException e) {
                        throw new RuntimeException(e);
                    }
                }
                case "EnergeenotCompanyAdmin pivoIson"->{
                    sendMessage(message.getChatId(), "Вы вошли как админ");
                    sendAdminKeyBoard(message);
                    isAdmin = true;
                }
            }
        }else if(update.hasCallbackQuery()){
            Long chatId = update.getCallbackQuery().getMessage().getChatId();
            String callbackData = update.getCallbackQuery().getData();
            Message upd = update.getCallbackQuery().getMessage();
            String databaseName;
            String buttonText = update.getCallbackQuery().getData();
            CatalogItem selectedProduct = null;

// Поиск товара в списке `catalog` по тексту кнопки
            for (CatalogItem item : catalog) {
                if (buttonText.equals(String.valueOf(item.getIdItem()))) {
                    selectedProduct = item;
                    break;
                }
            }

            if (selectedProduct != null) {
                System.out.println("xz");
                basket.add(selectedProduct);
                System.out.println(basket.toString());
                sendMessage(chatId, "Вы добавили в корзину " + selectedProduct.getDescription());
                sendBasketKeyboard(upd);
            }
            int itemId;
            System.out.println(callbackData);
            if (callbackData.startsWith("increment_")){
                itemId = Integer.parseInt(callbackData.substring(10));
                for (CatalogItem item : basket) {
                    if (item.getIdItem() == itemId) {
                        item.setQuantity(item.getQuantity() + 1);
                        sendMessage(chatId, "Корзина обновлена, сверьтесь");
                    }
                }
            }else if (callbackData.startsWith("decrement_")) {
                itemId = Integer.parseInt(callbackData.substring(10));
                for (CatalogItem item : basket){
                    if (item.getIdItem() == itemId){
                        if (item.getQuantity() == 1){
                            basket.remove(item);
                        }
                        item.setQuantity(itemId - 1);
                        sendMessage(chatId, "Корзина обновлена, сверьтесь");
                    }
                }
            }
            if (callbackData.startsWith("day_")){
                sendMessage(chatId, "Вы выбрали днём начала аренды и получения снаряжения: " + callbackData.substring(4));
                startDay = LocalDate.parse(callbackData.substring(4));
                System.out.println(startDay);
                sendChooseDayEndKeyBoard(update.getCallbackQuery().getMessage());
            }
            if (callbackData.startsWith("day-")){
                sendMessage(chatId, "Вы выбрали днём завершения аренды и возврата снаряжения: " + callbackData.substring(4) + "\n" + "Вам следует вернуть снаряжение до 18.00 в пункт выдачи");
                endDay = LocalDate.parse(callbackData.substring(4));
                System.out.println(endDay);
                // вызов метода записи заказа в бд и убирание количества из каталога
                addToDatabaseOrder(upd);
            }

            if (callbackData.startsWith("admin_")){
                sendMessage(chatId, "Какую таблицу нужно изменить");
                sendCatalogKeyBoard(upd);
            }

            switch (callbackData) {
                case "Клиент" -> {
//                    System.out.println("Client");
                    sendMessage(chatId, "Вы клиент");
                    sendClientMenuKeyBoard(update.getCallbackQuery().getMessage());
                }
                case "Админ" -> {
                    System.out.println("admin");
                    sendMessage(chatId, "Введите пароль и логин");
                }
                case "Каталог" -> {
                    sendCatalogKeyBoard(update.getCallbackQuery().getMessage());
                }
                case "Корзина" -> {
                    System.out.println("callback");
                    System.out.println(basket.isEmpty());
                    if (basket.isEmpty()) {
                        sendMessage(chatId, "Корзина пустая");
                        break;
                    }
                    try {
                        sendBasket(upd);
                    } catch (TelegramApiException e) {
                        throw new RuntimeException(e);
                    }
                }
                case "Оружие" -> {
                    sendCatalogGunKeyBoard(update.getCallbackQuery().getMessage());
                }
                case "Пулемёты" -> {
                    category = "Пулемёты";
                    if (isAdmin){
                        sendMessage(chatId, "Введите название товара и новое количество товара");
                        counterMessage+=1;

                    }else {
                        performDatabaseOperations(chatId, upd, category);
                    }
                }
                case "Автоматические винтовки"->{
                    category = "Автоматические винтовки";
                    if (isAdmin){
                        sendMessage(chatId, "Введите название товара и новое описание");
                        counterMessage+=1;

                    }else {
                        performDatabaseOperations(chatId, upd, category);
                    }
                }
                case "Дробовики"->{
                    category = "Дробовики";
                    if (isAdmin){
                        sendMessage(chatId, "Введите название товара и новое описание");
                        counterMessage+=1;

                    } else {
                        performDatabaseOperations(chatId, upd, category);
                    }
                }
                case "Винтовки"-> {
                    category = "Винтовки";
                    if (isAdmin) {
                        sendMessage(chatId, "Введите название товара и новое описание");
                        counterMessage += 1;

                    } else {
                        performDatabaseOperations(chatId, upd, category);
                    }
                }
                case "Пистолеты"-> {
                    category = "Пистолеты";
                    if (isAdmin) {
                        sendMessage(chatId, "Введите название товара и новое описание");
                        counterMessage += 1;

                    } else {
                        performDatabaseOperations(chatId, upd, category);
                    }
                }
                case "Расходники"-> {
                    category = "Расходники";
                    if (isAdmin) {
                        sendMessage(chatId, "Введите название товара и новое описание");
                        counterMessage += 1;

                    } else {
                        performDatabaseConsumables(chatId, upd);
                    }
                }
                case "Магазины"-> {
                    category = "Магазины";
                    if (isAdmin) {
                        sendMessage(chatId, "Введите название товара и новое описание");
                        counterMessage += 1;

                    } else {
                        performDatabasesMagazineRadioPyroBody(chatId, upd, category);
                    }
                }
                case "Радиосвязь"-> {
                    category = "Радио_связь";
                    if (isAdmin) {
                        sendMessage(chatId, "Введите название товара и новое количество товара");
                        counterMessage += 1;

                    } else {
                        performDatabasesMagazineRadioPyroBody(chatId, upd, category);
                    }
                }
                case "Игровая пиротехника"-> {
                    category = "Игровая_пиротехника";
                    if (isAdmin) {
                        sendMessage(chatId, "Введите название товара и новое количество товара");
                        counterMessage += 1;

                    } else {
                        performDatabasesMagazineRadioPyroBody(chatId, upd, category);
                    }
                }
                case "Обвесы"-> {
                    category = "Обвесы";
                    if (isAdmin) {
                        sendMessage(chatId, "Введите название товара и новое количество товара");
                        counterMessage += 1;

                    } else {
                        performDatabasesMagazineRadioPyroBody(chatId, upd, category);
                    }
                }
                case "Назад"->{
                    sendCatalogKeyBoard(upd);
                }
                case "Заказать"->{
                    sendChooseMethodReceiptKeyBoard(upd);
                }
                case "Самовывоз" ->{
                    sendChooseDayKeyBoard(upd);
                    setMethodReceipt("Самовывоз");
                }
                case  "Курьер"->{
                    sendChooseDayKeyBoard(upd);
                    setMethodReceipt("Курьер");
                }

            }
        }
    }

    public InlineKeyboardMarkup createAdminKeyBoard() {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        InlineKeyboardButton inlineKeyboardButton1 = new InlineKeyboardButton();
        InlineKeyboardButton inlineKeyboardButton2 = new InlineKeyboardButton();
        inlineKeyboardButton1.setText("Изменять каталог");
        inlineKeyboardButton1.setCallbackData("admin_Изменить");
        inlineKeyboardButton2.setText("Посмотреть каталог");
        inlineKeyboardButton2.setCallbackData("Каталог");
        List<InlineKeyboardButton> keyboardButtonsRow1 = new ArrayList<>();
        keyboardButtonsRow1.add(inlineKeyboardButton1);
        keyboardButtonsRow1.add(inlineKeyboardButton2);
        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
        rowList.add(keyboardButtonsRow1);
        inlineKeyboardMarkup.setKeyboard(rowList);
        return inlineKeyboardMarkup;
    }

    public void sendAdminKeyBoard(Message message){
        String chatId = String.valueOf(message.getChatId());
        InlineKeyboardMarkup customKeyboard = createAdminKeyBoard();
        SendMessage sndMessage = new SendMessage();
        sndMessage.setChatId(chatId);
        sndMessage.setText("Выберите что нужно сделать");
        sndMessage.setReplyMarkup(customKeyboard);
        try{
            execute(sndMessage);
        }catch (TelegramApiException e){
            e.printStackTrace();
        }
    }

    public void setMethodReceipt(String methodReceipt) {
        this.methodReceipt = methodReceipt;
    }

    public void setStartDay(LocalDate startDay) {
        this.startDay = startDay;
    }

    public void setEndDay(LocalDate endDay) {
        this.endDay = endDay;
    }

    public String getMethodReceipt() {
        return methodReceipt;
    }

    public LocalDate getStartDay() {
        return startDay;
    }

    public LocalDate getEndDay() {
        return endDay;
    }



    public InlineKeyboardMarkup createChooseDayEndKeyboard(LocalDate startDay){
        InlineKeyboardMarkup keyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rows = new ArrayList<>();
        for (int i = 0; i < 4; i++){
            LocalDate day = startDay.plusDays(i);
            String formattedDate = day.format(DateTimeFormatter.ofPattern("dd.MM"));
            InlineKeyboardButton button = new InlineKeyboardButton();
            button.setText(formattedDate);
            button.setCallbackData("day-" + day);
            List<InlineKeyboardButton> row = new ArrayList<>();
            row.add(button);
            rows.add(row);
        }
        keyboardMarkup.setKeyboard(rows);
        return keyboardMarkup;
    }

    public void sendChooseDayEndKeyBoard(Message message){
        String chatId = String.valueOf(message.getChatId());
        InlineKeyboardMarkup customKeyboard = createChooseDayEndKeyboard(startDay);
        SendMessage sndMessage = new SendMessage();
        sndMessage.setChatId(chatId);
        sndMessage.setText("Выберите дату возврата товара");
        sndMessage.setReplyMarkup(customKeyboard);
        try{
            execute(sndMessage);
        }catch (TelegramApiException e){
            e.printStackTrace();
        }
    }

    public InlineKeyboardMarkup createChooseDayKeyboard(){
        LocalDate curentDate = LocalDate.now();
        InlineKeyboardMarkup keyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rows = new ArrayList<>();
        for (int i = 0; i < 14; i++){
            LocalDate day = curentDate.plusDays(i);
            String formattedDate = day.format(DateTimeFormatter.ofPattern("dd.MM"));
            InlineKeyboardButton button = new InlineKeyboardButton();
            button.setText(formattedDate);
            button.setCallbackData("day_" + day);
            List<InlineKeyboardButton> row = new ArrayList<>();
            row.add(button);
            rows.add(row);
        }
        keyboardMarkup.setKeyboard(rows);
        return keyboardMarkup;
    }

    public void sendChooseDayKeyBoard(Message message){
        String chatId = String.valueOf(message.getChatId());
        InlineKeyboardMarkup customKeyboard = createChooseDayKeyboard();
        SendMessage sndMessage = new SendMessage();
        sndMessage.setChatId(chatId);
        sndMessage.setText("Выберите дату получения заказа");
        sndMessage.setReplyMarkup(customKeyboard);
        try{
            execute(sndMessage);
        }catch (TelegramApiException e){
            e.printStackTrace();
        }
    }

    public InlineKeyboardMarkup createChooseMethodReceiptKeyBoard() {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        InlineKeyboardButton inlineKeyboardButton1 = new InlineKeyboardButton();
        InlineKeyboardButton inlineKeyboardButton2 = new InlineKeyboardButton();
        inlineKeyboardButton1.setText("Самовывоз");
        inlineKeyboardButton1.setCallbackData("Самовывоз");
        inlineKeyboardButton2.setText("Курьер");
        inlineKeyboardButton2.setCallbackData("Курьер");
        List<InlineKeyboardButton> keyboardButtonsRow1 = new ArrayList<>();
        keyboardButtonsRow1.add(inlineKeyboardButton1);
        keyboardButtonsRow1.add(inlineKeyboardButton2);
        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
        rowList.add(keyboardButtonsRow1);
        inlineKeyboardMarkup.setKeyboard(rowList);
        return inlineKeyboardMarkup;
    }

    public void sendChooseMethodReceiptKeyBoard(Message message){
        String chatId = String.valueOf(message.getChatId());
        InlineKeyboardMarkup customKeyboard = createChooseMethodReceiptKeyBoard();
        SendMessage sndMessage = new SendMessage();
        sndMessage.setChatId(chatId);
        sndMessage.setText("Выберите способ получения заказа");
        sndMessage.setReplyMarkup(customKeyboard);
        try{
            execute(sndMessage);
        }catch (TelegramApiException e){
            e.printStackTrace();
        }
    }

    public InlineKeyboardMarkup createPlaceAnOrderKeyBoard() {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        InlineKeyboardButton inlineKeyboardButton1 = new InlineKeyboardButton();
        inlineKeyboardButton1.setText("Подтвердить заказ");
        inlineKeyboardButton1.setCallbackData("Заказать");
        List<InlineKeyboardButton> keyboardButtonsRow1 = new ArrayList<>();
        keyboardButtonsRow1.add(inlineKeyboardButton1);
        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
        rowList.add(keyboardButtonsRow1);
        inlineKeyboardMarkup.setKeyboard(rowList);
        return inlineKeyboardMarkup;
    }

    public void sendPlaceAnOrderKeyBoard(Message message){
        String chatId = String.valueOf(message.getChatId());
        InlineKeyboardMarkup customKeyboard = createPlaceAnOrderKeyBoard();
        SendMessage sndMessage = new SendMessage();
        sndMessage.setChatId(chatId);
        sndMessage.setText("Желаете оформить заказ?");
        sndMessage.setReplyMarkup(customKeyboard);
        try{
            execute(sndMessage);
        }catch (TelegramApiException e){
            e.printStackTrace();
        }
    }

    public void sendBasket(Message message) throws TelegramApiException {
        int quantity = 0;
        String price;
        sendMessage(message.getChatId(), "Содержание корзины");
        for (CatalogItem item : basket) {
            SendPhoto sendPhoto = new SendPhoto();
            sendPhoto.setChatId(message.getChatId());
            sendPhoto.setPhoto(new InputFile(new File(item.getWayToImage())));
            execute(sendPhoto);
            SendMessage messag = new SendMessage();
            messag.setChatId(message.getChatId());
            messag.setText("Название: " + item.getDescription() + "\n" +
                    "Категория " + item.getCategory() + "\n" +
                    "Стоимость: " + item.getPrice() + "\n" +
                    "Количество: " + item.getQuantity() + "\n");
            execute(messag);
            price = item.getPrice().replace(" ", "");
            quantity += Integer.parseInt(price) * item.getQuantity();
            sendActionInBasketKeyboard(message, item.getIdItem());
        }
        sendMessage(message.getChatId(), "Общая стоимость: " + quantity);
        sendPlaceAnOrderKeyBoard(message);
    }

    public InlineKeyboardMarkup createActionInBasketKeyboard(int idProduct){
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        InlineKeyboardButton inlineKeyboardButton1 = new InlineKeyboardButton();
        InlineKeyboardButton inlineKeyboardButton2 = new InlineKeyboardButton();
        inlineKeyboardButton1.setText("Добавить");
        inlineKeyboardButton1.setCallbackData("increment_" + idProduct);
        inlineKeyboardButton2.setText("Убавить");
        inlineKeyboardButton2.setCallbackData("decrement_" + idProduct);
        List<InlineKeyboardButton> keyboardButtonsRow1 = new ArrayList<>();
        keyboardButtonsRow1.add(inlineKeyboardButton1);
        keyboardButtonsRow1.add(inlineKeyboardButton2);
        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
        rowList.add(keyboardButtonsRow1);
        inlineKeyboardMarkup.setKeyboard(rowList);
        return inlineKeyboardMarkup;
    }

    public void sendActionInBasketKeyboard(Message message, int idProduct){
        String chatId = String.valueOf(message.getChatId());
        InlineKeyboardMarkup customKeyboard = createActionInBasketKeyboard(idProduct);
        SendMessage sndMessage = new SendMessage();
        sndMessage.setChatId(chatId);
        sndMessage.setText("Хотите изменить количество товара?");
        sndMessage.setReplyMarkup(customKeyboard);
        try{
            execute(sndMessage);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    public InlineKeyboardMarkup createAddToBasketKeyBoard(int idProduct) {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        InlineKeyboardButton inlineKeyboardButton1 = new InlineKeyboardButton();
        inlineKeyboardButton1.setText("Добавить в корзину");
        inlineKeyboardButton1.setCallbackData(String.valueOf(idProduct));
        List<InlineKeyboardButton> keyboardButtonsRow1 = new ArrayList<>();
        keyboardButtonsRow1.add(inlineKeyboardButton1);
        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
        rowList.add(keyboardButtonsRow1);
        inlineKeyboardMarkup.setKeyboard(rowList);
        return inlineKeyboardMarkup;
    }

    public void sendAddToBasketKeyBoard(Message message, int idProduct){
        String chatId = String.valueOf(message.getChatId());
        InlineKeyboardMarkup customKeyboard = createAddToBasketKeyBoard(idProduct);
        SendMessage sndMessage = new SendMessage();
        sndMessage.setChatId(chatId);
        sndMessage.setText("Хотите добавить в корзину?");
        sndMessage.setReplyMarkup(customKeyboard);
        try{
            execute(sndMessage);
        }catch (TelegramApiException e){
            e.printStackTrace();
        }
    }

    public InlineKeyboardMarkup createCatalogGunKeyBoard() {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        InlineKeyboardButton inlineKeyboardButton1 = new InlineKeyboardButton();
        InlineKeyboardButton inlineKeyboardButton2 = new InlineKeyboardButton();
        InlineKeyboardButton inlineKeyboardButton3 = new InlineKeyboardButton();
        InlineKeyboardButton inlineKeyboardButton4 = new InlineKeyboardButton();
        InlineKeyboardButton inlineKeyboardButton5 = new InlineKeyboardButton();
        InlineKeyboardButton inlineKeyboardButton6 = new InlineKeyboardButton();
        inlineKeyboardButton1.setText("Винтовки");
        inlineKeyboardButton1.setCallbackData("Винтовки");
        inlineKeyboardButton2.setText("Пулемёты");
        inlineKeyboardButton2.setCallbackData("Пулемёты");
        inlineKeyboardButton3.setText("Дробовики");
        inlineKeyboardButton3.setCallbackData("Дробовики");
        inlineKeyboardButton4.setText("Пистолеты");
        inlineKeyboardButton4.setCallbackData("Пистолеты");
        inlineKeyboardButton5.setText("Автоматические винтовки");
        inlineKeyboardButton5.setCallbackData("Автоматические винтовки");
        inlineKeyboardButton6.setText("Назад");
        inlineKeyboardButton6.setCallbackData("Назад");
        List<InlineKeyboardButton> keyboardButtonsRow1 = new ArrayList<>();
        List<InlineKeyboardButton> keyboardButtonsRow2 = new ArrayList<>();
        List<InlineKeyboardButton> keyboardButtonsRow3 = new ArrayList<>();
        List<InlineKeyboardButton> keyboardButtonsRow4 = new ArrayList<>();
        keyboardButtonsRow1.add(inlineKeyboardButton1);
        keyboardButtonsRow1.add(inlineKeyboardButton2);
        keyboardButtonsRow2.add(inlineKeyboardButton3);
        keyboardButtonsRow2.add(inlineKeyboardButton4);
        keyboardButtonsRow3.add(inlineKeyboardButton5);
        keyboardButtonsRow4.add(inlineKeyboardButton6);
        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
        rowList.add(keyboardButtonsRow1);
        rowList.add(keyboardButtonsRow2);
        rowList.add(keyboardButtonsRow3);
        rowList.add(keyboardButtonsRow4);
        inlineKeyboardMarkup.setKeyboard(rowList);
        return inlineKeyboardMarkup;
    }

    public void sendCatalogGunKeyBoard(Message message){
        String chatId = String.valueOf(message.getChatId());
        InlineKeyboardMarkup customKeyboard = createCatalogGunKeyBoard();
        SendMessage sndMessage = new SendMessage();
        sndMessage.setChatId(chatId);
        sndMessage.setText("Выберите категорию");
        sndMessage.setReplyMarkup(customKeyboard);
        try{
            execute(sndMessage);
        }catch (TelegramApiException e){
            e.printStackTrace();
        }
    }

    public InlineKeyboardMarkup createCatalogKeyBoard() {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        InlineKeyboardButton inlineKeyboardButton1 = new InlineKeyboardButton();
        InlineKeyboardButton inlineKeyboardButton2 = new InlineKeyboardButton();
        InlineKeyboardButton inlineKeyboardButton3 = new InlineKeyboardButton();
        InlineKeyboardButton inlineKeyboardButton4 = new InlineKeyboardButton();
        InlineKeyboardButton inlineKeyboardButton5 = new InlineKeyboardButton();
        InlineKeyboardButton inlineKeyboardButton6 = new InlineKeyboardButton();
        inlineKeyboardButton1.setText("Оружие");
        inlineKeyboardButton1.setCallbackData("Оружие");
        inlineKeyboardButton2.setText("Расходники");
        inlineKeyboardButton2.setCallbackData("Расходники");
        inlineKeyboardButton3.setText("Магазины");
        inlineKeyboardButton3.setCallbackData("Магазины");
        inlineKeyboardButton4.setText("Радиосвязь");
        inlineKeyboardButton4.setCallbackData("Радиосвязь");
        inlineKeyboardButton5.setText("Игровая пиротехника");
        inlineKeyboardButton5.setCallbackData("Игровая пиротехника");
        inlineKeyboardButton6.setText("Обвесы");
        inlineKeyboardButton6.setCallbackData("Обвесы");
        List<InlineKeyboardButton> keyboardButtonsRow1 = new ArrayList<>();
        List<InlineKeyboardButton> keyboardButtonsRow2 = new ArrayList<>();
        List<InlineKeyboardButton> keyboardButtonsRow3 = new ArrayList<>();
        keyboardButtonsRow1.add(inlineKeyboardButton1);
        keyboardButtonsRow1.add(inlineKeyboardButton2);
        keyboardButtonsRow1.add(inlineKeyboardButton3);
        keyboardButtonsRow2.add(inlineKeyboardButton4);
        keyboardButtonsRow2.add(inlineKeyboardButton5);
        keyboardButtonsRow3.add(inlineKeyboardButton6);
        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
        rowList.add(keyboardButtonsRow1);
        rowList.add(keyboardButtonsRow2);
        rowList.add(keyboardButtonsRow3);
        inlineKeyboardMarkup.setKeyboard(rowList);
        return inlineKeyboardMarkup;
    }

    public void sendCatalogKeyBoard(Message message){
        String chatId = String.valueOf(message.getChatId());
        InlineKeyboardMarkup customKeyboard = createCatalogKeyBoard();
        SendMessage sndMessage = new SendMessage();
        sndMessage.setChatId(chatId);
        sndMessage.setText("Выберите категорию");
        sndMessage.setReplyMarkup(customKeyboard);
        try{
            execute(sndMessage);
        }catch (TelegramApiException e){
            e.printStackTrace();
        }
    }

    public InlineKeyboardMarkup createClientMenuKeyBoard() {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        InlineKeyboardButton inlineKeyboardButton1 = new InlineKeyboardButton();
        InlineKeyboardButton inlineKeyboardButton2 = new InlineKeyboardButton();
        inlineKeyboardButton1.setText("Каталог снаряжения \uD83D\uDD2B");
        inlineKeyboardButton1.setCallbackData("Каталог");
        inlineKeyboardButton2.setText("Корзина\uD83D\uDED2");
        inlineKeyboardButton2.setCallbackData("Корзина");
        List<InlineKeyboardButton> keyboardButtonsRow1 = new ArrayList<>();
        keyboardButtonsRow1.add(inlineKeyboardButton1);
        keyboardButtonsRow1.add(inlineKeyboardButton2);
        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
        rowList.add(keyboardButtonsRow1);
        inlineKeyboardMarkup.setKeyboard(rowList);
        return inlineKeyboardMarkup;
    }

    public void sendClientMenuKeyBoard(Message message){
        String chatId = String.valueOf(message.getChatId());
        InlineKeyboardMarkup customKeyboard = createClientMenuKeyBoard();
        SendMessage sndMessage = new SendMessage();
        sndMessage.setChatId(chatId);
        sndMessage.setText("Выберите ответ");
        sndMessage.setReplyMarkup(customKeyboard);
        try{
            execute(sndMessage);
        }catch (TelegramApiException e){
            e.printStackTrace();
        }
    }

    public InlineKeyboardMarkup createChooseKeyBoard() {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        InlineKeyboardButton inlineKeyboardButton1 = new InlineKeyboardButton();
        InlineKeyboardButton inlineKeyboardButton2 = new InlineKeyboardButton();
        inlineKeyboardButton1.setText("Клиент");
        inlineKeyboardButton1.setCallbackData("Клиент");
        inlineKeyboardButton2.setText("Админ \uD83E\uDDE0");
        inlineKeyboardButton2.setCallbackData("Админ");
        List<InlineKeyboardButton> keyboardButtonsRow1 = new ArrayList<>();
        List<InlineKeyboardButton> keyboardButtonsRow2 = new ArrayList<>();
        keyboardButtonsRow1.add(inlineKeyboardButton1);
        keyboardButtonsRow2.add(inlineKeyboardButton2);
        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
        rowList.add(keyboardButtonsRow1);
        rowList.add(keyboardButtonsRow2);
        inlineKeyboardMarkup.setKeyboard(rowList);
        return inlineKeyboardMarkup;
    }

    public void sendInlineKeyboard(Message message){
        String chatId = String.valueOf(message.getChatId());
        InlineKeyboardMarkup customKeyboard = createChooseKeyBoard();
        SendMessage sndMessage = new SendMessage();
        sndMessage.setChatId(chatId);
        sndMessage.setText("Выберите ответ");
        sndMessage.setReplyMarkup(customKeyboard);
        try{
            execute(sndMessage);
        }catch (TelegramApiException e){
            e.printStackTrace();
        }
    }

    public ReplyKeyboardMarkup createBasketKeyboard(){
        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        List<KeyboardRow> keyboardRows = new ArrayList<>();
        KeyboardRow row1 = new KeyboardRow();
        row1.add("Корзина");
        keyboardRows.add(row1);
        keyboardMarkup.setKeyboard(keyboardRows);
        return keyboardMarkup;
    }

    public void sendBasketKeyboard(Message message){
        String chatId = String.valueOf(message.getChatId());
        ReplyKeyboardMarkup customKeyboard = createBasketKeyboard();
        SendMessage sndMessage = new SendMessage();
        sndMessage.setChatId(chatId);
        sndMessage.setText("Желаете проверить корзину?");
        sndMessage.setReplyMarkup(customKeyboard);
        System.out.println("chatId " + message.getChatId());
        try{
            execute(sndMessage);
        } catch (TelegramApiException e) {
            System.out.println("some shit happen");
            throw new RuntimeException(e);
        }
    }

    public void sendMessage(Long who, String what){
        SendMessage sm = SendMessage.builder()
                .chatId(who.toString()) //Who are we sending a message to
                .text(what).build();    //Message content
        try {
            execute(sm);                        //Actually sending the message
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);      //Any error will be printed here
        }
    }

    public Connection createConnection() throws SQLException {
        Connection connection = null;
        String url = "jdbc:postgresql://localhost:5432/AirsoftGearRent"; // Замените на свой URL базы данных
        String username = "postgres"; // Замените на своё имя пользователя
        String password = "fgtryuiop"; // Замените на свой пароль
        connection = DriverManager.getConnection(url, username, password);
        return connection;
    }

    public void addToDatabaseOrder(Message message){
        sendMessage(message.getChatId(), "Ваш заказ сохранён");
        for (CatalogItem item : basket){
            orderList.add(new Order(user.getFirstName(), String.valueOf(message.getChatId()), item.getDescription(), item.getWayToImage(), item.getCategory(), item.getPrice(), String.valueOf(item.getQuantity()), getMethodReceipt(), String.valueOf(startDay), String.valueOf(endDay)));
        }
        System.out.println(orderList.toString());
        Connection connection = null;
        try{
            connection = createConnection();

            try {
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT 1");
                if (resultSet.next()) {
                    int result = resultSet.getInt(1);
                    if (result == 1) {
                        // Соединение и база данных доступны
                        System.out.println("Соединение с базой данных установлено и база данных доступна");
                    }
                }
                resultSet.close();
                statement.close();
            } catch (SQLException e) {
                // Ошибка при выполнении запроса или соединении с базой данных
                System.out.println("Ошибка при выполнении запроса или соединении с базой данных");
                sendMessage(message.getChatId(), "Ошибка при выполнении запроса или соединении с базой данных");
                e.printStackTrace();
            }

            for (Order item : orderList){
//                String sqlQuery = "INSERT INTO public.Заказы (\"Имя клиента\", \"id\", \"Название товара\", \"Путь к картинке\", \"Категория товара\", \"Цена\", \"Количество\", \"Способ доставки\", \"День получения заказа\", \"День завершения аренды\")\n" +
//                        "VALUES ('" + item.getClientName() + "', '" + item.getId() +"', '" + item.getProductName() + "', '" + item.getWayToImage() + "', '" + item.getCategory() + "', '" + item.getPrice() + "', '" + item.getQuantity() + "', '" + item.getMethodReceipt() + "', '" + item.getStartDay() + "', '" + item.getEndDay() + "');\n";
//                PreparedStatement statement = connection.prepareStatement(sqlQuery);
//                statement.executeUpdate();
                String sqlQuery = "INSERT INTO public.Заказы (\"Имя клиента\", \"id\", \"Название товара\", \"Путь к картинке\", \"Категория товара\", \"Цена\", \"Количество\", \"Способ доставки\", \"День получения заказа\", \"День завершения аренды\") " +
                        "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

                PreparedStatement statement = connection.prepareStatement(sqlQuery);
                statement.setString(1, item.getClientName());
                statement.setString(2, item.getId());
                statement.setString(3, item.getProductName());
                statement.setString(4, item.getWayToImage());
                statement.setString(5, item.getCategory());
                statement.setString(6, item.getPrice());
                statement.setString(7, item.getQuantity());
                statement.setString(8, item.getMethodReceipt());
                statement.setString(9, item.getStartDay());
                statement.setString(10, item.getEndDay());

                System.out.println(item.getCategory());
                int rowsAffected = statement.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Запись успешно добавлена в таблицу Заказы");
                } else {
                    System.out.println("Не удалось добавить запись в таблицу Заказы");
                }

                String sqlQuery2;
                switch (item.getCategory()){
                    case "Винтовки", "Пулемёты", "Дробовики", "Пистолеты", "Автоматические винтовки"->{

                        String getQuantityQuery = "SELECT \"Количество\" FROM public.Оружие WHERE \"Название\" = '" + item.getProductName() + "'";
                        Statement getQuantityStatement = connection.createStatement();
                        ResultSet resultSet = getQuantityStatement.executeQuery(getQuantityQuery);
                        int currentQuantity = 0;
                        if (resultSet.next()) {
                            currentQuantity = resultSet.getInt("Количество");
                        }
                        int newQuantity = currentQuantity - Integer.parseInt(item.getQuantity());

                        sqlQuery2= "UPDATE public.Оружие SET \"Количество\" = " +  newQuantity +
                                " WHERE \"Название\" = '" + item.getProductName() + "'";
                        Statement updateQuantityStatement = connection.createStatement();
                        int rowsAffected1 = updateQuantityStatement.executeUpdate(sqlQuery2);
                        System.out.println("Изменено " + rowsAffected1 + " строк в таблице Оружие");
                    }
                    case "Расходники"->{
                        String getQuantityQuery = "SELECT \"Количество\" FROM public.Расходники WHERE \"Название\" = '" + item.getProductName() + "'";
                        Statement getQuantityStatement = connection.createStatement();
                        ResultSet resultSet = getQuantityStatement.executeQuery(getQuantityQuery);
                        int currentQuantity = 0;
                        if (resultSet.next()) {
                            currentQuantity = resultSet.getInt("Количество");
                        }
                        int newQuantity = currentQuantity - Integer.parseInt(item.getQuantity());

                        sqlQuery2= "UPDATE public.Расходники SET \"Количество\" = " +  newQuantity +
                                " WHERE \"Название\" = '" + item.getProductName() + "'";
                        Statement updateQuantityStatement = connection.createStatement();
                        int rowsAffected1 = updateQuantityStatement.executeUpdate(sqlQuery2);
                        System.out.println("Изменено " + rowsAffected1 + " строк в таблице Расходники");
                    }
                    case "Магазины"->{
                        String getQuantityQuery = "SELECT \"Количество\" FROM public.Магазины WHERE \"Название\" = '" + item.getProductName() + "'";
                        Statement getQuantityStatement = connection.createStatement();
                        ResultSet resultSet = getQuantityStatement.executeQuery(getQuantityQuery);
                        int currentQuantity = 0;
                        if (resultSet.next()) {
                            currentQuantity = resultSet.getInt("Количество");
                        }
                        int newQuantity = currentQuantity - Integer.parseInt(item.getQuantity());

                        sqlQuery2= "UPDATE public.Магазины SET \"Количество\" = " +  newQuantity +
                                " WHERE \"Название\" = '" + item.getProductName() + "'";
                        Statement updateQuantityStatement = connection.createStatement();
                        int rowsAffected1 = updateQuantityStatement.executeUpdate(sqlQuery2);
                        System.out.println("Изменено " + rowsAffected1 + " строк в таблице Магазины");
                    }
                    case "Радио_связь"->{
                        String getQuantityQuery = "SELECT \"Количество\" FROM public.Радио_связь WHERE \"Название\" = '" + item.getProductName() + "'";
                        Statement getQuantityStatement = connection.createStatement();
                        ResultSet resultSet = getQuantityStatement.executeQuery(getQuantityQuery);
                        int currentQuantity = 0;
                        if (resultSet.next()) {
                            currentQuantity = resultSet.getInt("Количество");
                        }
                        int newQuantity = currentQuantity - Integer.parseInt(item.getQuantity());

                        sqlQuery2= "UPDATE public.Радио_связь SET \"Количество\" = " +  newQuantity +
                                " WHERE \"Название\" = '" + item.getProductName() + "'";
                        Statement updateQuantityStatement = connection.createStatement();
                        int rowsAffected1 = updateQuantityStatement.executeUpdate(sqlQuery2);
                        System.out.println("Изменено " + rowsAffected1 + " строк в таблице Радиосвязь");
                    }
                    case "Игровая_пиротехника"->{
                        String getQuantityQuery = "SELECT \"Количество\" FROM public.Игровая_пиротехника WHERE \"Название\" = '" + item.getProductName() + "'";
                        Statement getQuantityStatement = connection.createStatement();
                        ResultSet resultSet = getQuantityStatement.executeQuery(getQuantityQuery);
                        int currentQuantity = 0;
                        if (resultSet.next()) {
                            currentQuantity = resultSet.getInt("Количество");
                        }
                        int newQuantity = currentQuantity - Integer.parseInt(item.getQuantity());

                        sqlQuery2= "UPDATE public.Игровая_пиротехника SET \"Количество\" = " +  newQuantity +
                                " WHERE \"Название\" = '" + item.getProductName() + "'";
                        Statement updateQuantityStatement = connection.createStatement();
                        int rowsAffected1 = updateQuantityStatement.executeUpdate(sqlQuery2);
                        System.out.println("Изменено " + rowsAffected1 + " строк в таблице Игровая пиротехника");
                    }
                    case "Обвесы"->{
                        System.out.println("you her");
                        String getQuantityQuery = "SELECT \"Количество\" FROM public.Обвесы WHERE \"Название\" = '" + item.getProductName() + "';";
                        Statement getQuantityStatement = connection.createStatement();
                        ResultSet resultSet = getQuantityStatement.executeQuery(getQuantityQuery);
                        int currentQuantity = 0;
                        if (resultSet.next()) {
                            currentQuantity = resultSet.getInt("Количество");
                            System.out.println(currentQuantity + " current quantity");
                        }
                        int newQuantity = currentQuantity - Integer.parseInt(item.getQuantity());
                        System.out.println(newQuantity + " newQuantity");

                        sqlQuery2= "UPDATE public.Обвесы SET \"Количество\" = " +  newQuantity +
                                " WHERE \"Название\" = '" + item.getProductName() + "'";
                        Statement updateQuantityStatement = connection.createStatement();
                        int rowsAffected1 = updateQuantityStatement.executeUpdate(sqlQuery2);
                        System.out.println("Изменено " + rowsAffected1 + " строк в таблице Обвесы");
                    }
                }

            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void performDatabaseOperations(Long chatId, Message upd, String category) {
        Connection connection = null;
        try {
            // Подключение к базе данных
            connection = createConnection();

            // Проверка соединения
            try {
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT 1");
                if (resultSet.next()) {
                    int result = resultSet.getInt(1);
                    if (result == 1) {
                        // Соединение и база данных доступны
                        System.out.println("Соединение с базой данных установлено и база данных доступна");
                    }
                }
                resultSet.close();
                statement.close();
            } catch (SQLException e) {
                // Ошибка при выполнении запроса или соединении с базой данных
                System.out.println("Ошибка при выполнении запроса или соединении с базой данных");
                sendMessage(chatId, "Ошибка при выполнении запроса или соединении с базой данных");
                e.printStackTrace();
            }

            // Ваш код для выполнения операций с базой данных
            String sqlQuery = "SELECT *\n" +
                    "FROM public.Оружие\n" +
                    "WHERE \"Категория\" = '" + category + "';";
            try {
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sqlQuery);

                // Обрабатываем результаты запроса
                while (resultSet.next()) {
                    // Получаем значения столбцов из результата
                    String название = resultSet.getString("Название");
                    String путьККартинке = resultSet.getString("Путь к картинке");
                    String калибр = resultSet.getString("Калибр");
                    String бренд = resultSet.getString("Бренд");
                    String принципДействия = resultSet.getString("Принцип действия");
                    String вес = resultSet.getString("Вес");
                    String длина = resultSet.getString("Длина");
                    String начальнаяСкорость = resultSet.getString("Начальная скорость");
                    String стоимость = resultSet.getString("Стоимость");
                    String количество = resultSet.getString("Количество");
                    String категория = resultSet.getString("Категория");

                    // Отправляем данные через бота
                    SendPhoto sendPhoto = new SendPhoto();
                    sendPhoto.setChatId(chatId);
                    sendPhoto.setPhoto(new InputFile(new File(путьККартинке)));
                    execute(sendPhoto);

                    SendMessage messag = new SendMessage();
                    messag.setChatId(chatId);
                    messag.setText("Название: " + название + "\n" +
                            "Калибр:  " + калибр + "\n" +
                            "Бренд: " + бренд + "\n" +
                            "Принцип действия: " + принципДействия + "\n" +
                            "Вес: " + вес + " гр. \n" +
                            "Длина: " + длина + "мм. \n" +
                            "Начальная скорость: " + начальнаяСкорость + "\n" +
                            "Стоимость: " + стоимость + "\n" +
                            "Количество: " + количество + "\n");
                    execute(messag);
                    System.out.println(idProduct);
                    idProduct++;
                    catalog.add(new CatalogItem(idProduct, название, путьККартинке, категория, стоимость, 1));
                    System.out.println(catalog.toString());
                    sendAddToBasketKeyBoard(upd, idProduct);
                }
                sendCatalogGunKeyBoard(upd);

            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (TelegramApiException | SQLException e) {
            System.out.println("smth went wrong");
            sendMessage(chatId, "smth went wrong");
            throw new RuntimeException(e);
        } finally {
            // Закрываем соединение
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void performDatabaseConsumables(Long chatId, Message upd) {
        Connection connection = null;
        try {
            // Подключение к базе данных
            connection = createConnection();

            // Проверка соединения
            try {
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT 1");
                if (resultSet.next()) {
                    int result = resultSet.getInt(1);
                    if (result == 1) {
                        // Соединение и база данных доступны
                        System.out.println("Соединение с базой данных установлено и база данных доступна");
                    }
                }
                resultSet.close();
                statement.close();
            } catch (SQLException e) {
                // Ошибка при выполнении запроса или соединении с базой данных
                System.out.println("Ошибка при выполнении запроса или соединении с базой данных");
                sendMessage(chatId, "Ошибка при выполнении запроса или соединении с базой данных");
                e.printStackTrace();
            }

            // Ваш код для выполнения операций с базой данных
            String sqlQuery = "SELECT * FROM public.Расходники;";
            try {
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sqlQuery);

                // Обрабатываем результаты запроса
                while (resultSet.next()) {
                    // Получаем значения столбцов из результата
                    String название = resultSet.getString("Название");
                    String путьККартинке = resultSet.getString("Путь к картинке");
                    String бренд = resultSet.getString("Бренд");
                    String описание = resultSet.getString("Описание");
                    String количество = resultSet.getString("Количество");
                    String стоимость = resultSet.getString("Стоимость");
                    String категория = resultSet.getString("Категория");

                    // Отправляем данные через бота
                    SendPhoto sendPhoto = new SendPhoto();
                    sendPhoto.setChatId(chatId);
                    sendPhoto.setPhoto(new InputFile(new File(путьККартинке)));
                    execute(sendPhoto);

                    SendMessage messag = new SendMessage();
                    messag.setChatId(chatId);
                    messag.setText("Название: " + название + "\n" +
                            "Бренд: " + бренд + "\n" +
                            "Описание: " + описание + "\n" +
                            "Количество: " + количество + "\n" +
                            "Стоимость: " + стоимость + "\n" );
                    execute(messag);
                    System.out.println(idProduct);
                    idProduct++;
                    catalog.add(new CatalogItem(idProduct, название, путьККартинке, категория, стоимость, 1));
                    System.out.println(catalog.toString());
                    sendAddToBasketKeyBoard(upd, idProduct);
                }
                sendCatalogKeyBoard(upd);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (TelegramApiException | SQLException e) {
            System.out.println("smth went wrong");
            sendMessage(chatId, "smth went wrong");
            throw new RuntimeException(e);
        } finally {
            // Закрываем соединение
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void performDatabasesMagazineRadioPyroBody(Long chatId, Message upd, String databaseName) {
        Connection connection = null;
        try {
            // Подключение к базе данных
            connection = createConnection();

            // Проверка соединения
            try {
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT 1");
                if (resultSet.next()) {
                    int result = resultSet.getInt(1);
                    if (result == 1) {
                        // Соединение и база данных доступны
                        System.out.println("Соединение с базой данных установлено и база данных доступна");
                    }
                }
                resultSet.close();
                statement.close();
            } catch (SQLException e) {
                // Ошибка при выполнении запроса или соединении с базой данных
                System.out.println("Ошибка при выполнении запроса или соединении с базой данных");
                sendMessage(chatId, "Ошибка при выполнении запроса или соединении с базой данных");
                e.printStackTrace();
            }

            // Ваш код для выполнения операций с базой данных
            String sqlQuery = "SELECT * FROM public." + databaseName + ";";
            try {
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sqlQuery);

                // Обрабатываем результаты запроса
                while (resultSet.next()) {
                    // Получаем значения столбцов из результата
                    String название = resultSet.getString("Название");
                    String путьККартинке = resultSet.getString("Путь к картинке");
                    String описание = resultSet.getString("Описание");
                    String количество = resultSet.getString("Количество");
                    String стоимость = resultSet.getString("Стоимость");
                    String категория = resultSet.getString("Категория");

                    // Отправляем данные через бота
                    SendPhoto sendPhoto = new SendPhoto();
                    sendPhoto.setChatId(chatId);
                    sendPhoto.setPhoto(new InputFile(new File(путьККартинке)));
                    execute(sendPhoto);

                    SendMessage messag = new SendMessage();
                    messag.setChatId(chatId);
                    messag.setText("Название: " + название + "\n" +
                            "Описание: " + описание + "\n" +
                            "Количество: " + количество + "\n" +
                            "Стоимость: " + стоимость + "\n" );
                    execute(messag);
                    System.out.println(idProduct);
                    idProduct++;
                    catalog.add(new CatalogItem(idProduct, название, путьККартинке,  категория, стоимость, 1));
                    System.out.println(catalog.toString());
                    sendAddToBasketKeyBoard(upd, idProduct);
                }
                sendCatalogKeyBoard(upd);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (TelegramApiException | SQLException e) {
            System.out.println("smth went wrong");
            sendMessage(chatId, "smth went wrong");
            throw new RuntimeException(e);
        } finally {
            // Закрываем соединение
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

//    private void performDatabaseBodyKits(Long chatId, Message upd) {
//        Connection connection = null;
//        try {
//            // Подключение к базе данных
//            connection = createConnection();
//
//            // Проверка соединения
//            try {
//                Statement statement = connection.createStatement();
//                ResultSet resultSet = statement.executeQuery("SELECT 1");
//                if (resultSet.next()) {
//                    int result = resultSet.getInt(1);
//                    if (result == 1) {
//                        // Соединение и база данных доступны
//                        System.out.println("Соединение с базой данных установлено и база данных доступна");
//                    }
//                }
//                resultSet.close();
//                statement.close();
//            } catch (SQLException e) {
//                // Ошибка при выполнении запроса или соединении с базой данных
//                System.out.println("Ошибка при выполнении запроса или соединении с базой данных");
//                sendMessage(chatId, "Ошибка при выполнении запроса или соединении с базой данных");
//                e.printStackTrace();
//            }
//
//            // Ваш код для выполнения операций с базой данных
//            String sqlQuery = "SELECT * FROM public.Обвесы;";
//            try {
//                Statement statement = connection.createStatement();
//                ResultSet resultSet = statement.executeQuery(sqlQuery);
//
//                // Обрабатываем результаты запроса
//                while (resultSet.next()) {
//                    // Получаем значения столбцов из результата
//                    String название = resultSet.getString("Название");
//                    String путьККартинке = resultSet.getString("Путь к картинке");
//                    String описание = resultSet.getString("Описание");
//                    String количество = resultSet.getString("Количество");
//                    String стоимость = resultSet.getString("Стоимость");
//
//                    // Отправляем данные через бота
//                    SendPhoto sendPhoto = new SendPhoto();
//                    sendPhoto.setChatId(chatId);
//                    sendPhoto.setPhoto(new InputFile(new File(путьККартинке)));
//                    execute(sendPhoto);
//
//                    SendMessage messag = new SendMessage();
//                    messag.setChatId(chatId);
//                    messag.setText("Название: " + название + "\n" +
//                            "Описание: " + описание + "\n" +
//                            "Количество: " + количество + "\n" +
//                            "Стоимость: " + стоимость + "\n" );
//                    execute(messag);
//                    sendAddToBasketKeyBoard(upd, idProduct);
//                }
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        } catch (TelegramApiException | SQLException e) {
//            System.out.println("smth went wrong");
//            sendMessage(chatId, "smth went wrong");
//            throw new RuntimeException(e);
//        } finally {
//            // Закрываем соединение
//            if (connection != null) {
//                try {
//                    connection.close();
//                } catch (SQLException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//    }

    @Override
    public String getBotUsername() {
        return "AirsoftGearRentBot";
    }

    public String getBotToken() {
        return "";
    }
}