package com.soundlly.enjoylunchforsalaryman.lunch.dao;

import com.soundlly.enjoylunchforsalaryman.lunch.model.LunchModel;

import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

/**
 * Created by soundllydev on 2016. 3. 16..
 */
@Transactional
@Repository
public class LunchDAOImpl implements LunchDAO {

  private final Logger logger = LoggerFactory.getLogger(getClass());

  @Autowired
  private HibernateTemplate hibernateTemplate;

  @Override
  public boolean saveLunch(LunchModel lunchModel) throws Exception {
    Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
    session.save(lunchModel);

    return true;
  }
}
