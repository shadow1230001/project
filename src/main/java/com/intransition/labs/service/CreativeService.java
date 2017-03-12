package com.intransition.labs.service;

import com.intransition.labs.domain.content.Creative;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Set;

public interface CreativeService {
    /**
     *
     * @param id id creative
     * @return Returns creative by id
     */

    Creative getCreative(int id);

    /**
     *
     * @param creativesToSort list creativesToSort
     * @return Creatives List sorted by Created
     */

    List<Creative> sortCreativesByCreated(Set<Creative> creativesToSort);

    /**
     *
     * @param creatives creatives collection
     * @return creatives sorted by CreatedDesc
     */

    List<Creative> sortCreativesByCreatedDesc(Set<Creative> creatives);

    /**
     *
     * @param pageable Exemplar Pageable
     * @return colletion sorted by Order and CreatedDesk
     */

    Page<Creative> findAllByOrderByCreatedDesc(Pageable pageable);

    /**
     *
     * @param pageable Exemplar Pageable
     * @return colletion sorted by Order and EditorDesk
     */

    Page<Creative> findAllByOrderByEditedDesc(Pageable pageable);

}