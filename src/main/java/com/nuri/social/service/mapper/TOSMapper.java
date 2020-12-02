package com.nuri.social.service.mapper;


import com.nuri.social.domain.*;
import com.nuri.social.service.dto.TOSDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link TOS} and its DTO {@link TOSDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface TOSMapper extends EntityMapper<TOSDTO, TOS> {



    default TOS fromId(String id) {
        if (id == null) {
            return null;
        }
        TOS tOS = new TOS();
        tOS.setId(id);
        return tOS;
    }
}
