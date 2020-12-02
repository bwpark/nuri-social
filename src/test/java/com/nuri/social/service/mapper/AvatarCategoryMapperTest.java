package com.nuri.social.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class AvatarCategoryMapperTest {

    private AvatarCategoryMapper avatarCategoryMapper;

    @BeforeEach
    public void setUp() {
        avatarCategoryMapper = new AvatarCategoryMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        String id = "id1";
        assertThat(avatarCategoryMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(avatarCategoryMapper.fromId(null)).isNull();
    }
}
