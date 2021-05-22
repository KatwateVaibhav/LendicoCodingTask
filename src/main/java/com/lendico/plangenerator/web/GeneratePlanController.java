package com.lendico.plangenerator.web;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lendico.plangenerator.model.RequestObject;
import com.lendico.plangenerator.model.ResponseObject;
import com.lendico.plangenerator.service.GeneratePayLoudObjectInterface;

@RestController
@RequestMapping("/generate-plan")
public class GeneratePlanController {

    @Autowired
    private GeneratePayLoudObjectInterface generatePayLoudObjectInterface;

    @PostMapping
    public Collection<ResponseObject> generateplan(@RequestBody RequestObject requestObejct) {
    	
        return generatePayLoudObjectInterface.generateResponsePayLoad(requestObejct);
    }
}
