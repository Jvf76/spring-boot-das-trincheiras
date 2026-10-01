package academy.devdojo.user_service.controller;

import academy.devdojo.user_service.mapper.UserMapper;
import academy.devdojo.user_service.request.UserPostRequest;
import academy.devdojo.user_service.response.UserGetResponse;
import academy.devdojo.user_service.response.UserPostResponse;
import academy.devdojo.user_service.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/users")
@RequiredArgsConstructor
public class UserController {
    private final UserMapper mapper;
    private final UserService service;

    @GetMapping
    public ResponseEntity<List<UserGetResponse>> listAll(@RequestParam(required = false) String firstName){
        var userList = service.findAll(firstName);

        var response = mapper.toUserGetResponseList(userList);

        return ResponseEntity.ok(response);

    }

    @GetMapping("{id}")
    public ResponseEntity<UserGetResponse> findById(@PathVariable Long id){

        var user = service.findByIdOrThrowNotFound(id);

        var response = mapper.toUserGetResponse(user);

        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<UserPostResponse> saved(@RequestBody UserPostRequest userPostRequest){

        var user = mapper.toUserPostRequest(userPostRequest);

        var userSaved = service.saved(user);

        var response = mapper.toUserPostResponse(userSaved);

        return ResponseEntity.ok(response);

    }

}
