package io.junrock.GAZA.domain.admin.controller;

import io.junrock.GAZA.domain.admin.dto.SignupDto;
import io.junrock.GAZA.domain.admin.entity.Admin;
import io.junrock.GAZA.domain.admin.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping("/api/admin")
public class AdminController {
    private final AdminService adminService;
    @PostMapping("/signup")
    public ResponseEntity<SignupDto> registerAdmin(@Valid @RequestBody SignupDto signupDto){
        return ResponseEntity.ok(adminService.signupAdmin(signupDto));
    }
}
