package com.dunshan.mall;

import com.dunshan.mall.member.MallMemberApplication;
import com.dunshan.mall.member.service.MemberCacheService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author zhenghf
 * @date 2024-05-14
 * @desc
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MallMemberApplication.class)
public class AuthCodeTest {

    @Autowired
    MemberCacheService memberCacheService;

    @Test
    public void setAuthCodeTest() {
        memberCacheService.setAuthCode("13854673456", "123456");
    }

}
