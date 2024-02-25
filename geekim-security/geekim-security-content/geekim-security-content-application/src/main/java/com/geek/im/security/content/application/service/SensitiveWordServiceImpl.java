package com.geek.im.security.content.application.service;

import com.geek.im.security.content.domain.service.SensitiveWordService;
import org.ahocorasick.trie.Emit;
import org.ahocorasick.trie.Token;
import org.ahocorasick.trie.Trie;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * @author : HK意境
 * @ClassName : SensitiveWordServiceImpl
 * @date : 2024/2/23 14:41
 * @description :
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
@Service
public class SensitiveWordServiceImpl implements SensitiveWordService {

    public static void main(String[] args) {

        // AC自动机
        Trie.TrieBuilder builder = Trie.builder();
        Trie trie = builder.onlyWholeWords()
                .ignoreCase()
                .build();
        for (Emit emit : trie.parseText("")) {
            String keyword = emit.getKeyword();
        }

        Collection<Token> tokenize = trie.tokenize("");
        for (Token token : tokenize) {
            if (token.isMatch()) {
                String fragment = token.getFragment();
            }
        }

    }


}
