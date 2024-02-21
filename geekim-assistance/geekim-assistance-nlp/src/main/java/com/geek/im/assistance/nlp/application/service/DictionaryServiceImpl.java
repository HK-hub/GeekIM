package com.geek.im.assistance.nlp.application.service;

import com.geek.im.assistance.nlp.domain.repository.DictionaryRepository;
import com.geek.im.assistance.nlp.domain.service.DictionaryService;
import com.hankcs.hanlp.HanLP;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author : HK意境
 * @ClassName : DictionaryServiceImpl
 * @date : 2024/2/21 10:09
 * @description :
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
@Service
public class DictionaryServiceImpl implements DictionaryService {

    @Resource
    private DictionaryRepository dictionaryRepository;

    @Override
    public String getSummary(String document) {
        String summary = HanLP.getSummary(document, 20);
        return summary;
    }

    @Override
    public List<String> extractKeywords(String document, int size) {

        return HanLP.extractKeyword(document, size);
    }

    @Override
    public List<String> extractSummary(String document, Integer size) {

        List<String> summaryList = HanLP.extractSummary(document, size);
        return summaryList;
    }


}
