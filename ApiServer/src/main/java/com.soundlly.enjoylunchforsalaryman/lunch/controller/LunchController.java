package com.soundlly.enjoylunchforsalaryman.lunch.controller;

import com.soundlly.enjoylunchforsalaryman.DefaultResponse;
import com.soundlly.enjoylunchforsalaryman.common.Constants;
import com.soundlly.enjoylunchforsalaryman.lunch.model.LunchModel;
import com.soundlly.enjoylunchforsalaryman.lunch.service.LunchService;
import com.soundlly.enjoylunchforsalaryman.lunch.validator.LunchModelValidator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by soundllydev on 2016. 3. 15..
 */
@RestController
@RequestMapping(value = "/api/v1/lunch")
public class LunchController implements Constants {

  private final Logger logger = LoggerFactory.getLogger(getClass());

  @Autowired
  private LunchService lunchService;

  @RequestMapping(value = "", method = RequestMethod.POST)
  public ResponseEntity<DefaultResponse> saveLunchData(@RequestBody LunchModel lunchModel,
      BindingResult bindingResult) {
    new LunchModelValidator().validate(lunchModel, bindingResult);

    DefaultResponse response = new DefaultResponse();

    if (bindingResult.hasErrors()) {
      response.setCode(ERROR_PARAM);
      response.setMessage(bindingResult.getAllErrors().get(0).getDefaultMessage());
      return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    int result = lunchService.saveLunchData(lunchModel);

    if (result == SUCCESS) {
      response.setCode(SUCCESS);
      response.setMessage("success");
      return new ResponseEntity<>(response, HttpStatus.OK);
    } else {
      response.setCode(ERROR_DB);
      response.setMessage("Internal Server Error");
      return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
}
