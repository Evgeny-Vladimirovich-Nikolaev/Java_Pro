package computerAccessories.service.impl;

import computerAccessories.service.UserServic;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import computerAccessories.dto.UserDto;
import computerAccessories.mapper.RoleMapper;
import computerAccessories.mapper.UserMapper;
import computerAccessories.model.type.Status;
import computerAccessories.repository.RoleReposotory;
import computerAccessories.repository.UserRepository;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServicImpl implements UserServic {

    private final UserMapper userMapper;
    private final RoleMapper roleMapper;
    private final RoleReposotory roleReposotory;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public UserDto save(UserDto userDto) {
        String encrypt = passwordEncoder.encode(userDto.getPassword());
        userDto.setStatus(Status.OK);
        userDto.setRole(roleMapper.toDtos(roleReposotory.findByName("admin")));
        userDto.setPassword(encrypt);
        return userMapper.toDto(userRepository.save(userMapper.toEntity(userDto)));
    }
}
