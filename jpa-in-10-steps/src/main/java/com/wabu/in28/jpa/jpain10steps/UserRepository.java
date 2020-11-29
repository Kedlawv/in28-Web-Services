package com.wabu.in28.jpa.jpain10steps;

import com.wabu.in28.jpa.jpain10steps.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
