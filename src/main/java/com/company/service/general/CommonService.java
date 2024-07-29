package com.company.service.general;

import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Calendar;

@Service
public class CommonService {

    public String updateState(String name) {

        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());

        name = name + timeStamp;

        return name;
    }
}
