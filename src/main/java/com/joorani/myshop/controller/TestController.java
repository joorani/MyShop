
package com.joorani.myshop.controller;

import com.joorani.myshop.common.exception.DBEmptyDataException;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class TestController {


    @Operation(summary = "test hello", description = "api 설명")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK !!!"),
            @ApiResponse(code = 400, message = "BAD REQUEST !!!"),
    })
    @GetMapping("/hello")
    public ResponseEntity<String> hello(
            @Parameter(description = "이름", required = true, example = "kim")
            @RequestParam String name
    ) {

        if (name.equals("kim")) {
            throw new DBEmptyDataException("DB 예외");
        }
        return ResponseEntity.ok("hello = " + name);
    }

}

