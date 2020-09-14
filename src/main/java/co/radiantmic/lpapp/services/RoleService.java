package co.radiantmic.lpapp.services;

import co.radiantmic.lpapp.domain.Role;
import co.radiantmic.lpapp.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    public List<Role> getAllRoles(){
        return roleRepository.findAll();
    }

    public Role getRoleById(Long roleId){
        Optional<Role> optionalRole = roleRepository.findById(roleId);
        return optionalRole.orElse(null);
    }

}
