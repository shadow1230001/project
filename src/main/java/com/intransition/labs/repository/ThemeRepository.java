package com.intransition.labs.repository;

import com.intransition.labs.domain.user.Theme;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ThemeRepository extends JpaRepository<Theme, Long> {

}
