package com.soundlly.enjoylunchforsalaryman.lunch.service;

import com.soundlly.enjoylunchforsalaryman.common.Constants;
import com.soundlly.enjoylunchforsalaryman.lunch.dao.LunchDAO;
import com.soundlly.enjoylunchforsalaryman.lunch.model.LunchModel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by soundllydev on 2016. 3. 16..
 */
@Service
public class LunchServiceImpl implements LunchService, Constants {

  private final Logger logger = LoggerFactory.getLogger(getClass());

  @Autowired
  private LunchDAO lunchDAO;

  @Override
  public int saveLunchData(LunchModel lunchModel) {
    try {
      Calendar calendar = Calendar.getInstance();

      Date now = calendar.getTime();
      lunchModel.setCreated(now);
      lunchModel.setUpdated(now);
      
      lunchDAO.saveLunch(lunchModel);
    } catch (Exception e) {
      logger.error(e.getMessage(), e);
      return ERROR_DB;
    }
    return SUCCESS;
  }
}
