package com.study.dubboprovider.apiimpl;

import com.study.dubboapi.api.ServiceApi;
import org.apache.dubbo.config.annotation.Service;

import java.util.Random;

@Service
public class ServiceApiImpl implements ServiceApi {

    @Override
    public void methodA() {
        randomSleep(0, 100);
    }

    @Override
    public void methodB() {
        randomSleep(0, 100);
    }

    @Override
    public void methodC() {
        randomSleep(0, 100);
    }


    private void randomSleep(int min, int max) {
        int i = new Random().nextInt(max - min + 1) + min;
        try {
            Thread.sleep(i);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
