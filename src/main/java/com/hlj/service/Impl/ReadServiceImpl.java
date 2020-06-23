package com.hlj.service.Impl;

import com.hlj.dto.DownLoadDto;
import com.hlj.dto.TestDto;
import com.hlj.service.ReadService;
import org.springframework.stereotype.Service;

/**
 * @author wangzi
 * @date 19/4/10 上午9:29.
 */
@Service
public class ReadServiceImpl implements ReadService {

    @Override
    public TestDto test( TestDto testDto ) {
        System.out.println(1);
        System.out.println(2);
        System.out.println(3);

        return testDto;
//        throw new RuntimeException("testEx");

    }
}