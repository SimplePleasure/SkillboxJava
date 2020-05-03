package com.session.session;

import com.session.session.model.NoteRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CountStatistics {


    public static Map<String, String> getStat(List<NoteRepository.StatRow> list) {
        HashMap<String, String> map = new HashMap<>();

        double part = (list.stream().mapToInt(NoteRepository.StatRow::getCount).summaryStatistics().getSum()) / 100.0;
//        double part = ((double) list.stream().mapToInt(x -> x.getCount()).sum()) / 100;

        for (NoteRepository.StatRow sr : list) {
            double percent = ((double)sr.getCount()/ part);
            String res = String.format("%.1f", percent) ;
            map.put(sr.getBrowser(), res);
        }
        return map;
    }


}
