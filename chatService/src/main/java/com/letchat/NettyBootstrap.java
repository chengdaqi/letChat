package com.letchat;

import com.letchat.netty.NettyServer;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * @author alice
 * 注意将 NettyBootstrap 注册为 SpringBoot 组件
 */
@Component
public class NettyBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        if (contextRefreshedEvent.getApplicationContext().getParent()==null){
            NettyServer.getInstance().start();
        }
    }
}
