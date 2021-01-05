package bot;
import java.util.*;
import java.util.regex.*;

public class SimpleBot {
    final String[] COMMON_PHRASES = {
            "Нет ничего ценнее слов, сказанных к месту и ко времени.",
            "Порой молчание может сказать больше, нежели уйма слов.",
            "Перед тем как писать/говорить всегда лучше подумать.",
            "Вежливая и грамотная речь говорит о величии души.",
            "Приятно когда текст без орфографических ошибок.",
            "Многословие есть признак неупорядоченного ума.",
            "Слова могут ранить, но могут и исцелять.",
            "Записывая слова, мы удваиваем их силу.",
            "Кто ясно мыслит, тот ясно излагает.",
            "Боюсь Вы что-то не договариваете."};
    final String[] ELUSIVE_ANSWERS = {
            "Вопрос непростой, прошу тайм-аут на раздумья.",
            "Не уверен, что располагаю такой информацией.",
            "Может лучше поговорим о чём-то другом?",
            "Простите, но это очень личный вопрос.",
            "Не уверен, что Вам понравится ответ.",
            "Поверьте, я сам хотел бы это знать.",
            "Вы действительно хотите это знать?",
            "Уверен, Вы уже догадались сами.",
            "Зачем Вам такая информация?",
            "Давайте сохраним интригу?"};
    final Map<String, String> ANALYS_PATTERNS = new HashMap<>(){{
            //hello
            put("алоха","hello");
            put("привет","hello");
            put("здравствуй","hello");
            put("здорова","hello");
            put("хай","hello");
            //who
            put("кто\\s.*ты","who");
            put("ты\\s.*кто","who");
            //name
            put("как\\s.*зовут","name");
            put("как\\s.*имя","name");
            put("есть\\s.*имя","name");
            put("какое\\s.*имя","name");
            //howareyou
            put("как\\s.*дела", "howareyou");
            put("как\\s.*жизнь", "howareyou");
            //whatareyoudoing
            put("зачем\\s.*тут", "whatareyoudoing");
            put("зачем\\s.*здесь", "whatareyoudoing");
            put("что\\s.*делаешь", "whatareyoudoing");
            put("чем\\s.*занимаешься", "whatareyoudoing");
            //whatdoyoulike
            put("что\\s.*нравится", "whatdoyoulike");
            put("что\\s.*любишь", "whatdoyoulike");
            //iamfeeling
            put("кажется", "iamfeelling");
            put("чувствую", "iamfeelling");
            put("испытываю", "iamfeelling");
            //yes
            put("^да", "yes");
            put("согласен", "yes");
            put("ага", "yes");
            put("конечно", "yes");
            //whattime
            put("который\\s.*час", "whattime");
            put("сколько\\s.*время", "whattime");
            //bye
            put("прощай", "bye");
            put("увидимся", "bye");
            put("до\\s.*свидания", "bye");
            put("пока", "bye");
        }};
    final Map<String, String> PATTERN_ANSWERS = new HashMap<>(){{
        put("hello", "Здравствуйте, рад Вас видеть.");
        put("who", "Я обычный чат-бот.");
        put("name", "Зовите меня Чаттер :)");
        put("howareyou", "Спасибо, что интересуетесь. У меня всё хорошо.");
        put("whatareyoudoing", "Я пробую общаться с людьми.");
        put("whatdoyoulike", "Мне нравиться думать что я не просто программа.");
        put("iamfeelling", "Как давно это началось? Расскажите чуть подробнее.");
        put("yes", "Согласие есть продукт при полном непротивлении сторон.");
        put("bye", "До свидания. Надеюсь, ещё увидимся.");
    }};
    Pattern pattern;
    Random random;
    Date date;

    public SimpleBot(){
        random = new Random();
        date = new Date();
}

public String sayInReturn(String msg, boolean ai){
        String say = (msg.trim().endsWith("?"))?
                ELUSIVE_ANSWERS[random.nextInt(ELUSIVE_ANSWERS.length)]:
                COMMON_PHRASES[random.nextInt(COMMON_PHRASES.length)];
        if(ai) {
            String message =
                    String.join(" ", msg.toLowerCase().split("[ {,|.}?]+"));
            for (Map.Entry<String, String> o : ANALYS_PATTERNS.entrySet()) {
                pattern = Pattern.compile(o.getKey());
                if (pattern.matcher(message).find())
                    if (o.getValue().equals("whattime")) return date.toString();
                    else return PATTERN_ANSWERS.get(o.getValue());
            }
        }
        return say;
    }
}
