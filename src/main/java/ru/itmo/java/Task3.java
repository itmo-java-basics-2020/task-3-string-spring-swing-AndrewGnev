package ru.itmo.java;

import java.util.Arrays;
import java.util.HashSet;

public class Task3 {

    /**
     * Напишите функцию, которая принимает массив целых чисел и циклически сдвигает элементы этого массива вправо:
     * A[0] -> A[1], A[1] -> A[2] .. A[length - 1] -> A[0].
     * Если инпут равен null - вернуть пустой массив
     */
    int[] getShiftedArray(int[] inputArray) {
        if(inputArray != null && inputArray.length > 0){
            int buffer1 = inputArray[inputArray.length - 1], buffer2 = inputArray[0];
            for(int i = 0; i < inputArray.length; ++i){
                buffer2 = inputArray[i];
                inputArray[i] = buffer1;
                buffer1 = buffer2;
            }
            return  inputArray;
        }
        else{
            return new int[0];
        }
    }

    /**
     * Напишите функцию, которая принимает массив целых чисел и возвращает максимальное значение произведения двух его элементов.
     * Если массив состоит из одного элемента, то функция возвращает этот элемент.
     *
     * Если входной массив пуст или равен null - вернуть 0
     *
     * Пример: 2 4 6 -> 24
     */
    int getMaxProduct(int[] inputArray) {
        if(inputArray != null && inputArray.length > 0){
            if(inputArray.length == 1) return inputArray[0];
            int max1 = Integer.MIN_VALUE, max2 = max1, min1 = Integer.MAX_VALUE, min2 = min1;
            for(int i = 0; i < inputArray.length; ++i){

                if(inputArray[i] > max1){
                    if(inputArray[i] >= max2){
                        max1 = inputArray[i];
                        max2 = max1 + max2;//
                        max1 = max2 - max1;// swap
                        max2 = max2 - max1;//
                    }
                    else max1 = inputArray[i];
                }

                if(inputArray[i] < min1){
                    if(inputArray[i] <= min2){
                        min1 = inputArray[i];
                        min2 = min1 + min2;//
                        min1 = min2 - min1;// swap
                        min2 = min2 - min1;//
                    }
                    else min1 = inputArray[i];
                }
            }
            if(min1 * min2 > max1 * max2) return min1 * min2;
            return max1 * max2;
        }
        return 0;
    }

    /**
     * Напишите функцию, которая вычисляет процент символов 'A' и 'B' (латиница) во входной строке.
     * Функция не должна быть чувствительна к регистру символов.
     * Результат округляйте путем отбрасывания дробной части.
     *
     * Пример: acbr -> 50
     */
    int getABpercentage(String input) {
        if (input == null) return 0;

        String a = "a", b = "b";
        int n = 0;
        for(int i = 0; i < input.length(); ++i){
            if(a.equalsIgnoreCase(String.valueOf(input.charAt(i)))) n++;
            if(b.equalsIgnoreCase(String.valueOf(input.charAt(i)))) n++;
        }
        return (int) (n / (double) input.length() * 100);
    }

    /**
     * Напишите функцию, которая определяет, является ли входная строка палиндромом
     */
    boolean isPalindrome(String input) {
        if(input == null) return false;
        if(input.length() == 0) return true;
        boolean check = true;
        for(int  i = 0; i < input.length() / 2 + input.length() % 2; ++i){
            if(input.charAt(i) != input.charAt(input.length() - i - 1)){
                check = false;
                break;
            }
        }
        return check;
    }

    /**
     * Напишите функцию, которая принимает строку вида "bbcaaaak" и кодирует ее в формат вида "b2c1a4k1",
     * где группы одинаковых символов заменены на один символ и кол-во этих символов идущих подряд в строке
     */
    String getEncodedString(String input) {
        if(input == null || input.length() == 0) return "";
        char actualchar = input.charAt(0);
        int n = 0;
        StringBuilder newString = new StringBuilder();
        for(int i = 0 ; i < input.length(); ++i){
            if(input.charAt(i) == actualchar) n++;
            else{
                newString.append(actualchar).append(n);
                n = 1;
                actualchar = input.charAt(i);
            }
        }
        newString.append(actualchar).append(n);
        return newString.toString();
    }

    /**
     * Напишите функцию, которая принимает две строки, и возвращает true, если одна может быть перестановкой (пермутатсией) другой.
     * Строкой является последовательность символов длинной N, где N > 0
     * Примеры:
     * isPermutation("abc", "cba") == true;
     * isPermutation("abc", "Abc") == false;
     */
    boolean isPermutation(String one, String two) {
        if(one == null || two == null || one.length() == 0 && two.length() == 0 || one.length() != two.length()) return false;

        HashSet<Character> checkchars = new HashSet<Character>();
        for(int i = 0; i < one.length(); ++i){
            checkchars.add(one.charAt(i));
        }
        boolean check = true;
        for(int i = 0; i < two.length(); ++i){
            if(!checkchars.contains(two.charAt(i))){
                check = false;
                break;
            }
        }
        return check;
    }

    /**
     * Напишите функцию, которая принимает строку и возвращает true, если она состоит из уникальных символов.
     * Из дополнительной памяти (кроме примитивных переменных) можно использовать один массив.
     * Строкой является последовательность символов длинной N, где N > 0
     */
    boolean isUniqueString(String s) {
        if(s == null || s.length() == 0) return false;
        char[] chars = new char[s.length()];
        for(int i = 0; i < s.length(); ++i){
            chars[i] = s.charAt(i);
        }
        Arrays.sort(chars);
        for(int i = 1; i < chars.length; ++i){
            if(chars[i] == chars[i-1]) return false;
        }
        return true;
    }

    /**
     * Напишите функцию, которая транспонирует матрицу. Только квадратные могут быть на входе.
     *
     * Если входной массив == null - вернуть пустой массив
     */
    int[][] matrixTranspose(int[][] m) {
        if(m == null || m.length == 0) return new int[][]{{},{}};
        int[][] ans = new int[m.length][m[0].length];
        for(int i = 0; i < m.length; ++i){
            for(int j = 0; j < m[0].length; ++j){
                ans[j][i] = m[i][j];
            }
        }
        return ans;
    }

    /**
     * Напиишите функцию, принимающую массив строк и символ-разделитель,
     * а возвращает одну строку состоящую из строк, разделенных сепаратором.
     *
     * Запрещается пользоваться функций join
     *
     * Если сепаратор == null - считайте, что он равен ' '
     * Если исходный массив == null -  вернуть пустую строку
     */
    String concatWithSeparator(String[] inputStrings, Character separator) {
        if(inputStrings == null) return "";
        if(separator == null) separator = ' ';
        StringBuilder newString = new StringBuilder();
        for(int i = 0; i < inputStrings.length; ++i){
            newString.append(inputStrings[i]);
            if(i < inputStrings.length - 1)
                newString.append(separator);
        }
        return newString.toString();
    }

    /**
     * Напишите функцию, принимающую массив строк и строку-перфикс и возвращающую кол-во строк массива с данным префиксом
     */
    int getStringsStartWithPrefix(String[] inputStrings, String prefix) {
        if(inputStrings == null || prefix == null || inputStrings.length == 0 || prefix.length() == 0) return 0;
        int n = 0;
        for(int i = 0; i < inputStrings.length; ++i){
            if(prefix.length() <= inputStrings[i].length()){
                boolean check = true;
                for(int j = 0; j < prefix.length(); ++j){
                    if(prefix.charAt(j) != inputStrings[i].charAt(j)){
                        check = false;
                        break;
                    }
                }
                if(check) n++;
            }
        }
        return n;
    }
}
