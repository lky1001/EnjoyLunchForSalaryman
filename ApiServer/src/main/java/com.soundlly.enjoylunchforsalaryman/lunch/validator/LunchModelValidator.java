package com.soundlly.enjoylunchforsalaryman.lunch.validator;

import com.soundlly.enjoylunchforsalaryman.lunch.model.LunchModel;

import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Created by soundllydev on 2016. 3. 16..
 */
public class LunchModelValidator implements Validator {

  @Override
  public boolean supports(Class<?> clazz) {
    return LunchModel.class.isAssignableFrom(clazz);
  }

  @Override
  public void validate(Object target, Errors errors) {
    LunchModel lunchModel = (LunchModel) target;

    if (StringUtils.isEmpty(lunchModel.getPhone())) {
      errors.reject("phone", "phone is required");
      return;
    }

    if (StringUtils.isEmpty(lunchModel.getStoreName())) {
      errors.reject("storeName", "storeName is required");
      return;
    }
  }
}
