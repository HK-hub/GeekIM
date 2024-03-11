package com.geek.im.security.content.domain.service;

import java.util.Collection;

/**
 * @author : HK意境
 * @ClassName : DictionaryService
 * @date : 2024/2/23 14:48
 * @description : 词典服务
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
public interface DictionaryService {


    boolean addWord(String word);


    boolean addWords(String... words);


    boolean addWords(Collection<String> words);


    boolean match(String word);


    String sensitive(String word);


    boolean removeWord(String word);


    boolean replaceWord(String oldWord, String newWord);
}
