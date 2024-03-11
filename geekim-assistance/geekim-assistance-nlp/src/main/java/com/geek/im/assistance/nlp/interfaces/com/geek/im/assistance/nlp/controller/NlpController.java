package com.geek.im.assistance.nlp.interfaces.com.geek.im.assistance.nlp.controller;

import com.geek.im.assistance.nlp.domain.service.DictionaryService;
import com.geek.im.common.response.ResponseResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * @author : HK意境
 * @ClassName : NlpController
 * @date : 2024/2/21 11:19
 * @description :
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
@Slf4j
@Tag(name = "NLP自然语言处理")
@RestController
@RequestMapping("/assistance/nlp")
public class NlpController {

    @Resource
    private DictionaryService dictionaryService;


    /**
     * 获取文本摘要信息
     * @param document
     * @return
     */
    @GetMapping("/summary")
    @Operation(summary = "提取文本摘要")
    public Mono<ResponseResult<String>> getSummary(@RequestParam(name = "document") String document,
                                                   @RequestParam(name = "length", required = false, defaultValue = "20") Integer length) {

        String summary = dictionaryService.getSummary(document);
        return Mono.just(ResponseResult.SUCCESS(summary));
    }


    /**
     * 抽取关键词
     * @param document 文档内容
     * @param size 关键词数量
     * @return 抽取的关键词列表
     */
    @Operation(summary = "抽取文本关键词")
    @Parameters({
            @Parameter(name = "document", description = "文本", in = ParameterIn.QUERY),
            @Parameter(name = "size", description = "关键词数量", required = false, in = ParameterIn.QUERY)
    })
    @GetMapping("/extract/keyword")
    public Mono<ResponseResult<List<String>>> extractKeywords(@RequestParam(name = "document") String document,
                                                              @RequestParam(name = "size", required = false, defaultValue = "20") Integer size) {

        // 调用字典服务的抽取关键词方法，传入文档内容和关键词数量
        List<String> keywords = this.dictionaryService.extractKeywords(document, size);

        // 返回成功的结果，包含抽取的关键词列表
        return Mono.just(ResponseResult.SUCCESS(keywords));
    }

    /**
     * 抽取摘要
     * @param document 文档内容
     * @param size 摘要数量
     * @return 抽取的摘要列表
     */
    @GetMapping("/extract/summary")
    public Mono<ResponseResult<List<String>>> extractSummary(@RequestParam(name = "document") String document,
                                                              @RequestParam(name = "size", required = false, defaultValue = "20") Integer size) {

        // 调用字典服务的抽取摘要方法，传入文档内容和摘要数量
        List<String> summaryList = this.dictionaryService.extractSummary(document, size);

        // 返回成功的结果，包含抽取的摘要列表
        return Mono.just(ResponseResult.SUCCESS(summaryList));
    }


}
