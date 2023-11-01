package com.geek.im.generator.code.service;

import com.fengwenyi.api.result.ResponseTemplate;
import com.fengwenyi.codegenerator.vo.CodeGeneratorRequestVo;

/**
 * @author <a href="https://www.fengwenyi.com">Erwin Feng</a>
 * @since 2021-07-12
 */
public interface IIndexService {

    /**
     * 生成代码
     * @param requestVo
     * @return
     */
    ResponseTemplate<Void> codeGenerator(CodeGeneratorRequestVo requestVo);

    /**
     * 升级检查
     * */
    String upgrade(String version);

}
