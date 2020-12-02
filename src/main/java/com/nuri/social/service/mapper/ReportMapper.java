package com.nuri.social.service.mapper;


import com.nuri.social.domain.*;
import com.nuri.social.service.dto.ReportDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Report} and its DTO {@link ReportDTO}.
 */
@Mapper(componentModel = "spring", uses = {AvatarMapper.class})
public interface ReportMapper extends EntityMapper<ReportDTO, Report> {

    @Mapping(source = "you.id", target = "youId")
    @Mapping(source = "me.id", target = "meId")
    ReportDTO toDto(Report report);

    @Mapping(source = "youId", target = "you")
    @Mapping(source = "meId", target = "me")
    Report toEntity(ReportDTO reportDTO);

    default Report fromId(String id) {
        if (id == null) {
            return null;
        }
        Report report = new Report();
        report.setId(id);
        return report;
    }
}
