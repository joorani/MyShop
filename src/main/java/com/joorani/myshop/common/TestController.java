
package com.joorani.myshop.common;

import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
public class TestController {

    private final ApiLogger apiLogger;

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

        return ResponseEntity.ok("hello = " + name);
    }

    @RequestMapping("log-demo")
    public String logDemo(HttpServletRequest request) {
        long startTime = System.currentTimeMillis();
        String requestURL = request.getRequestURI();
        apiLogger.setRequestURL(requestURL);

        long endTime = System.currentTimeMillis();
        apiLogger.log(getClass().toString(),  endTime-startTime);
        return "logDemo";
    }
}

