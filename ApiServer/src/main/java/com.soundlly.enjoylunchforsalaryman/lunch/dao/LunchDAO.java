package com.soundlly.enjoylunchforsalaryman.lunch.dao;

import com.soundlly.enjoylunchforsalaryman.lunch.model.LunchModel;

/**
 * Created by soundllydev on 2016. 3. 16..
 */
public interface LunchDAO {

  public boolean saveLunch(LunchModel lunchModel) throws Exception;
}
