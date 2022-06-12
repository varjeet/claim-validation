package com.sap.claimvalidation.controller;

import com.sap.claimvalidation.entities.Item;
import com.sap.claimvalidation.entities.Version;
import com.sap.claimvalidation.service.ItemService;
import com.sap.claimvalidation.service.VersionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
public class VersionController {
    private final VersionService versionService;

    VersionController(VersionService versionService){
        this.versionService=versionService;
    }
//    @PostMapping("/claim/version")
//    public ResponseEntity<Version> addVersion(@RequestBody Version version){
//        if(version != null){
//            return new ResponseEntity<>(versionService.addVersion(version), HttpStatus.CREATED);
//        }
//        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//    }

    @GetMapping("/claim/versions")
    public ResponseEntity<List<Version>> getAllVersions(){
        return new ResponseEntity<>(versionService.getAllVersion(),HttpStatus.OK);
    }

    @GetMapping("/claim/version/versionId")
    public ResponseEntity<Version> getItemById(@PathVariable(value = "versionID") String versionId){
        return new ResponseEntity<>(versionService.getVersionById(versionId),HttpStatus.OK);
    }


}
