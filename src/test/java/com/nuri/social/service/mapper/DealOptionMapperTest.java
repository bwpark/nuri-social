package com.nuri.social.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class DealOptionMapperTest {

    private DealOptionMapper dealOptionMapper;

    @BeforeEach
    public void setUp() {
        dealOptionMapper = new DealOptionMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        String id = "id1";
        assertThat(dealOptionMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(dealOptionMapper.fromId(null)).isNull();
    }
}
