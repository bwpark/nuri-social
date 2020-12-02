package com.nuri.social.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class ReputeMapperTest {

    private ReputeMapper reputeMapper;

    @BeforeEach
    public void setUp() {
        reputeMapper = new ReputeMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        String id = "id1";
        assertThat(reputeMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(reputeMapper.fromId(null)).isNull();
    }
}
