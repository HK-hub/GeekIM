package com.geek.im.assistance.nlp.infrastructure.persistent.repository;

import com.geek.im.assistance.nlp.domain.repository.DictionaryRepository;
import com.hankcs.hanlp.dictionary.CoreDictionary;
import com.hankcs.hanlp.dictionary.CustomDictionary;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author : HK意境
 * @ClassName : CustomDictionaryRepository
 * @date : 2024/2/21 9:59
 * @description :
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
@Component
public class CustomDictionaryRepository implements DictionaryRepository {
    @Override
    public boolean addDictionary(String work, String natureAndFrequency) {

        boolean add = CustomDictionary.add(work, natureAndFrequency);

        return add;
    }

    @Override
    public boolean updateDictionary(String key, String value) {
        return false;
    }

    @Override
    public boolean deleteDictionary(String key) {

        CustomDictionary.remove(key);
        return false;
    }

    @Override
    public boolean contains(String word) {

        return CustomDictionary.contains(word);
    }

    @Override
    public CoreDictionary.Attribute getDictionary(String word) {

        CoreDictionary.Attribute attribute = CustomDictionary.get(word);
        return attribute;
    }


    @Override
    public List<String> searchDictionary(String word) {

        LinkedList<Map.Entry<String, CoreDictionary.Attribute>> wordEntryList = CustomDictionary.commonPrefixSearch(word);
        List<String> wordList = wordEntryList.stream().map(Map.Entry::getKey).toList();
        return wordList;
    }


}
