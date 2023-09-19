package sabancidx.productservice.business.concretes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ExpressionException;
import org.springframework.stereotype.Service;
import sabancidx.productservice.dataAccess.RoleRepository;
import sabancidx.productservice.entities.concretes.Role;
import sabancidx.productservice.entities.concretes.RoleName;
import sabancidx.productservice.security.JwtTokenProvider;

@Service
public class RoleService {
    private static final Logger logger = LoggerFactory.getLogger(JwtTokenProvider.class);

    @Autowired
    RoleRepository roleRepository;

    public Role getRoleByName(RoleName roleName) {
            return roleRepository.findByName(roleName).get();
    }


}
