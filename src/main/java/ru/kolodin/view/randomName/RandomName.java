package ru.kolodin.view.randomName;

import java.util.Random;

/**
 *
 */
public class RandomName {
    private final int max_name_length;

    /**
     * Конструктор генератора случайных "слов" - в нашем случае имен
     * @param max_name_length - максимальное количество букв в слове
     */
    public RandomName(int max_name_length){
        this.max_name_length = Math.max(max_name_length, 4);

    }

    /**
     * Метод генерирующий слово в кирилице из случайных букв
     * нечетные буквы согласные, четные гласные
     * количество букв в слове случайное от 4-х до указанного
     * @return Строка
     */
    public String getNameRusByChar(){
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        int name_length = random.nextInt(max_name_length - 3)+4;
        char letter;
        int[] vowels = new int[]{0, 5, 8, 14, 19, 29, 30, 31};
        int[] consonant = new int[]{1, 2, 3, 4, 6, 7, 10, 11, 12, 13, 15, 16, 17, 18, 20, 21, 22, 23, 24, 25};
        for(int i = 0; i < name_length; i++)
        {
            char ini_char;
            if (i == 0) {
                ini_char = 'А';
            } else {
                ini_char = 'а';
            }
            if (i % 2 == 1){
                letter = (char)(ini_char + vowels[random.nextInt(vowels.length)]);
            } else {
                letter = (char)(ini_char + consonant[random.nextInt(consonant.length)]);
            }
            sb.append(letter);
        }
        return sb.toString();
    }

    /**
     * Метод генерирующий слово в кирилице из случайных букв
     * нечетные буквы согласные, четные гласные
     * количество букв в слове случайное от 4-х до указанного
     * @return Строка
     */
    public String getNameRusByString(){
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        int name_length = random.nextInt(max_name_length - 3)+4;
        String letter;
        String[] vowels = new String[]{"а", "е", "и", "о", "у", "э", "ю", "я"};
        String[] consonant = new String[]{"б", "в", "г", "д", "ж", "з", "к", "л", "м", "н", "п", "р", "с", "т", "ф", "х", "ц", "ч", "ш", "щ"};
        for(int i = 0; i < name_length; i++)
        {
            if (i % 2 == 1){
                letter = vowels[random.nextInt(vowels.length)];
            } else {
                letter = consonant[random.nextInt(vowels.length)];
            }
            if (i == 0) letter = letter.toUpperCase();
            sb.append(letter);
        }
        return sb.toString();
    }

    /**
     * Метод генерирующий слово в кирилице из случайных букв
     * нечетные буквы согласные, четные гласные
     * количество букв в слове случайное от 4-х до указанного
     * @return Строка
     */
    public String getNameRusByStringShort(){
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        int name_length = random.nextInt(max_name_length - 3)+4;
        String letter;
        String[] vowels = new String[]{"а", "е", "и", "о", "у", "ю", "я"};
        String[] consonant = new String[]{"б", "в", "ж", "з", "к", "л", "м", "н", "п", "с", "ф", "х", "ц", "ч", "ш", "щ"};
        for(int i = 0; i < name_length; i++)
        {
            if (i % 2 == 1){
                letter = vowels[random.nextInt(vowels.length)];
            } else {
                letter = consonant[random.nextInt(vowels.length)];
            }
            if (i == 0) letter = letter.toUpperCase();
            sb.append(letter);
        }
        return sb.toString();
    }
}

