package com.geek.im.assistance.nlp.domain.service;

import java.util.List;

/**
 * @author : HK意境
 * @ClassName : DictionaryService
 * @date : 2024/2/21 10:08
 * @description :
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
public interface DictionaryService {

    String getSummary(String document);

    List<String> extractKeywords(String document, int size);

    List<String> extractSummary(String document, Integer size);
}
