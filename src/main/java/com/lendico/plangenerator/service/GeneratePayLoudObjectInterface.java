package com.lendico.plangenerator.service;

import java.util.Collection;

import com.lendico.plangenerator.model.RequestObject;
import com.lendico.plangenerator.model.ResponseObject;

public interface GeneratePayLoudObjectInterface {

    Collection<ResponseObject> generateResponsePayLoad(RequestObject requestObject);

    ResponseObject responseObject(RequestObject requestObject, int monthIndex);
}
