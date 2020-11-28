package com.study.dubboconsumer;


import com.study.dubboconsumer.pojo.TP;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Component
@Order(1)
public class PrintTPCommandLineRunner implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        //每隔5秒打印
        Timer t1 = new Timer();
        TimerTask timerTask1 = new TimerTask() {
            @Override
            public void run() {
                HashMap<String, List<Integer>> tpInfoMap = TPMonitorUtil.getTPInfoMap();
                if (tpInfoMap.isEmpty()) {
                    return;
                }
                Set<String> methods = tpInfoMap.keySet();
                for (String method : methods) {
                    TP value = TPMonitorUtil.getTPValueByMethod(method);
                    System.out.println(method + "-----------TP90: " + value.getTp90() + "ms / TP99:" + value.getTp99() + "ms");
                }

                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:ss");
                String time = format.format(new Date());
                System.out.println("=======================================" + time + "==========================================");
            }
        };
        t1.schedule(timerTask1, 0, 5 * 1000);

        //每隔一分钟重新计算
        Timer t2 = new Timer();
        TimerTask timerTask2 = new TimerTask() {
            @Override
            public void run() {
                TPMonitorUtil.clearTPInfoMap();
            }
        };
        t2.schedule(timerTask2, 0, 60 * 1000);
    }

}
