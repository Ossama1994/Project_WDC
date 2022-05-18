package com.youcode.wdcmanager.controller;

import com.youcode.wdcmanager.dto.role.CreateRoleDto;
import com.youcode.wdcmanager.dto.role.UpdateRoleDto;
import com.youcode.wdcmanager.entity.Role;
import com.youcode.wdcmanager.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController @RequestMapping("/api/role")
@RequiredArgsConstructor
public class RoleController {
    private final RoleService roleService;
    private final ModelMapper modelMapper;

    @GetMapping
    public List<Role> getAll() {
        return roleService.findAll();
    }

    @PostMapping
    public ResponseEntity<Role> create(@RequestBody CreateRoleDto roleDto) {
        if(roleDto == null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        Role role = modelMapper.map(roleDto, Role.class);
        Role createdRole = roleService.create(role);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdRole);
    }

    @PatchMapping("/{id}")
    public Role update(@PathVariable Long id, @RequestBody UpdateRoleDto roleDto) {
        Role role = modelMapper.map(roleDto, Role.class);
        return roleService.update(role);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        roleService.deleteById(id);
    }

    @GetMapping("/{id}")
    public Role getOne(@PathVariable Integer id) {
        return roleService.findById(id);
    }

}
