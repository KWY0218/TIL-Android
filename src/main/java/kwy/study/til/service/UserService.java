package kwy.study.til.service;

import kwy.study.til.domain.User;
import kwy.study.til.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User createUser(String name) {
        User newUser = new User(name);
        return userRepository.save(newUser);
    }

    @Transactional
    public User updateUser(Long userId, String name) {
        User user = userRepository.findById(userId).orElseThrow();
        user.setName(name);
        return user;
    }

    public User getUser(Long userId) {
        return userRepository.findById(userId).orElseThrow();
    }

    @Transactional
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }
}
