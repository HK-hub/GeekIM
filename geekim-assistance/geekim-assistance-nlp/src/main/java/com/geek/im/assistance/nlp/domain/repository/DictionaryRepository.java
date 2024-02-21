package com.geek.im.assistance.nlp.domain.repository;

import com.hankcs.hanlp.dictionary.CoreDictionary;

import java.util.List;

/**
 * @author : HK意境
 * @ClassName : DictionaryRepository
 * @date : 2024/2/21 9:57
 * @description :
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
public interface DictionaryRepository {

    boolean addDictionary(String word, String natureAndFrequency);

    boolean updateDictionary(String word, String value);

    boolean deleteDictionary(String word);

    boolean contains(String word);

    CoreDictionary.Attribute getDictionary(String word);


    List<String> searchDictionary(String word);

}
