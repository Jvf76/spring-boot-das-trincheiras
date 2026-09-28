package academy.devdojo.controller;

import academy.devdojo.Config.Connection;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/connections")
@Slf4j
@RequiredArgsConstructor
public class ConnectionController {

    private final Connection connectionMySql;
    @GetMapping
    public ResponseEntity<Connection> getCoonection(){
        return ResponseEntity.ok(connectionMySql);
    }


}
