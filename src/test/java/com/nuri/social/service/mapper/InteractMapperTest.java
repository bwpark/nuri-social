package com.nuri.social.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class InteractMapperTest {

    private InteractMapper interactMapper;

    @BeforeEach
    public void setUp() {
        interactMapper = new InteractMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        String id = "id1";
        assertThat(interactMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(interactMapper.fromId(null)).isNull();
    }
}
