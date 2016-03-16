package com.soundlly.enjoylunchforsalaryman.lunch.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by soundllydev on 2016. 3. 16..
 */
@Entity
@Table(name = "TB_LUNCH")
public class LunchModel {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id")
  private int id;

  @Column(name = "phone")
  private String phone;

  @Column(name = "user_name")
  private String userName;

  @Column(name = "lunch_date_time")
  private Date lunchDateTime;

  @Column(name = "hunger_point")
  private float hungerPoint;

  @Column(name = "store_name")
  private String storeName;

  @Column(name = "menu")
  private String menu;

  @Column(name = "price")
  private int price;

  @Column(name = "satisfaction_point")
  private float satisfactionPoint;

  @Column(name = "memo")
  private String memo;

  @Column(name = "created")
  private Date created;

  @Column(name = "updated")
  private Date updated;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public Date getLunchDateTime() {
    return lunchDateTime;
  }

  public void setLunchDateTime(Date lunchDateTime) {
    this.lunchDateTime = lunchDateTime;
  }

  public float getHungerPoint() {
    return hungerPoint;
  }

  public void setHungerPoint(float hungerPoint) {
    this.hungerPoint = hungerPoint;
  }

  public String getStoreName() {
    return storeName;
  }

  public void setStoreName(String storeName) {
    this.storeName = storeName;
  }

  public String getMenu() {
    return menu;
  }

  public void setMenu(String menu) {
    this.menu = menu;
  }

  public int getPrice() {
    return price;
  }

  public void setPrice(int price) {
    this.price = price;
  }

  public float getSatisfactionPoint() {
    return satisfactionPoint;
  }

  public void setSatisfactionPoint(float satisfactionPoint) {
    this.satisfactionPoint = satisfactionPoint;
  }

  public String getMemo() {
    return memo;
  }

  public void setMemo(String memo) {
    this.memo = memo;
  }

  public Date getCreated() {
    return created;
  }

  public void setCreated(Date created) {
    this.created = created;
  }

  public Date getUpdated() {
    return updated;
  }

  public void setUpdated(Date updated) {
    this.updated = updated;
  }
}
