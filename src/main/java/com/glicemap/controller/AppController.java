package com.glicemap.controller;

import com.glicemap.dto.DailyMeasuresDTO;
import com.glicemap.dto.DatesWithMeasuresDTO;
import com.glicemap.dto.PostMeasureDTO;
import com.glicemap.dto.UserDTO;
import com.glicemap.service.MeasureService;
import com.glicemap.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/app")
public class AppController {

    @Autowired
    private UserService userService;

    @Autowired
    private MeasureService measureService;

    @GetMapping("/hello")
    public ResponseEntity<String> index(@RequestParam(value = "name", defaultValue = "Mundo") String name) {
        return new ResponseEntity<>(String.format("Olá, %s! Você está no App", name), HttpStatus.OK);
    }

    @GetMapping("/searchMeasures/month")
    public ResponseEntity<DatesWithMeasuresDTO> searchMeasuresMonth(@RequestHeader("documentNumber") String documentNumber, @RequestHeader("date") String date) {
        return new ResponseEntity<>(measureService.getDaysWithMeasure(documentNumber, date), HttpStatus.OK);
    }

    @GetMapping("/searchMeasures/day")
    public ResponseEntity<DailyMeasuresDTO> searchMeasuresDay(@RequestHeader("documentNumber") String documentNumber, @RequestHeader("date") String date) {
        return new ResponseEntity<>(measureService.getDailyMeasures(documentNumber, date), HttpStatus.OK);
    }

    @PostMapping("/postMeasure")
    public ResponseEntity<Boolean> postMeasure(@RequestBody PostMeasureDTO postMeasureDTO) {
        return new ResponseEntity<>(measureService.postMeasure(postMeasureDTO), HttpStatus.OK);
    }

    @GetMapping("/getInfo/user")
    public ResponseEntity<UserDTO> getUserInfo(@RequestHeader("documentNumber") String documentNumber) {
        return new ResponseEntity<>(userService.getUserInfo(documentNumber), HttpStatus.OK);
    }

}