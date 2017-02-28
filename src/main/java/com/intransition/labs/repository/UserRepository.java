package com.intransition.labs.repository;

import com.intransition.labs.domain.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends PagingAndSortingRepository<User, Integer> {
	
    User findByNickname( String nickname );
    
    User findByEmail( String email );
	
    Page<User> findAllByOrderByIdAsc( Pageable pageable );
}
