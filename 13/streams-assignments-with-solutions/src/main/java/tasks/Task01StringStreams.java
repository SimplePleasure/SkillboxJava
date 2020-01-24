package tasks;

import org.w3c.dom.ls.LSOutput;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class Task01StringStreams {

    /**
     * Функция должна вернуть число строчных символов в строке.
     *
     * Пример:
     *  "abcDE" -> 3
     *  "ABC" -> 0
     */
    static long countLowercaseLetters(String str) {
//        throw new PleaseImplementMeException();
        return Stream.of(str.split("")).filter(x -> x.matches("[^\\s]+")).
                                   filter(x -> x.matches("[a-z а-я]")).map(x -> x.length()).count();
    }

    /**
     * Функция должна заменить каждое слово в строке его длинной.
     *
     * Слова разделяются одним или более пробелами.
     *
     * Пример:
     *   "a b cd" -> "1 1 2"
     *   "one two   three" -> "3 3 5"
     *
     * Тут подойдут эти методы:
     *    - String::split
     *    - Stream::map
     *    - Stream::collect
     *    - Collectors.joining
     */
    static String replaceWordsOnLength(String stir) {
//        throw new PleaseImplementMeException();
        return Stream.of(stir.split("\\s+")).map(x -> x.length()).map(String::valueOf).
                                                                collect(Collectors.joining(" "));
    }
}