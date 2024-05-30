package com.se104.librarymanagementbe.controller;

import com.se104.librarymanagementbe.common.RestResponse;
import com.se104.librarymanagementbe.dto.request.CreateConfigLibraryRequest;
import com.se104.librarymanagementbe.dto.response.CreateConfigLibraryResponse;
import com.se104.librarymanagementbe.dto.response.GetListAuthorResponse;
import com.se104.librarymanagementbe.dto.response.GetListConfigLibraryResponse;
import com.se104.librarymanagementbe.dto.response.GetOneConfigLibraryResponse;
import com.se104.librarymanagementbe.service.ConfigLibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/configLibrary")
public class ConfigLibraryController {
    @Autowired
    private ConfigLibraryService configLibraryService;
    @PostMapping
    public ResponseEntity<RestResponse<CreateConfigLibraryResponse>> createConfigLibrary (@RequestBody CreateConfigLibraryRequest configLibrary){
        return ResponseEntity.ok().body(configLibraryService.createConfigLibrary(configLibrary));
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<RestResponse<GetOneConfigLibraryResponse>> getOneConfigLibrary(@PathVariable Long id) {
        return ResponseEntity.ok().body(configLibraryService.getOneConfigLibrary(id));
    }
    @GetMapping
    public ResponseEntity<RestResponse<List<GetListConfigLibraryResponse>>> getListConfigLibrary(){
        return ResponseEntity.ok().body(configLibraryService.getListConfigLibraries());
    }
    @GetMapping(value = "/last/config")
    public ResponseEntity<RestResponse<GetOneConfigLibraryResponse>> getLastConfigLibrary(){
        return ResponseEntity.ok().body(configLibraryService.getLastConfigLibrary());
    }
}
