package com.nuri.social.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class AvatarMapperTest {

    private AvatarMapper avatarMapper;

    @BeforeEach
    public void setUp() {
        avatarMapper = new AvatarMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        String id = "id1";
        assertThat(avatarMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(avatarMapper.fromId(null)).isNull();
    }
}
