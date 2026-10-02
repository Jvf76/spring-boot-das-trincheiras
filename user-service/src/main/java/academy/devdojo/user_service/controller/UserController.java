package academy.devdojo.user_service.controller;

import academy.devdojo.user_service.mapper.UserMapper;
import academy.devdojo.user_service.request.UserPostRequest;
import academy.devdojo.user_service.request.UserPutRequest;
import academy.devdojo.user_service.response.UserGetResponse;
import academy.devdojo.user_service.response.UserPostResponse;
import academy.devdojo.user_service.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
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

    @GetMapping("/{id}")
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

        return ResponseEntity.status(HttpStatus.CREATED).body(response);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<UserPostResponse> delete(@PathVariable Long id ){
        log.debug("Request to delete anime by id: {}", id);
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping
    public ResponseEntity<Void> update(@RequestBody UserPutRequest request){
        log.debug("Request to update user by id: {}", request);

        var user = mapper.toUserPutRequest(request);

        service.update(user);

        return ResponseEntity.noContent().build();
    }

}
