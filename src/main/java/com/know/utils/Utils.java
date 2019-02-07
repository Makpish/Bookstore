package com.know.utils;

import com.know.pojo.Pair;
import io.dropwizard.jersey.params.IntParam;

public class Utils {

    public Pair<Long, Long> processPageLimit(IntParam pageNumber, IntParam pageSize) {
        Pair<Long, Long> temp = new Pair<>();
        temp.setSecond(((pageSize != null && pageSize.get() >= 0) ? pageSize.get() : 0L));
        temp.setFirst((pageNumber != null && pageNumber.get() >= 0) ? pageNumber.get() * temp.getSecond() : 0L);
        return temp;
    }

}
