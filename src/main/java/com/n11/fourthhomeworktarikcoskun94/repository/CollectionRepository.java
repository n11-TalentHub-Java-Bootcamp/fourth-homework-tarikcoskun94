package com.n11.fourthhomeworktarikcoskun94.repository;

import com.n11.fourthhomeworktarikcoskun94.entity.Collection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CollectionRepository extends JpaRepository<Collection, Long> {
}
