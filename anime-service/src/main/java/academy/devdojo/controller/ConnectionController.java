package academy.devdojo.controller;

import academy.devdojo.external.dependency.Connection;
import academy.devdojo.mapper.AnimeMapper;
import academy.devdojo.request.AnimePostRequest;
import academy.devdojo.request.AnimePutRequest;
import academy.devdojo.response.AnimeGetResponse;
import academy.devdojo.response.AnimePostResponse;
import academy.devdojo.service.AnimeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
