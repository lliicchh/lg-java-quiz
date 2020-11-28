package com.study.dubboconsumer;

import com.study.dubboconsumer.pojo.TP;
import com.study.dubboconsumer.pojo.TPInfo;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class TPMonitorUtil {

    private static final Double TP90 = 0.9;

    private static final Double TP99 = 0.99;

    private static HashMap<String, List<Integer>> tPInfoMap = new HashMap<>();

    public synchronized static HashMap<String, List<Integer>> getTPInfoMap() {
        return tPInfoMap;
    }

    public synchronized static void clearTPInfoMap() {
        tPInfoMap.clear();
    }

    public static synchronized void addTPInfo(TPInfo tpInfo) {
        String methodName = tpInfo.getMethodName();
        Integer consume = tpInfo.getConsume().intValue();
        if (tPInfoMap.containsKey(methodName)) {
            tPInfoMap.get(methodName).add(consume);
        } else {
            List<Integer> timeList = new ArrayList<>();
            timeList.add(consume);
            tPInfoMap.put(methodName, timeList);
        }
    }

    public synchronized static TP getTPValueByMethod(String methodName) {
        List<Integer> list = tPInfoMap.get(methodName);
        list.sort(Comparator.comparingInt(o -> o));
        long tp90 = calTP90(list);
        long tp99 = calTP99(list);
        return new TP(tp90, tp99);
    }

    private static long calTP90(List<Integer> list) {
        return tPFormula(list, TP90);
    }

    private static long calTP99(List<Integer> list) {
        return tPFormula(list, TP99);
    }

    private static int tPFormula(List<Integer> items, Double tp) {
        double ceil = Math.ceil(items.size() * tp);
        int index = new Double(ceil).intValue();
        return items.get(index - 1);
    }



}
