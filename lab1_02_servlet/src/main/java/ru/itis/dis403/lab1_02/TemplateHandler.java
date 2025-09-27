package ru.itis.dis403.lab1_02;

import java.io.Writer;
import java.util.Map;

public class TemplateHandler {
    public void hanlde(String templateName,
                       Map<String, String> params,
                       Writer writer) {
        // 1. Найти файл по имени templateName
        // 2. Прочитать файл в строку
        // 3. Найти в файле ${param_name} и заменить на значения параметра
        // 4. Передать строку во writer
    }
}
