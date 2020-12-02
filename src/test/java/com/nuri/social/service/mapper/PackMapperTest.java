package com.nuri.social.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class PackMapperTest {

    private PackMapper packMapper;

    @BeforeEach
    public void setUp() {
        packMapper = new PackMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        String id = "id1";
        assertThat(packMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(packMapper.fromId(null)).isNull();
    }
}
