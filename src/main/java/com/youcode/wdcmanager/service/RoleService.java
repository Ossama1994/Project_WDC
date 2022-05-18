package com.youcode.wdcmanager.service;

import com.youcode.wdcmanager.entity.Role;
import com.youcode.wdcmanager.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;

    public Role create(Role role) {
        return roleRepository.save(role);
    }

    public Role update(Role role) {
        return roleRepository.save(role);
    }

    public Role findById(Integer id) {
        return roleRepository.findById(id).orElse(null);
    }

    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    public void deleteById(Integer id) {
        roleRepository.deleteById(id);
    }
}
